package com.hibernate.utils;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

 

@Configuration

public class EHCacheConfig {
	
	/*  It shows cache enabling with EhCache-related beans in a separate configuration class.
	 *  Overriding these two beans is not needed if you want to stay with the default definition,
	 *  but we wanted to make cache transactions aware to synchronize put/evict operations with
	 *  ongoing Spring-managed transactions.*/

/*	 @Bean
	    public EhCacheManagerFactoryBean ehCacheManagerFactory() {
	        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
	        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
	        cacheManagerFactoryBean.setShared(true);
	        return cacheManagerFactoryBean;
	    }
    @Bean
    public EhCacheCacheManager cacheManager() {
        EhCacheCacheManager cacheManager = new EhCacheCacheManager();
        cacheManager.setCacheManager(ehCacheManagerFactory().getObject());
        cacheManager.setTransactionAware(true);
        return cacheManager;
    }*/
	/*
	 *  User Spring CacheManager instead of ehcache one
	 */
 /*   @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(cacheMangerFactory().getObject());
    }
*/
   @Bean
    public EhCacheCacheManager cacheManager() {
        EhCacheCacheManager cacheManager = new EhCacheCacheManager();
        cacheManager.setCacheManager(cacheMangerFactory().getObject());
        cacheManager.setTransactionAware(true);
        return cacheManager;
    }
    @Bean
    public EhCacheManagerFactoryBean cacheMangerFactory() {
        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        bean.setShared(true);
        return bean;
    }

}
