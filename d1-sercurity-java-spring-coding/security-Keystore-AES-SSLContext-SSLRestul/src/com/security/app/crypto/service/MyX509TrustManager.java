package com.security.app.crypto.service;
import javax.net.ssl.*;
import org.apache.logging.log4j.Logger;
import com.mailfrontier.msgcenter.app.log.Logger;
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
 *  Modified by jzhang 
 *  Modified Date: 3/17/2015
 */
public class MyX509TrustManager implements X509TrustManager {
	 public static Logger Log =Logger.getLogger(MyX509TrustManager.class.getName());
     
    X509TrustManager pkixTrustManager;

    public MyX509TrustManager(String trustStore, char[] password) throws Exception {
        this(new File(trustStore), password);
    }

    public MyX509TrustManager(File trustStore, char[] password) throws Exception {
        // create a "default" JSSE X509TrustManager.

        KeyStore ks = KeyStore.getInstance("JKS");

        ks.load(new FileInputStream(trustStore), password);

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("PKIX");
        tmf.init(ks);

        TrustManager tms[] = tmf.getTrustManagers();

        /*
         * Iterate over the returned trustmanagers, look
         * for an instance of X509TrustManager.  If found,
         * use that as our "default" trust manager.
         */
        for (int i = 0; i < tms.length; i++) {
            if (tms[i] instanceof X509TrustManager) {
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
