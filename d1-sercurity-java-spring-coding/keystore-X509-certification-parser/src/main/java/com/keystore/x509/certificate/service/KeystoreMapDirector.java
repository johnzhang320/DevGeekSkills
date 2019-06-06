package com.keystore.x509.certificate.service;

import java.util.List;

import com.keystore.x509.certificate.model.KSConfigureVO;
import com.keystore.x509.certificate.model.KeystoreMap;
import com.keystore.x509.certificate.model.KeystoreVO;
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 12/2017
 */
public class KeystoreMapDirector {
	private static KeystoreMap ksmap = new KeystoreMap();
	public static void builder(KSConfigureVO ksConfVO) {
		if (null==ksConfVO.getKeyStoreType()) {
			ksConfVO.setKeyStoreType("jks");
		}
		
		KeystoreBuilder ksb = KeystoreFactory.getKeystoreBuilder(ksConfVO);
		KeystoreVO ksvo = ksb.getKSOwner(ksConfVO);
		ksvo =ksb.getTrustCAs(ksvo);
		ksmap.put(ksvo.getKsDirectory(), ksvo);
	}
	public static KeystoreMap getKsmap() {
		return ksmap;
	}
	public static void setKsmap(KeystoreMap ksmap) {
		KeystoreMapDirector.ksmap = ksmap;
	}
	
}
