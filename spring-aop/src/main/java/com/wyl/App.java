package com.wyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class App {

    public static void main(String[] args) {
        //SpringApplication.run(App.class, args);
        Long ipLong = setIP("183.62.169.146");
        System.out.println(ipLong);
        System.out.println(getIP(ipLong));
    }

    private static Long setIP(String ipaddr) {
        String ip[] = ipaddr.split("\\.");
        Long ipLong = 256 * 256 * 256 * Long.parseLong(ip[0]) +
                      256 * 256 * Long.parseLong(ip[1]) +
                      256 * Long.parseLong(ip[2]) +
                      Long.parseLong(ip[3]);
        return ipLong;
    }

    private static String getIP(Long ipaddr) {
        long y = ipaddr % 256;
        long m = (ipaddr - y) / (256 * 256 * 256);
        long n = (ipaddr - 256 * 256 *256 * m - y) / (256 * 256);
        long x = (ipaddr - 256 * 256 *256 * m - 256 * 256 *n - y) / 256;
        return m + "." + n + "." + x + "." + y;
    }
}
