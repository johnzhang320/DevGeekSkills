package com.keystore.truststore.manager;
import javax.net.ssl.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
/**
 *  MyX509TrustManager 
 *  If found Trust Store file path and password, load Trust Store, create JSSE X509TrustManager from 
 *  TrustManagerFactory.getInstance("PKIX")  
 *  Originated from public website: 
 *  http://stackoverflow.com/questions/3434309/accessing-secure-restful-web-services-using-jersey-client
 *  Modified by john.zhang320@gmail.com
 *  Modified Date: 01/17/2018
 */
public class MyX509TrustManager implements X509TrustManager {
	 public static Logger Log =Logger.getLogger(MyX509TrustManager.class.getName());
     
    X509TrustManager pkixTrustManager;

    public MyX509TrustManager(String trustStore, char[] password) throws Exception {
        this(new File(trustStore), password);
    }

    public MyX509TrustManager(File trustStore, char[] password) throws Exception {
        // create a "default" JSSE X509TrustManager.
    	Log.debug("Begin");
        KeyStore ks = KeyStore.getInstance("JKS");
        Log.debug("Trust Step 1 Create JKS truststore  " );
        ks.load(new FileInputStream(trustStore), password);
        
        Log.debug("Trust Step 2 loaded  truststore !");

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("PKIX");
        
        tmf.init(ks);
        Log.debug("Trust Step 3 Initialized  truststore !");

        TrustManager tms[] = tmf.getTrustManagers();

        /*
         * Iterate over the returned trustmanagers, look
         * for an instance of X509TrustManager.  If found,
         * use that as our "default" trust manager.
         */
        for (int i = 0; i < tms.length; i++) {
            if (tms[i] instanceof X509TrustManager) {
            	Log.debug("Trust Step 4  find: "+tms[i].toString());
                pkixTrustManager = (X509TrustManager) tms[i];
                return;
            }
        }

        /*
         * Find some other way to initialize, or else we have to fail the
         * constructor.
         */
        throw new Exception("Couldn't initialize");
    }

    /*
     * Delegate to the default trust manager.
     */
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        try {
            pkixTrustManager.checkClientTrusted(chain, authType);
        } catch (CertificateException excep) {
        	Log.error("CheckClientTrusted Failed "+excep);
        	throw new CertificateException("CheckClientTrusted Failed ");
            // do any special handling here, or rethrow exception.
        }
    }

    /*
     * Delegate to the default trust manager.
     */
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        try {
            pkixTrustManager.checkServerTrusted(chain, authType);
        } catch (CertificateException excep) {
            /*
             * Possibly pop up a dialog box asking whether to trust the
             * cert chain.
             */
        	Log.error("CheckServerTrusted Failed "+excep);
        	throw new CertificateException("CheckServerTrusted Failed ");
        }
    }

    /*
     * Merely pass this through.
     */
    public X509Certificate[] getAcceptedIssuers() {
        return pkixTrustManager.getAcceptedIssuers();
    }
}
