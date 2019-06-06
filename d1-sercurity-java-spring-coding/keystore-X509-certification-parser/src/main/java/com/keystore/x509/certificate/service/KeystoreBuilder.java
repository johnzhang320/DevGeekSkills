package com.keystore.x509.certificate.service;
import java.util.List;

import com.keystore.x509.certificate.model.CAVO;
import com.keystore.x509.certificate.model.KSConfigureVO;
import com.keystore.x509.certificate.model.KeystoreVO;
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 12/2017
 */
public interface KeystoreBuilder {
	public KeystoreVO getKSOwner(String ksDir,String keystoreName, String storePassword, String keypassword);
     
	public KeystoreVO getKSOwner(KSConfigureVO ksConfVO);
	public KeystoreVO getTrustCAs(KeystoreVO ksvo);
}
