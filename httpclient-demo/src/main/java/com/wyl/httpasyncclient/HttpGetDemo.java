package com.wyl.httpasyncclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.Future;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpGetDemo {

	public static void main(String[] args) throws Exception {
		KeyStore trustStore  = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream instream = new FileInputStream(new File("/Users/wangyulin/workDir/keystore/srca.ketstore"));
        
        try {
            trustStore.load(instream, "123456".toCharArray());
        } finally {
            instream.close();
        }
        
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
                .build();
        
        SSLIOSessionStrategy sslSessionStrategy = new SSLIOSessionStrategy(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLIOSessionStrategy.getDefaultHostnameVerifier());
        
        
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setSSLStrategy(sslSessionStrategy)
                .build();
        
		//CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        InputStream in = null;
		try {
            httpclient.start();
            HttpGet request = new HttpGet(
            		"http://kyfw.12306.cn/otn/leftTicket/queryX?leftTicketDTO.train_date=2016-12-23&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=VUQ&purpose_codes=ADULT");
                //https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?station_version=1.8971
            	//https://kyfw.12306.cn/otn/leftTicket/queryX?leftTicketDTO.train_date=2016-12-23&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=VUQ&purpose_codes=ADULT
            Future<HttpResponse> future = httpclient.execute(request, null);
            HttpResponse response = future.get();
            HttpEntity entity = response.getEntity();
            
            String charset = EntityUtils.getContentCharSet(entity);
            
            JSONObject json = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
            
            in = entity.getContent();
            byte[] buffer = new byte[4096];
            int readLength = 0;
            int line_counter = 0;
            while ((readLength=in.read(buffer)) > 0) {
            	byte[] bytes = new byte[readLength];
            	line_counter ++;
            	System.out.println(new String(buffer,"UTF-8"));
            }
            
            System.out.println("Response: " + response.getStatusLine());
            System.out.println("Body: " + json);
            System.out.println("Shutting down");
            //System.out.println(line_counter);
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
	}
	
	public static JSONObject getTrainTicket(String date, String fromStation, String toStation) {
		CloseableHttpAsyncClient httpclient = null;
		KeyStore trustStore;
		JSONObject json = null;
		try {
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream instream = new FileInputStream(new File("/Users/wangyulin/workDir/keystore/srca.ketstore"));
	        
			trustStore.load(instream, "123456".toCharArray());
	        
	        SSLContext sslcontext = SSLContexts.custom()
	                .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
	                .build();
	        SSLIOSessionStrategy sslSessionStrategy = new SSLIOSessionStrategy(
	                sslcontext,
	                new String[] { "TLSv1" },
	                null,
	                SSLIOSessionStrategy.getDefaultHostnameVerifier());
	        httpclient = HttpAsyncClients.custom()
	                .setSSLStrategy(sslSessionStrategy)
	                .build();
	        
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | KeyManagementException e1) {
			e1.printStackTrace();
		}
        
        try {
            httpclient.start();
            String url = String.format(
            		"https://kyfw.12306.cn/otn/leftTicket/queryX?leftTicketDTO.train_date=%s&leftTicketDTO.from_station=%s&leftTicketDTO.to_station=%s&purpose_codes=ADULT", date, fromStation,toStation);
            //System.out.println(url);
            HttpGet request = new HttpGet(url);
            Future<HttpResponse> future = httpclient.execute(request, null);
            HttpResponse response = future.get();
            json = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
            return json;
        } catch (Exception e) {
        	System.out.println(fromStation + ";" + toStation + " 数据获取异常");
		} finally {
            if(httpclient != null) {
            	try {
					httpclient.close();
				} catch (IOException e) {
					System.out.println("httpclient 连接关闭异常");
				}
            }
        }
		return null;
	}

}
