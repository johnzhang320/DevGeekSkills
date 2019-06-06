package com.keystore.x509.certificate.service;
import com.keystore.x509.certificate.model.KSConfigureVO;
import com.keystore.x509.certificate.service.impl.DERKeystoreAnalyzer;
import com.keystore.x509.certificate.service.impl.JKSP12KeystoreAnalyzer;
import com.keystore.x509.certificate.service.impl.PEMKeystoreAnalyzer;

/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 12/2017
 */
public class KeystoreFactory {
	private static KeystoreBuilder jksp12 =  new JKSP12KeystoreAnalyzer();
	private static KeystoreBuilder pem =  new PEMKeystoreAnalyzer();
	private static KeystoreBuilder der =  new DERKeystoreAnalyzer();
	 
	public static KeystoreBuilder getKeystoreBuilder(KSConfigureVO ksConfVo) {
		KeystoreBuilder retval=null;
		String ksType=ksConfVo.getKeyStoreType();
		
		if (null==ksType || "no".equalsIgnoreCase(ksType) ) {
			ksType="jks";
		}
		ksType =ksType.toLowerCase();
		if (ksType.equalsIgnoreCase("jks") || ksType.equalsIgnoreCase("p12") || ksType.contains("pkcs")) {
			retval = jksp12;
		}
		if (ksType.equalsIgnoreCase("pem") || ksType.contains("pem")) {
			retval = pem;
		}
		if (ksType.equalsIgnoreCase("der") || ksType.contains("der")) {
			retval = der;
		}
		return retval;
	}
}
