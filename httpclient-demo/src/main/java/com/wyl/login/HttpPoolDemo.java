package com.wyl.login;

import com.google.common.io.CharStreams;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by wangyulin on 02/12/2017.
 */
public class HttpPoolDemo {

    public static void main(String[] args) {
        /**
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 200
        cm.setMaxTotal(200);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(20);
        // Increase max connections for localhost:80 to 50
        HttpHost localhost = new HttpHost("locahost", 8082);
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
        */
        String uid = "pkRfnHtKntPviz6mHvZF5vZnhWFysmN63G5wnucc7mRWiBkSXp-vKmHFJ307JwOsSCOJd5Sz0pGxROTr5Q7gCqSDTNrIgaAXoSNfNLWdPnfSBl-i8fZLT84-nUwL_XFGdfaww4OKpYQza6gdJ9zGAHEVr7o9q9f1SJdgyonnpXYx4VwX";

        HttpConnectionManager httpConnectionManager = new HttpConnectionManager();


        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                CloseableHttpClient httpClient = httpConnectionManager.getHttpClient();

                BasicCookieStore cookieStore = new BasicCookieStore();
                CloseableHttpClient httpclient = HttpClients.custom()
                        .setDefaultCookieStore(cookieStore)
                        .build();

                HttpUriRequest query = null;
                CloseableHttpResponse response = null;
                try {
                    query = RequestBuilder.get()
                            .setUri(new URI("http://127.0.0.1:8082/queryName")).build();
                    BasicClientCookie cookie = new BasicClientCookie("uid", uid);
                    cookie.setDomain("127.0.0.1");
                    cookie.setPath("/");
                    cookieStore.addCookie(cookie);

                    response = httpclient.execute(query);
                    HttpEntity entity = response.getEntity();
                    System.out.println("Result statue : " + response.getStatusLine());
                    String result = CharStreams.toString(new InputStreamReader(entity.getContent(), Charsets.UTF_8));
                    System.out.println(result);

                    List<Cookie> cookies = cookieStore.getCookies();
                    if (cookies.isEmpty()) {
                        System.out.println("None");
                    } else {
                        for (int e = 0; e < cookies.size(); e++) {
                            System.out.println("- " + cookies.get(e).toString());
                        }
                    }
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(null != response) {
                        try {
                            response.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

}
