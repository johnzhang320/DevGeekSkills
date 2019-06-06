package com.keystore.x509.certificate.model;
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 2/2018
 */
public class JsonConfVO {
	private String environment;
	private String jsonSubFolder;
	private String ksSubFolder;
	private String jsonFileName;
	private String gitPrivateKey;
	
	
	 

	public JsonConfVO() {
		super();
	}



	public JsonConfVO(String environment, String jsonSubFolder, String ksSubFolder, String jsonFileName) {
		super();
		this.environment = environment;
		this.jsonSubFolder = jsonSubFolder;
		this.ksSubFolder = ksSubFolder;
		this.jsonFileName = jsonFileName;
	}





	public JsonConfVO(String environment, String jsonSubFolder, String ksSubFolder, String jsonFileName,
			String gitPrivateKey) {
		super();
		this.environment = environment;
		this.jsonSubFolder = jsonSubFolder;
		this.ksSubFolder = ksSubFolder;
		this.jsonFileName = jsonFileName;
		this.gitPrivateKey = gitPrivateKey;
	}



	public String getEnvironment() {
		return environment;
	}



	public void setEnvironment(String environment) {
		this.environment = environment;
	}



	public String getJsonSubFolder() {
		return jsonSubFolder;
	}



	public void setJsonSubFolder(String jsonSubFolder) {
		this.jsonSubFolder = jsonSubFolder;
	}



	public String getKsSubFolder() {
		return ksSubFolder;
	}



	public void setKsSubFolder(String ksSubFolder) {
		this.ksSubFolder = ksSubFolder;
	}



	public String getJsonFileName() {
		return jsonFileName;
	}



	public void setJsonFileName(String jsonFileName) {
		this.jsonFileName = jsonFileName;
	}



	public String getGitPrivateKey() {
		return gitPrivateKey;
	}



	public void setGitPrivateKey(String gitPrivateKey) {
		this.gitPrivateKey = gitPrivateKey;
	}



	@Override
	public String toString() {
		return "JsonConfVO [environment=" + environment + ", jsonSubFolder=" + jsonSubFolder + ", ksSubFolder="
				+ ksSubFolder + ", jsonFileName=" + jsonFileName + ", gitPrivateKey=" + gitPrivateKey + "]";
	}
	 
	 
	 
	
	
}
