package com.wyl.spring.boot.demo.conf;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by wangyulin on 27/03/2017.
 */
@EnableAutoConfiguration(exclude = {  DataSourceAutoConfiguration.class })
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "PRIMARY_ENTITY_MANAGER_FACTORY",
        transactionManagerRef = "PRIMARY_PLATFORM_TX_MANAGER", basePackages = { "com.wyl.spring.boot.demo.repository" })
public class PrimaryDataSourceConfig {

    public static final String PRIMARY_JPA_PROPS = "primary.jpa";
    public static final String PRIMARY_DATASOURCE = "primary.datasource";
    public static final String PRIMARY_PERSISTENCE_UNIT = "PRIMARY_PERSISTENCE_UNIT";
    public static final String PRIMARY_ENTITY_MANAGER = "PRIMARY_ENTITY_MANAGER";
    public static final String PRIMARY_ENTITY_MANAGER_FACTORY = "PRIMARY_ENTITY_MANAGER_FACTORY";
    public static final String PRIMARY_PLATFORM_TX_MANAGER = "PRIMARY_PLATFORM_TX_MANAGER";

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Bean(name = PRIMARY_JPA_PROPS)
    @Primary
    @ConfigurationProperties(PRIMARY_JPA_PROPS)
    public JpaProperties primaryJpaProperties() {
        return new JpaProperties();
    }

    @Bean(name = PRIMARY_DATASOURCE)
    @Primary
    //@ConfigurationProperties(prefix = PRIMARY_DATASOURCE)
    @ConfigurationProperties
    public DataSource primaryDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(dataSourceConfig.getDriverClassname());
        dataSourceBuilder.url(dataSourceConfig.getUrl());
        dataSourceBuilder.username(dataSourceConfig.getUserName());
        dataSourceBuilder.password(dataSourceConfig.getPassword());
        return dataSourceBuilder.build();
    }

    @Bean(name = PRIMARY_ENTITY_MANAGER)
    @Primary
    public EntityManager primaryEntityManager(@Qualifier(PRIMARY_ENTITY_MANAGER_FACTORY) EntityManagerFactory
                                                      entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean(name = PRIMARY_ENTITY_MANAGER_FACTORY)
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource())
                .packages("com.wyl.spring.boot.demo.model")
                .persistenceUnit(PRIMARY_PERSISTENCE_UNIT)
                .properties(primaryJpaProperties().getHibernateProperties(primaryDataSource()))
                .build();
    }

    @Bean(name = PRIMARY_PLATFORM_TX_MANAGER)
    @Primary
    public PlatformTransactionManager primaryPlatformTransactionManager(@Qualifier(PRIMARY_ENTITY_MANAGER_FACTORY)
                                                                                EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}