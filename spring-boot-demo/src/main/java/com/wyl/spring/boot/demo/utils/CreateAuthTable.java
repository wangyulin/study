package com.wyl.spring.boot.demo.utils;

/**
 * Created by wangyulin on 10/05/2017.
 */
public class CreateAuthTable {

    public static void main(String[] args) {
        String createTable = "CREATE TABLE `auth` (`id` bigint(20) NOT NULL AUTO_INCREMENT,`user_id` bigint(20) DEFAULT NULL,`product_type` varchar(32) DEFAULT NULL,`product_id` varchar(36) DEFAULT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        String createindex = "ALTER TABLE `auth` ADD INDEX idx_userId_product_type (user_id, product_type);";

        for(int i = 0; i < 1000; i++) {
            String table = createTable.replaceFirst("auth", "auth_" + i);
            String index = createindex.replaceFirst("auth", "auth_" + i);

            System.out.println(table);
            System.out.println(index);
        }
    }

}
