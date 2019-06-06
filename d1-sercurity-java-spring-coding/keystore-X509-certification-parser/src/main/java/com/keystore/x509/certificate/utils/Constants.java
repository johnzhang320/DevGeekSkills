package com.keystore.x509.certificate.utils;

public interface Constants {
	// ------------------Should write below lines into properties file  -----------------------
	
	public static final String BULK_API_MODE="bulkAPI";
	public static final String CERT_MANAGER_MODE="certMgr";
	
 	public static final String alias="asystem3.apple.com";
	public static final String STORE_PASSWORD = "abc1234";  
	public static final String KEY_PASSWORD = "abc1234";  
	 
 	public static String KSCONFIGURE_FULLNAME="ksconfigure_fullname";
	public static String CA_SERVER_HOST="certificate_authority_server_host";
	public static String CA_SERVER_IP="certificate_authority_server_ip";
	public static String CA_SERVER_PORT="certificate_authority_server_port";
	public static String TLS_SERVER="tlsserver";
	public static String KS_DIRECTORY="directory";
	public static String KS_PATH="path";
	public static String KEYSTORE="keystore";
	public static String KS_NAME="name";
	public static String KEYSTORE_TYPE="keystoretype";
	public static String TRUSTSTORE="truststore";
	public static String ALIAS="alias";
	 
	
	public static String WRONG_KEYTOOL_COMMAND="Wrong Keytool Command";
	public static String WRONG_KEYSTORE_PASSWORD="password was incorrect";
	public static String TEMPERED_KEYSTORE="Keystore was tampered";
	
	
	public static String USER_HOME="user.home";
	
	public static String CC_RENEW_HOME="ccrenew";
	
	public static String PROP_ENV_ONE_PROPERTIES="env_one.properties";
	public static String PROP_DATABAGS_PATH="databags_path";
	public static String PROP_KEYSTORES_PATH="keystores_path";
	public static String PROP_DATABAGS_SUBDIR_FILTER="databags_subdir_filter";
	public static String PROP_GPG_KEY_FILE_NAME ="gpg_key_file_name";
	
	
	
	
	public static String KS_DATABAGS="databags";
	public static String KS_KEYSTORES="keystores";
	public static String KS_SERVICE="service";
	public static String KS_FILENAME="keystore.filename";
	public static String KS_PASSWORD="keystore.password";
	
	 
	public static String JKS_ALIAS_NAME="Alias name";
	public static String JKS_KEYSTORE_TYPE="Keystore type";
	public static String JKS_MANAGEMENT_GROUP="management:idms.group.";
	public static String JKS_IDENTITY_GROUP="identity:idms.group.";
	public static String JKS_APPLE_INC="O=Apple Inc.";
	public static String JKS_ENTRY_TYPE="Entry type";
	public static String JKS_OWNER="Owner";
	public static String JKS_SIGAGUTURE_ALGORITHM_NAME="Signature algorithm name";
	public static String JKS_SUBJECT_PUBLIC_KEY_ALGORITHM="Subject Public Key Algorithm";
	public static String JKS_SHA256="SHA256:";
	public static String JKS_ISSUER="Issuer";
	public static String JKS_SERIAL_NAME="Serial number";
	public static String JKS_KEY="key";
	public static String JKS_UNTIL_COLON="until:";
	
	//GIT Repository Constants	
	public static String GIT_REPO_URL_QA="git@github.pie.apple.com:apple-pay/applepay_qa_environment.git";
	public static String GIT_REPO_URL_QA_NC="git@github.pie.apple.com:apple-pay/applepay_qa-nc_environment.git";
	public static String GIT_REPO_URL_LT="git@github.pie.apple.com:apple-pay/applepay_lt_environment.git";	
	public static String GIT_REPO_URL_STAG="git@github.pie.apple.com:apple-pay/applepay_staging_environment.git";	
	public static String GIT_REPO_URL_PROD="git@github.pie.apple.com:apple-pay/applepay_production_environment.git";
	public static String GIT_REPO_URL_PROD_NC="git@github.pie.apple.com:apple-pay/applepay_production-nc_environment.git";
	public static String GIT_REPO_URL_PROD_PR="git@github.pie.apple.com:apple-pay/applepay_production-pr_environment.git";
	public static String GIT_REPO_URL_PROD_TJ="git@github.pie.apple.com:apple-pay/applepay_production-tj_environment.git";
	public static String GIT_REPO_URL_PROD_SH="git@github.pie.apple.com:apple-pay/applepay_production-sh_environment.git";		
	public static String GIT_REPO_URL_PROD_CN="git@github.pie.apple.com:apple-pay/applepay_cert-cn_environment.git";	


	
	//GIT Repository Names
	public static String GIT_REPO_ENV_QA="applepay_qa_environment";
	public static String GIT_REPO_ENV_QA_NC="applepay_qa-nc_environment";
	public static String GIT_REPO_ENV_LT="applepay_lt_environment";	
	public static String GIT_REPO_ENV_STAG="applepay_staging_environment";	
	public static String GIT_REPO_ENV_PROD="applepay_production_environment";
	public static String GIT_REPO_ENV_PROD_NC="applepay_production-nc_environment";
	public static String GIT_REPO_ENV_PROD_PR="applepay_production-pr_environment";
	public static String GIT_REPO_ENV_PROD_TJ="applepay_production-tj_environment";
	public static String GIT_REPO_ENV_PROD_SH="applepay_production-sh_environment";		
	public static String GIT_REPO_ENV_PROD_CN="applepay_cert-cn_environment";	

	
	public static String JKS_CERTIFICATE_CHAIN_LENGTH="Certificate chain length";
	 	 
	public static String APPLE_ROOT_CA_FINGER_PRINT="50:41:69:C1:76:A2:C3:0D:A2:E9:0E:A9:8A:53:5D:78:EF:42:F3:1A:90:FA:48:B6:CE:C2:45:A4:72:12:7A:D3";
	public static String GEOTRUST_ROOT_CA_FINGER_PRINT="FF:85:6A:2D:25:1D:CD:88:D3:66:56:F4:50:12:67:98:CF:AB:AA:DE:40:79:9C:72:2D:E4:D2:B5:DB:36:A7:3A";
	public static final long DAYS_TO_EXPIRED=180L;
	
	// Splunk Constants
	
	public static final String USER_NAME="username";
	public static final String PASSWORD="password";
	public static final String PERSONAL_USER_NAME="personal_username";
	public static final String PERSONAL_PASSWORD="personal_password";
	public static final String TARGETED_CERT_TYPE="targeted_cert_type";
	public static final String SOURCE_TYPE="source_type";
	public static final String ACCESS_TOKEN="access_token";
	public static final String CERT_MGR_BULK_API_TOKEN="cert_mgr_bulk_api_token";
	public static final String LOCAL_IP_ADDRESS="local_ip_address";
	public static final String SSH_KEY_PASSWORD="ssh_key_password";
	public static final String CURRENT_CERT_MANAGER_SYMBOL="current_cert_manager";
	public static final String SPLUNK_SERVER_URL="splunk_server_url";
	
	public static final String SPLUNK_CONF_PROPERTIES="marshall_conf.properties"; //"splunk_conf.properties";
	public static final String MARSHALL_CONF_PROPERTIES="marshall_conf.properties";
	public static final String SPLUNK_QUERY_LINES="splunk_query_lines_file.query";
	public static final String DESIRED_SEARCH_STRING_NO="desired_search_string_no";
	public static final String EARLIEST_TIME="earliest";
	
	/**
	 *  Rules  for environment Ankur: 4/6/2018 :
	 *  prod => 1. applepay_production-nc_environment
	 *          2. applepay_production-pr_environmnet
	 *  qa   => 1. applepay_qa_environment  
	 *          2. applepay_qa-nc_environment       
	 */
	public static final String APPLEPAY_PRODUCTION_NC_ENVIRONMENT="applepay_production-nc_environment";
	public static final String APPLEPAY_PRODUCTION_PR_ENVIRONMENT="applepay_production-pr_environment";
	public static final String GROUP_01 = "group01";
	public static final String GROUP_02 = "group02";
	public static final String APPLEPAY_CERT_US_ENVIRONMENT="applepay_cert_environment";
	public static final String APPLEPAY_STAGING_US_ENVIRONMENT="applepay_staging_environment";	
	
	public static final String APPLEPAY_QA_ENVIRONMENT="applepay_qa_environment";
	public static final String APPLEPAY_QA_NC_ENVIRONMENT="applepay_qa-nc_environment";
	
    //-----------------------Radar Constants ---------------------------
	
	 public static final String RWS_URL = "https://radar-webservices.apple.com";
	 public static final String ENVIRONMENT = "myacinfo";
	 public static final String ENVIRONMENT_AUTH_URL = "https://idmsa.apple.com/IDMSWebAuth/authenticate";
	 
	 //--------------------- Owner Certificate Category --------------------
	    /* apple_internal_cert --- O=Apple,Inc, DC=Certificate Manager
		 * apple_external_cert --- O=Apple,Inc, DC=null, 
		 * external_cert       --- O=Others, DC=null*/
	 
	 public static final String APPLE_INTERNAL_CERT ="apple_internal_cert";
	 public static final String APPLE_EXTERNAL_CERT ="apple_external_cert";
	 public static final String EXTERNAL_CERT ="external_cert";
	 
	 // ---------------_AES key -----------------------------------
	  public final static String DIGEST_MESSAGE_STRING = "sonicwall40%dsgsonicwall40345234214%ds12345678";

	  public final static String KEY_STRING="456f!@#!@#dgdg5756771bcbvhe!@@#!3442112123";
	  //-------------Environment Configure and Create Information --------------
	  public static final String CONFIGURED_CREATED="configured_created";
	  public static final String CONFIGURED_NOT_CREATED="configured_not_created";
	  public static final String NOT_CONFIGURED="not_configured";
	  
	  // ---------------environment.json ---------------------
	  public static final String ENVIRONMENT_JSON="environment.json";
	  
	  // --------------Title of Displaying Expiring Keystores----------
	  public static final String ALL_EXPIRING_KEYSTORES="All Exipiring Keystores that Splunk found ";
	  public static final String EXPIRING_APPLE_INTERNAL_KEYSTORES="Expiring Apple Internal Keystores that Splunk found ";
	  public static final String EXPIRING_CONFIGURED_KEYSTORES="Expiring Configured Keystores that Splunk found ";
	  public static final String CERT_MANAGER_API_GENERATED_REPORT="The Reports that Certificate Manager API Generated ";

}


