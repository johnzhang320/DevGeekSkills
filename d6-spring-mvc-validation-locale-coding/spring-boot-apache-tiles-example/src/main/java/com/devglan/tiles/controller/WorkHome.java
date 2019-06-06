package com.devglan.tiles.controller;
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 1/29/2018
 */
import java.io.File;

 
// All data below this class are not changed at runtime after code deployed, that's why we apply static and final
// the properties need to change at runtime goes through properties files 
public class WorkHome {
	public final static String CURRENT_CERT_MANAGER ="dev";    // production put "", development, put "dev", test , put test
 	
	
	
	private final static String HOME_MAIN_DIR="certRenewMgr";
	public final static String USER_HOME= System.getProperty("user.home")+File.separator;
	//public final static String WORK_HOME=USER_HOME+HOME_MAIN_DIR+File.separator;	
	public final static String CLASSES_HOME=System.getProperty("user.dir")+File.separator+"target/classes"+File.separator;
	public final static String WORK_HOME=rootDirectory().getAbsolutePath()+File.separator;
	
	/**
	 *  The directories under WORK_HOME
	 */
	public final static String BIN_HOME=WORK_HOME+"bin"+File.separator;
	public final static String DATA_HOME=WORK_HOME+"data"+File.separator;
	public final static String CONF_HOME=WORK_HOME+"conf"+File.separator;
	public final static String TESTS_HOME=WORK_HOME+"tests"+File.separator;
	public final static String COMMON_LOG_HOME=WORK_HOME+"commonlog"+File.separator;
	
	//Path to Fetch GIT Script Files
	public final static String GIT_FETCH_SHELL_FILE_PATH = BIN_HOME + "fetch_git.sh";
	public final static String GIT_COMMIT_SHELL_FILE_PATH = BIN_HOME + "git_commit.sh";	
	
	public final static String GIT_SYSTEM_HOME="/usr/bin/";
	public final static String GIT_CRYPT_SYSTEM_HOME="/usr/local/bin/";

	/**
	 *  sub directories under data home
	 */
	public final static String DATA_CERT_MANAGER_HOME=DATA_HOME+"certmanager"+File.separator;
	public final static String DATA_GPGREPOS_HOME=DATA_HOME+"gpgrepos"+File.separator;
	public final static String DATA_MAPDICTIONARY_HOME=DATA_HOME+"mapdictionary"+File.separator;
	public final static String DATA_GPGARCHIVE_HOME=DATA_HOME+"gpgarchive"+File.separator;
	public final static String DATA_GPGARCHIVE_CERTCONTENTS_HOME=DATA_HOME+"gpgarchive"+File.separator+"certcontents"+File.separator;
	
	public final static String DATA_SPLUNK_HOME=DATA_HOME+"splunk"+File.separator;
	public final static String DATA_SPLUNK_RESULTS=DATA_SPLUNK_HOME+"results"+File.separator;
	public final static String DATA_NEW_CERTS_HOME=DATA_HOME+"newCerts"+File.separator;

	
	/**
	 *  sub directories under tests
	 */
	
	public final static String TESTS_SSL_HOME=TESTS_HOME+"ssl"+File.separator;
	public final static String TESTS_GPG_RETRIEVE_HOME=TESTS_HOME+"gpgretrieve"+File.separator;
	public final static String TESTS_SPLUNK_RETRIEVE_HOME=TESTS_HOME+"splunkretrieve"+File.separator;

	/**
	 *  sub directories under conf
	 */
	 
	public final static String CONF_CERT_MANAGER_HOME=CONF_HOME+"certmanager"+File.separator;
	public final static String CONF_GPGREPOS_HOME=CONF_HOME+"gpgrepos"+File.separator;
	public final static String CONF_MAPDICTIONARY_HOME=CONF_HOME+"mapdictionary"+File.separator;

	public final static String CONF_SPLUNK_HOME=CONF_HOME+"splunk"+File.separator;
	

	/**
	 *  Client Certificate for SSL connection
	 */
	public final static String SSL_CLIENT_CERT_WORK_DIR=WORK_HOME+"ssl/CertManagerDev"+File.separator;
	public final static String SSL_CLIENT_CERT_BUILD_DIR=WORK_HOME+"ssl/CertManagerDev/ShellScript"+File.separator;
	public final static String SSL_CLIENT_CERT_ARCHIVE_DIR=WORK_HOME+"ssl/CertManagerDev/Archive"+File.separator;
	
	/**
	 *  Keystore and Truststore for client to establish SSL Conection
	 */
	//public static String CURRENT_CERT_MANAGER =  FlatTextProperty.getInstance().getCurrent_cert_manager();


	
//	public static String JKS_TRUSTSTORE_PATH=SSL_CLIENT_CERT_WORK_DIR+"trustCMChain.jks";
	public static String TRUSTSTORE_PASSWORD="abc1234";
//	public static String JKS_KEYSTORE_PATH=SSL_CLIENT_CERT_BUILD_DIR +"keystoreClient.jks";
	public static String KEYSTORE_PASSWORD="abc1234";
	
	public static String RENEW_BUILD_HOME=WORK_HOME+"data/renewbuild"+File.separator; 
	
	public static String TEXT_USER_CREDENTIAL_FILE=CONF_CERT_MANAGER_HOME+"user_credential.properties";

	public static String ENCRYPTED_USER_CREDENTIAL_FILE=CONF_CERT_MANAGER_HOME+"user_credential_enc.properties";		

	public static String MY_SEEDS_FILE=CONF_MAPDICTIONARY_HOME+"myseeds.dat";

	  //  ----------------Progress Bar URL ---------------------
	 public static final String PROGRESS_BASE_URL="https://localhost:8443/"; 
	 //public static final String PROGRESS_BASE_URL="http://localhost:8081/"; 
	 
	 // -----------------Root Directory from ETC_DIR -----------------------
	 /** Attempt to resolve the root resource directory */
	    public static final File rootDirectory() {
	        if (!Utils.isEmptyStr(System.getenv("ETC_DIR"))) {
	            return new File(System.getenv("ETC_DIR"));
	        }
	        if (!Utils.isEmptyStr(System.getProperty("ETC_DIR"))) {
	            return new File(System.getProperty("ETC_DIR"));
	        }
	        if (!Utils.isEmptyStr(System.getProperty("user.home"))) {
	            return new File(System.getProperty("user.home"), HOME_MAIN_DIR);
	        }

	        throw new RuntimeException("Unable to determine rootDirectory() - neither ETC_DIR environment property nor user.home was specified");
	    }
}
