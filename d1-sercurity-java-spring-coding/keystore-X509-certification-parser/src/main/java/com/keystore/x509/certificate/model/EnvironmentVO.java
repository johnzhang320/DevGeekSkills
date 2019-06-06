package com.keystore.x509.certificate.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 2/2018
 */
/**
    	"environment":"applypay_qa_environment", 
   		"configure_parent_folder":"databags",
   		"keystore_parent_folder":"keystores"
   		"working_subfolders":["applepay_qa1","applepay_qa2","applepay_qa3"]

 */
public class EnvironmentVO {

	private String environment;         
	private String environment_symbol;  
	private String splunk_link;  
	private String configure_parent_folder;
	private String keystore_parent_folder;
	private String git_clone_url;
	private String gpg_priv_keyfile;
	private List<String> working_subfolders;
	
	
	public EnvironmentVO() {
		super();
	}

	public EnvironmentVO(String environment, String configure_parent_folder, String keystore_parent_folder,
			List<String> working_subfolders) {
		super();
		this.environment = environment;
		this.configure_parent_folder = configure_parent_folder;
		this.keystore_parent_folder = keystore_parent_folder;
		this.working_subfolders = working_subfolders;
	}
	
	public EnvironmentVO(String environment, String configure_parent_folder, String keystore_parent_folder,
			String git_clone_url, String gpg_priv_keyfile, List<String> working_subfolders) {
		super();
		this.environment = environment;
		this.configure_parent_folder = configure_parent_folder;
		this.keystore_parent_folder = keystore_parent_folder;
		this.git_clone_url = git_clone_url;
		this.gpg_priv_keyfile = gpg_priv_keyfile;
		this.working_subfolders = working_subfolders;
	}

	public EnvironmentVO(String environment, String environment_symbol, String splunk_link,
			String configure_parent_folder, String keystore_parent_folder, String git_clone_url,
			String gpg_priv_keyfile, List<String> working_subfolders) {
		super();
		this.environment = environment;
		this.environment_symbol = environment_symbol;
		this.splunk_link = splunk_link;
		this.configure_parent_folder = configure_parent_folder;
		this.keystore_parent_folder = keystore_parent_folder;
		this.git_clone_url = git_clone_url;
		this.gpg_priv_keyfile = gpg_priv_keyfile;
		this.working_subfolders = working_subfolders;
	}

	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	public String getEnvironment_symbol() {
		return environment_symbol;
	}

	public void setEnvironment_symbol(String environment_symbol) {
		this.environment_symbol = environment_symbol;
	}

	public String getSplunk_link() {
		return splunk_link;
	}

	public void setSplunk_link(String splunk_link) {
		this.splunk_link = splunk_link;
	}

	public String getConfigure_parent_folder() {
		return configure_parent_folder;
	}
	public void setConfigure_parent_folder(String configure_parent_folder) {
		this.configure_parent_folder = configure_parent_folder;
	}
	public String getKeystore_parent_folder() {
		return keystore_parent_folder;
	}
	public void setKeystore_parent_folder(String keystore_parent_folder) {
		this.keystore_parent_folder = keystore_parent_folder;
	}
	public List<String> getWorking_subfolders() {
		return working_subfolders;
	}
	public void setWorking_subfolders(List<String> working_subfolders) {
		this.working_subfolders = working_subfolders;
	}
	


	public String getGit_clone_url() {
		return git_clone_url;
	}

	public void setGit_clone_url(String git_clone_url) {
		this.git_clone_url = git_clone_url;
	}

	public String getGpg_priv_keyfile() {
		return gpg_priv_keyfile;
	}

	public void setGpg_priv_keyfile(String gpg_priv_keyfile) {
		this.gpg_priv_keyfile = gpg_priv_keyfile;
	}

	@Override
	public String toString() {
		return "EnvironmentVO [environment=" + environment + ", environment_symbol=" + environment_symbol
				+ ", splunk_link=" + splunk_link + ", configure_parent_folder=" + configure_parent_folder
				+ ", keystore_parent_folder=" + keystore_parent_folder + ", git_clone_url=" + git_clone_url
				+ ", gpg_priv_keyfile=" + gpg_priv_keyfile + ", working_subfolders=" + working_subfolders + "]";
	}

}
