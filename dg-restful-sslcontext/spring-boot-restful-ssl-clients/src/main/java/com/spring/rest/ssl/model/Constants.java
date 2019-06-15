package com.spring.rest.ssl.model;

public interface Constants {
	public static final String PROTOCAL="https://";
	public static final String PORT="8443";
	public static final String CONTEXT_PATH="/spring-boot-restful-ssl-server/";
	
	
	
	public static final String LOCAL_HOST_SERVICE= PROTOCAL+"localhost:"+PORT+CONTEXT_PATH;
	 
	public static final String trustStoreFilename="selfsignkeystore.jks"; //"tomcat_keystore.jks";
    public static final String trustStorePassword="rose1234";     //"marshallpwd";
    
	public static final String keystoreFilename=null;  
    public static final String keystorePassword=null;    
		


}
