package com.loan.agent.mvc.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.DataSources;
import com.loan.agent.services.Constant;
import com.loan.agent.services.impl.AgentReviewServiceImpl;

public class ConnectionManager {
	    private static DataSource pooledConnection = null;
	    private final String MYSQL_DEFAULT_USER="johnz148_agent";
	    private final String MYSQL_DEFAULT_PASS="nus658742478";
	    static final Logger LOG = Logger.getLogger(AgentReviewServiceImpl.class); 
	    
	    private static ConnectionManager handler = null;
	   
	    public static ConnectionManager getInstance() {
	    	  if (null==handler || null==pooledConnection) {
	    		  handler = new ConnectionManager();
	    		  
	    	  }
	    	  return handler;
	    }
	    private ConnectionManager() {
	    	createDatabaseConnectionPool();
	    }
	    public boolean createDatabaseConnectionPool() {	     
    	try {
    		LOG.info("ConnectionManager.createDatabaseConnectionPool: Creating connection pool.");
	    	
    		Class.forName(Constant.DB_DEVICE_DRIVER).newInstance();
	      //  String url = "jdbc:mysql://localhost:3306/johnz148_mortgage_agency";
    		  String url = "jdbc:mariadb://localhost:3306/johnz148_mortgage_agency";
  	        
	      	        
	        Properties connProp = new Properties();
	         connProp.put("checkoutTimeout", "120000");
	         connProp.put("testConnectionOnCheckout", "true");
	         connProp.put("autoCommitOnClose", "true");
	         connProp.put("autoReconnect", "true");
	        
	        DataSource unpooled = DataSources.unpooledDataSource(url, MYSQL_DEFAULT_USER, MYSQL_DEFAULT_PASS);
	        pooledConnection = DataSources.pooledDataSource( unpooled, connProp );
	        
	        LOG.info("ConnectionManager.createDatabaseConnectionPool: Creating connection pool succedded.");
    	} catch (Exception exp) {
    		LOG.info("ConnectionManager.createDatabaseConnectionPool: Exception in creating connection pool. " + exp.getMessage());
    		return false;
    	}
    	return true;
    }

		public DataSource getPooledConnection() {
			return pooledConnection;
		}

		public void setPooledConnection(DataSource pooledConnection) {
			this.pooledConnection = pooledConnection;
		}
		public static Connection getConnection() throws Exception {
			    getInstance();
		    	Connection conn = pooledConnection.getConnection();
		    	conn.setAutoCommit(true);
		    	return conn;
		 }

}
