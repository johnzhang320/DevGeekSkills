package com.spring.ssl.rest.utils;

public interface Constants {
	public static final String PROTOCAL="https://";
	public static final String PORT="8092";
	public static final String CONTEXT_PATH="/spring-boot-restful-ouath-ssl-server/";
	
	
	
	public static final String LOCAL_HOST_SERVICE= PROTOCAL+"localhost:"+PORT+CONTEXT_PATH;
	 
	public static final String trustStoreFilename="selfsignkeystore.jks"; //"tomcat_keystore.jks";
    public static final String trustStorePassword="rose1234";     //"marshallpwd";
    
	public static final String keystoreFilename=null;  
    public static final String keystorePassword=null;    

	public static final String TRUSTED_CLIENT_ID="my-trusted-client"; 
    public static final String TRUSTED_CLIENT_SECRET="secret"; 
    
	public static final String RESOURCE_OWNER_USERNAME="suresh"; 
    public static final String RESOURCE_OWNER_PASSWORD="abc12345";     
    
    public static final String GRANT_TYPE="password";
    
   
}
