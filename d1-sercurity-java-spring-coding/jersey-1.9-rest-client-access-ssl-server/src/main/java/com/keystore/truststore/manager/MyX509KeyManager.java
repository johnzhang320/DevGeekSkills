package com.keystore.truststore.manager;
 
import java.io.*;
import java.net.Socket;
import java.security.*;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509KeyManager;
import org.apache.log4j.Logger;

/**
 *  MyX509KeyManager
 *  If found keyStore path and password, load KeyStore, create JSSE X509KeyManager from 
 *  KeyManagerFactory.getInstance("SunX509", "SunJSSE") and return KeyManager array 
 *  Originated from public website: 
 *  http://stackoverflow.com/questions/3434309/accessing-secure-restful-web-services-using-jersey-client
 *  Modified by john.zhang320@gmail.com
 *  Modified Date: 06/06/2019
 */
public class MyX509KeyManager implements X509KeyManager {
	public static Logger Log =Logger.getLogger(MyX509KeyManager.class.getName());    
    private X509KeyManager pkixKeyManager;
    private KeyManager keyManager[] = null;
   
   /**
     * Constructor: MyX509KeyManager
     * @param keyStore --  key store File handler
     * @param password --  key store password
     * @throws Exception
     */
    public MyX509KeyManager(File keyStore, char[] password, String keyStoreType) throws Exception {
        /**
         *  create a "default" JSSE X509KeyManager.
         */
	    Log.debug("try to create Key Store Instance -- ks step 1 ");
	    
        KeyStore ks = KeyStore.getInstance("JKS");
        Log.debug("try to ks.load keyStore file:"+keyStore.getAbsolutePath()+" step 2");
        FileInputStream fs =null;
        if (!keyStore.exists()) {
        	Log.error("Fail to find keyStore file:"+keyStore.getAbsolutePath()+" step 3");
        	return;
        } else {
        	fs = new FileInputStream(keyStore);
        }
        ks.load(fs, password);
        Log.debug("try to create KeyManagerFactory kmf step 3");
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
        Log.debug("try to create KeyManagerFactory kmf step 4");
        kmf.init(ks, password);
	 	Log.debug("try to getKeyManagers step 5");
        keyManager = kmf.getKeyManagers();
	    Log.debug("try to gpkixKeyManager step 6");
        /**
         * Iterate over the returned keymanagers, look
         * for an instance of X509KeyManager.  If found,
         * use that as our "default" key manager.
         */
        for (int i = 0; i < keyManager.length; i++) {
            if (keyManager[i] instanceof X509KeyManager) {
                pkixKeyManager = (X509KeyManager) keyManager[i];
                 Log.debug("found gpkixKeyManager return step 7");
                return;
            }
        }

        /**
         * Find some other way to initialize, or else we have to fail the
         * constructor.
         */
        Log.error("Initializing MyX509KeyManager failed !");
        throw new Exception("Initializing MyX509KeyManager failed !");
    }

    public PrivateKey getPrivateKey(String arg0) {
        return pkixKeyManager.getPrivateKey(arg0);
    }

    public X509Certificate[] getCertificateChain(String arg0) {
        return pkixKeyManager.getCertificateChain(arg0);
    }

    public String[] getClientAliases(String arg0, Principal[] arg1) {
        return pkixKeyManager.getClientAliases(arg0, arg1);
    }

    public String chooseClientAlias(String[] arg0, Principal[] arg1, Socket arg2) {
        return pkixKeyManager.chooseClientAlias(arg0, arg1, arg2);
    }

    public String[] getServerAliases(String arg0, Principal[] arg1) {
        return pkixKeyManager.getServerAliases(arg0, arg1);
    }

    public String chooseServerAlias(String arg0, Principal[] arg1, Socket arg2) {
        return pkixKeyManager.chooseServerAlias(arg0, arg1, arg2);
    }

	public KeyManager[] getKeyManager() {
		return keyManager;
	}

	public void setKeyManager(KeyManager[] keyManager) {
		this.keyManager = keyManager;
	}



}
