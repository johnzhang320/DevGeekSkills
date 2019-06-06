package com.keystore.x509.certificate.model;
/**
 * 
 * @author john_zhang3@apple.com
 * Created Date: 01/2017
 *
 */
public class CAVO {
	private String alias;
	private String CN;
	private String CAUrl;
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getCN() {
		return CN;
	}
	public void setCN(String cN) {
		CN = cN;
	}
	public String getCAUrl() {
		return CAUrl;
	}
	public void setCAUrl(String cAUrl) {
		CAUrl = cAUrl;
	}
	@Override
	public String toString() {
		return "CAVO [alias=" + alias + ", CN=" + CN + ", CAUrl=" + CAUrl + "]";
	}
	
}
