package com.dataart.softwarestore.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class HibernateProperties {

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @SuppressWarnings("serial")
    public Properties getProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", hibernateDialect);
            }
        };
    }
}
