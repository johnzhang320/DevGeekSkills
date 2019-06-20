package com.hibernate.utils;

 
import com.google.common.base.Preconditions;
 

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
/** 
 * Important annotation notes:
 * @Configuration -- means this class is spring configuration file, the sessionfactory as bean in SpringSessionFactoryValidate will be injected by app's loading this configuration class 
 *                   @ContextConfiguration(classes = { SpringSessionFactoryCreate.class }, loader = AnnotationConfigContextLoader.class)
 *                   before the class which use sessionfactory
 * @EnableTransactionManagement  -- @EnableTransactionManagement  will fall back to a by-type lookup for any PlatformTransactionManager bean in the container. 
 * @PropertySource  ---- Load specified properties file to Environment        
 * @Component       ----- make SpringSessionFactoryValidate  as another configuration file, app can use
 * 						  @ContextConfiguration(classes = { SpringSessionFactoryValidate.class }, loader = AnnotationConfigContextLoader.class)
 * 						  to load this file just like XMLApplicationContext("applicationContext.xml")                     
 */
@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:hibernate-validate.properties" })
@Component
public class SpringSessionFactoryValidate {
	
	static Logger Log = Logger.getLogger( SpringSessionFactoryValidate.class);
    @Autowired
    private Environment env;
   

	@Bean
    public LocalSessionFactoryBean sessionFactory() {
    	Log.info("sessionFactory() begin");
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { 
        		"com.hibernate.one.to.many.model",
        		"com.hibernate.many.to.many.model",
        		"com.hibernate.one.to.one.primary.model",
        	//	"com.hibernate.custom.type.model",
    			"com.hibernate.join.table.many.to.many.model" 
        		});
      
        sessionFactory.setHibernateProperties(hibernateProperties());
        Log.info("sessionFactory() end");
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        Log.info("jdbc.driverClassName="+env.getProperty("jdbc.driverClassName"));
        Log.info("jdbc.url="+env.getProperty("jdbc.url"));
        Log.info("jdbc.user="+env.getProperty("jdbc.user")); 
        Log.info("jdbc.pass="+env.getProperty("jdbc.pass"));
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

  /*
   * in hibernate-base.properties contain below properties
   * 
   * hibernate.dialect=org.hibernate.dialect.MySQLDialect
	 hibernate.show_sql=true
	 hibernate.connection.autocommit=true
	
	 # hibernate.hbm2ddl.auto = create -- drop current tables and recreate again only effects on metadata we defined in SessionFactoryService
	 # hibernate.hbm2ddl.auto = validate -- update and query existing tables
	 hibernate.hbm2ddl.auto=create
	 # c3p0 connection pool
	 hibernate.c3p0.min_size=5
	 hibernate.c3p0.max_size=200
	 hibernate.c3p0.timeout=180
	 hibernate.c3p0.max_statements=50
	 envers.audit_table_suffix=_audit_log
   */
    
   public final Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
       
         hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        
        Log.info("hibernateProperties.setProperty(\"hibernate.hbm2ddl.auto\"="+hibernateProperties.getProperty("hibernate.hbm2ddl.auto"));
        
        hibernateProperties.setProperty("hibernate.connection.autocommit", env.getProperty("hibernate.connection.autocommit"));
        hibernateProperties.setProperty("hibernate.c3p0.min_size", env.getProperty("hibernate.c3p0.min_size"));
        hibernateProperties.setProperty("hibernate.c3p0.max_size", env.getProperty("hibernate.c3p0.max_size"));
        hibernateProperties.setProperty("hibernate.c3p0.timeout", env.getProperty("hibernate.c3p0.timeout"));
        hibernateProperties.setProperty("hibernate.c3p0.max_statements", env.getProperty("hibernate.c3p0.max_statements"));
        hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
         // Envers properties
        hibernateProperties.setProperty("envers.audit_table_suffix", env.getProperty("envers.audit_table_suffix"));
        hibernateProperties.setProperty("hibernate.connection.characterEncoding",env.getProperty("hibernate.connection.characterEncoding"));
        return hibernateProperties;
    }

}