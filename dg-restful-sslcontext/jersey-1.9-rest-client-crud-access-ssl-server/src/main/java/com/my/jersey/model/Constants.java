package com.my.jersey.model;

public interface Constants {
	public static final String PROTOCAL="https://";
	public static final String PORT="8443";
	public static final String CONTEXT_PATH="/jersey-1.9-rest-client-crud-access-ssl-server/rest/";
	public static final String LOCAL_HOST_SERVICE= PROTOCAL+"localhost:"+PORT+CONTEXT_PATH;
	 
	public static final String trustStoreFilename="selfsignkeystore.jks"; //"tomcat_keystore.jks";
    public static final String trustStorePassword="rose1234";     //"marshallpwd";
		


}
