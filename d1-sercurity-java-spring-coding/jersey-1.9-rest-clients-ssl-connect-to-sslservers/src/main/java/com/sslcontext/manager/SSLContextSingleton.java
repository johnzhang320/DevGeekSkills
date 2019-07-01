package com.sslcontext.manager;

import javax.net.ssl.SSLContext;

public class SSLContextSingleton {
	private static SSLContextSingleton handler=null;
	private static SSLContext sslContext=null;
	public static SSLContextSingleton getInstance() {
		if (handler==null) {
			handler = new SSLContextSingleton();  
		}
		return handler;
	}
	public SSLContext getSSLContext(SSLContextBuilder b) {
		
		if (sslContext==null) {
			SSLContextManager sslContextManager= new SSLContextManager(b.getTrustStorePath(),b.getTrustPassword(),
								                b.getKeyStorePath(),b.getKeyPassword());
			sslContext=sslContextManager.getSSLContext();
		}
		return sslContext;
	}
	
}
