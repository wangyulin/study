package com.wyl.httpasyncclient;

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

import net.sf.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

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
		try {
            httpclient.start();
            HttpGet request = new HttpGet("https://kyfw.12306.cn/otn/leftTicket/queryC?leftTicketDTO.train_date=2016-10-24&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=XAY&purpose_codes=ADULT");
            Future<HttpResponse> future = httpclient.execute(request, null);
            HttpResponse response = future.get();
            HttpEntity entity = response.getEntity();
            entity.getContent();
            String charset = EntityUtils.getContentCharSet(entity);
            
            JSONObject json = JSONObject.fromObject(EntityUtils.toString(response.getEntity()));
            
            System.out.println("Response: " + response.getStatusLine());
            System.out.println("Body: " + json.optString("data1", "{}"));
            System.out.println("Shutting down");
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
	}

}
