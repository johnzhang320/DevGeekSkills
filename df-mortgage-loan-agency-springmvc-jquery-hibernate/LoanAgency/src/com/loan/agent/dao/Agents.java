package com.loan.agent.dao;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.lang.Byte;

/**
 * Agents entity. @author MyEclipse Persistence Tools
 */

public class Agents implements java.io.Serializable {

	// Fields

	private Integer agentId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;
	private String companyName;
	private String workPhone;
	private String cellPhone;
	private Blob pictureContent;
	private String pictureFilename=null;
	private String applicationFormFilename=null;
	private String pictureType=null;
	private String appFormType=null;
	private String gendar;
	private String address;
	private String state;
	private String zipCode;
	private String description;
	private String licenseNo;
	private String dreNo;
	private String nmlsNo;
	private String licenseEligibleState;
	private Timestamp modifiedDate;
	private String city;
	private String nicheIntro;
	
	//private Set quotes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Agents() {
	}

	/** full constructor */
	public Agents(String firstName, String lastName, String emailAddress,
			String password, String companyName, String workPhone,
			String cellPhone, Blob pictureContent, String pictureFilename, String applicationFormFilename,
			String pictureType,String appFormType, String gendar, String address, String state,
			String zipCode, String description, String licenseNo,String dreNo,String nmlsNo,
			String licenseEligibleState, Timestamp modifiedDate, String city,String nicheIntro
			/*Set quotes*/) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.companyName = companyName;
		this.workPhone = workPhone;
		this.cellPhone = cellPhone;
		this.pictureContent = pictureContent;
		this.pictureFilename = pictureFilename;
		this.applicationFormFilename = applicationFormFilename;
		this.pictureType = pictureType;
		this.appFormType = appFormType;
		this.gendar = gendar;
		this.address = address;
		this.state = state;
		this.zipCode = zipCode;
		this.description = description;
		this.licenseNo = licenseNo;
		this.dreNo = dreNo;
		this.nmlsNo = nmlsNo;
		this.licenseEligibleState = licenseEligibleState;
		this.modifiedDate = modifiedDate;
		this.city = city;
		this.nicheIntro = nicheIntro;
	//	this.quotes = quotes;
	}

	// Property accessors

	public Integer getAgentId() {
		return this.agentId;
	}

	public String getNicheIntro() {
		return nicheIntro;
	}

	public void setNicheIntro(String nicheIntro) {
		this.nicheIntro = nicheIntro;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	 

	public Blob getPictureContent() {
		return pictureContent;
	}

	public void setPictureContent(Blob pictureContent) {
		this.pictureContent = pictureContent;
	}

	public String getPictureFilename() {
		return this.pictureFilename;
	}

	public String getApplicationFormFilename() {
		return applicationFormFilename;
	}

	public void setApplicationFormFilename(String applicationFormFilename) {
		this.applicationFormFilename = applicationFormFilename;
	}

	public void setPictureFilename(String pictureFilename) {
		this.pictureFilename = pictureFilename;
	}

	public String getPictureType() {
		return this.pictureType;
	}

	public void setPictureType(String pictureType) {
		this.pictureType = pictureType;
	}

	public String getAppFormType() {
		return appFormType;
	}

	public void setAppFormType(String appFormType) {
		this.appFormType = appFormType;
	}

	public String getGendar() {
		return this.gendar;
	}

	public void setGendar(String gendar) {
		this.gendar = gendar;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLicenseNo() {
		return this.licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getLicenseEligibleState() {
		return this.licenseEligibleState;
	}

	public void setLicenseEligibleState(String licenseEligibleState) {
		this.licenseEligibleState = licenseEligibleState;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDreNo() {
		return dreNo;
	}

	public void setDreNo(String dreNo) {
		this.dreNo = dreNo;
	}

	public String getNmlsNo() {
		return nmlsNo;
	}

	public void setNmlsNo(String nmlsNo) {
		this.nmlsNo = nmlsNo;
	}

	 

}