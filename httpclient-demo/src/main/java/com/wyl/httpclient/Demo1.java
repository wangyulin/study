package com.wyl.httpclient;

import com.wyl.common.utils.Logger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by wangyulin on 8/17/16.
 */
public class Demo1 {

    static Logger log = Logger.getLogger(Demo1.class);

    public static void main(String[] args) throws IOException, URISyntaxException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URI uri = new URIBuilder("http://www.baidu.com").build();
        HttpGet httpGet = new HttpGet(String.valueOf(uri));
        log.info( "url : " + httpGet.getURI().toString());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        log.info(response.getStatusLine().toString());
        log.info(response.getProtocolVersion().toString());
        log.info(response.getAllHeaders().toString());
    }
}
