package com.mkyong.test;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
/**
 * 
 *@EnableCaching
 *Create a ehcache.xml file, to tell Ehcache how and where to cache the data.
 */
@Configuration
@EnableCaching
@ComponentScan({ "com.mkyong.*" })
public class AppConfig {

	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}
    // load ehcache.xml file
	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		
		return cmfb;
	}

	/*@Bean
    public CacheManager defaultCacheManager() {
        return new ConcurrentMapCacheManager("books");
    }*/
	
}