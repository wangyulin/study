package com.wyl.login;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

import com.google.common.io.CharStreams;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

/**
 * Created by wangyulin on 02/12/2017.
 * A example that demonstrates how HttpClient APIs can be used to perform
 * form-based logon.
 */
public class ClientFormLogin {

    public static void main(String[] args) throws Exception {
        String uid = null;
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
        try {

            HttpGet httpget = new HttpGet("http://127.0.0.1:8082/queryName");
            CloseableHttpResponse response1 = httpclient.execute(httpget);
            try {
                HttpEntity entity = response1.getEntity();
                System.out.println("Login form get: " + response1.getStatusLine());
                System.out.println("Initial set of cookies:");
                List<Cookie> cookies = cookieStore.getCookies();
                if (cookies.isEmpty()) {
                    System.out.println("None");
                } else {
                    for (int i = 0; i < cookies.size(); i++) {
                        System.out.println("- " + cookies.get(i).toString());
                    }
                }
            } finally {
                response1.close();
            }

            HttpUriRequest login = RequestBuilder.post()
                    .setUri(new URI("http://127.0.0.1:8082/loginIn"))
                    .addParameter("account", "admin")
                    .addParameter("password", "admin")
                    .build();
            CloseableHttpResponse response2 = httpclient.execute(login);
            try {
                HttpEntity entity = response2.getEntity();

                System.out.println("Login form get: " + response2.getStatusLine());
                EntityUtils.consume(entity);

                System.out.println("Post logon cookies:");
                List<Cookie> cookies = cookieStore.getCookies();
                if (cookies.isEmpty()) {
                    System.out.println("None");
                } else {
                    for (int i = 0; i < cookies.size(); i++) {
                        System.out.println("- " + cookies.get(i).toString());
                        uid = cookies.get(i).getValue();
                    }
                }
            } finally {
                response2.close();
            }


            /**
             *  通过cookie携带登陆信息
             */
            HttpUriRequest query = RequestBuilder.get()
                    .setUri(new URI("http://127.0.0.1:8082/queryName")).build();
            BasicClientCookie cookie = new BasicClientCookie("uid", uid);
            cookie.setDomain("127.0.0.1");
            cookie.setPath("/");
            cookieStore.addCookie(cookie);
            CloseableHttpResponse response3 = httpclient.execute(query);
            try {
                HttpEntity entity = response3.getEntity();
                System.out.println("Login form get: " + response3.getStatusLine());
                //EntityUtils.consume(entity);

                String result = CharStreams.toString(new InputStreamReader(entity.getContent(), Charsets.UTF_8));
                //EntityUtils.consume(entity);
                System.out.println(result);

                System.out.println("Post logon cookies:");
                List<Cookie> cookies = cookieStore.getCookies();
                if (cookies.isEmpty()) {
                    System.out.println("None");
                } else {
                    for (int i = 0; i < cookies.size(); i++) {
                        System.out.println("- " + cookies.get(i).toString());
                    }
                }
            } finally {
                response2.close();
            }


        } finally {
            httpclient.close();
        }
    }
}