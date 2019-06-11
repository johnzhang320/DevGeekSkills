package com.keystore.truststore.manager;
import javax.net.ssl.*;
import org.apache.log4j.Logger;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
/**
 * EmptyMyX509TrustManager
 *  
 *  If trust manager file is not available, typically self-signed, implements interface X509TrustManager with empty methods  
 *  Originated from public website: 
 *  http://www.bhaveshthaker.com/24/calling-invoking-secure-restful-web-service-over-https-with-jax-rs-in-java-without-keystore-truststore-information/
 *  Modified by john.zhang320@gmail.com
 *  Modified Date: 06/06/2019
 */ 
  
public class EmptyX509TrustManager implements X509TrustManager {
	 public static Logger Log =Logger.getLogger(EmptyX509TrustManager.class.getName());
	    public EmptyX509TrustManager() {}
		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}

		public boolean isClientTrusted(X509Certificate[] arg0) {
			return true;
		}

		public boolean isServerTrusted(X509Certificate[] arg0) {
			return true;
		}
}
