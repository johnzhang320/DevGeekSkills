package com.keystore.x509.certificate.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.keystore.x509.certificate.model.CAVO;
import com.keystore.x509.certificate.model.KSConfigureVO;
import com.keystore.x509.certificate.model.KeystoreVO;
import com.keystore.x509.certificate.service.KeystoreBuilder;
 
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 12/2017
 */
public class DERKeystoreAnalyzer implements KeystoreBuilder {
	public KeystoreVO getKSOwner(KSConfigureVO ksConfVO) {
		KeystoreVO vo = new KeystoreVO();
		return vo;
	}
	public KeystoreVO getKSOwner(String ksDir,String keystoreName, String storePassword, String keypassword) {
		KeystoreVO vo = new KeystoreVO();
		return vo;
	}
	public KeystoreVO getTrustCAs(KeystoreVO ksvo) {
		KeystoreVO results = ksvo;
		return results;
	}
}
