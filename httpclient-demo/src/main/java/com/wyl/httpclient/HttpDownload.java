package com.wyl.httpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyStore;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 说明
 *
 */
@SuppressWarnings("deprecation")
public class HttpDownload {

	public static final int cache = 10 * 1024;
	public static final boolean isWindows;
	public static final String splash;
	public static final String root;
	private static HttpClient client;
	static {
		if (System.getProperty("os.name") != null && System.getProperty("os.name").toLowerCase().contains("windows")) {
			isWindows = true;
			splash = "\\";
			root="D:";
			System.out.println(isWindows);
			System.out.println(splash);
			System.out.println(root);
		} else {
			isWindows = false;
			splash = "/";
			root="/search";
			System.out.println(isWindows);
			System.out.println(splash);
			System.out.println(root);
		}
	}
	
	/**
	 * 根据url下载文件，文件名从response header头中获取
	 * @param url
	 * @return
	 */
	public static String download(String url) {
		return download(url, null);
	}

	/**
	 * 根据url下载文件，保存到filepath中
	 * @param url
	 * @param filepath
	 * @return
	 */
	public static String download(String url, String filepath) {
		try {
			client = new DefaultHttpClient();
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			InputStream in = new FileInputStream(new File("/Users/wangyulin/workDir/keystore/srca.ketstore"));
			trustStore.load(in, "123456".toCharArray());
			in.close();
			SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
			Scheme sch = new Scheme("https", 443, socketFactory);
			client.getConnectionManager().getSchemeRegistry().register(sch);
			
			
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = client.execute(httpget);

			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			if (filepath == null)
				filepath = getFilePath(response);
			File file = new File(filepath);
			file.getParentFile().mkdirs();
			FileOutputStream fileout = new FileOutputStream(file);

			byte[] buffer=new byte[cache];
			int ch = 0;
			while ((ch = is.read(buffer)) != -1) {
				fileout.write(buffer,0,ch);
			}
			is.close();
			fileout.flush();
			fileout.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取response要下载的文件的默认路径
	 * @param response
	 * @return
	 */
	public static String getFilePath(HttpResponse response) {
		String filepath = root + splash;
		String filename = getFileName(response);

		if (filename != null) {
			filepath += filename;
		} else {
			filepath += getRandomFileName();
		}
		return filepath;
	}
	/**
	 * 获取response header中Content-Disposition中的filename值
	 * @param response
	 * @return
	 */
	public static String getFileName(HttpResponse response) {
		Header contentHeader = response.getFirstHeader("Content-Disposition");
		String filename = null;
		if (contentHeader != null) {
			HeaderElement[] values = contentHeader.getElements();
			if (values.length == 1) {
				NameValuePair param = values[0].getParameterByName("filename");
				if (param != null) {
					try {
						filename = param.getValue();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return filename;
	}
	/**
	 * 获取随机文件名
	 * @return
	 */
	public static String getRandomFileName() {
		return String.valueOf(System.currentTimeMillis());
	}
	public static void outHeaders(HttpResponse response) {
		Header[] headers = response.getAllHeaders();
		for (int i = 0; i < headers.length; i++) {
			System.out.println(headers[i]);
		}
	}
	public static void main(String[] args) {
		String url="https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?station_version=1.8971";
		String filepath = "/Users/wangyulin/test_1.txt";
		HttpDownload.download(url, filepath);
	}
}
