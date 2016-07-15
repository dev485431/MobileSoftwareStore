package com.dataart.softwarestore.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class HibernateProperties {

    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddl;
    @Value("${hibernate.cache.use.query.cache}")
    private String hibernateQueryCache;
    @Value("${hibernate.cache.region.factory_class}")
    private String cacheRegionFactoryClass;


    @SuppressWarnings("serial")
    public Properties getProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", hibernateDialect);
                setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddl);
                setProperty("hibernate.cache.use_query_cache", hibernateQueryCache);
                setProperty("hibernate.cache.region.factory_class", cacheRegionFactoryClass);
            }
        };
    }
}
