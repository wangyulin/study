package com.wyl.spring.demo.soundsystem.chapter3.embeddeddb;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by wangyulin on 14/01/2017.
 */

@Configuration
@Profile ( "dev" )
public class DevelopmentProfileConfig {

    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType( EmbeddedDatabaseType.H2)
                .addScript ( "classpath:script/create-db.sql" )
                .addScript ( "classpath:script/insert-data.sql" )
                .build ();
    }

}
