/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary;

import java.util.HashMap;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author bokon
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(
        basePackages = "com.accionmfb.ussd.repository.customerAccount",
        entityManagerFactoryRef = "customerAccountEntityManager",
        transactionManagerRef = "customerAccountTransactionManager"
)
@EnableTransactionManagement
public class CustomerAccountPersistenceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.ds_customerAccount.db.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.ds_customerAccount.url"));
        dataSource.setUsername(env.getProperty("spring.ds_customerAccount.db.username"));
        dataSource.setPassword(env.getProperty("spring.ds_customerAccount.db.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean customerAccountEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.accionmfb.ussd.model.customerAccount"});
        em.setPersistenceUnitName("customerAccountPersistenceUnit");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean()
    public PlatformTransactionManager customerAccountTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(customerAccountEntityManager().getObject());
        return transactionManager;
    }

}
