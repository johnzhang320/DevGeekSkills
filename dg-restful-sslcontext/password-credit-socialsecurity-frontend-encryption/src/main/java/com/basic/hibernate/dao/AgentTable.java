package com.basic.hibernate.dao;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * AgentTable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agent_table", catalog = "rua_receiverdb", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email_address"),
		@UniqueConstraint(columnNames = "full_name") })
public class AgentTable implements java.io.Serializable {
	private static final long serialVersionUID=01L;
	// Fields

	private Integer agentId;
	private String address;
	private String cellPhone;
	private String city;
	private String companyName;
	private String decryptedPassword;
	private String description;
	private String encryptedPassword;
	private String fullName;
	private String gender;
	private String hashedPassword;
	private String ipAddress;
	private String ipAddressRange;
	private String ipAddressRangeSample;
	private Date joiningDate;
	private String password;
	private String state;
	private String workPhone;
	private String zipCode;
	private String publicKey;
	private String emailAddress;
	private String creditNumber;
	private String cardHolderName;
	private String expiringDate;
	private String securityCode;
 
	private String encryptedCredit;
	private String decryptedCredit;
	private String encryptedSecurityCode;
	private String decryptedSecurityCode;
	private String encryptedSSO;
	private String decryptedSSO;
	 
	 
	
	private String socialSecurity;

	// Constructors

	/** default constructor */
	public AgentTable() {
	}

	/** minimal constructor */
	public AgentTable(String fullName, String password) {
		this.fullName = fullName;
		this.password = password;
	}

	/** full constructor */
	public AgentTable(String address, String cellPhone, String city,
			String companyName, String decryptedPassword, String description,
			String encryptedPassword, String fullName, String gender,
			String hashedPassword, String ipAddress, String ipAddressRange,
			String ipAddressRangeSample, Date joiningDate, String password,
			String state, String workPhone, String zipCode, String publicKey,
			String emailAddress) {
		this.address = address;
		this.cellPhone = cellPhone;
		this.city = city;
		this.companyName = companyName;
		this.decryptedPassword = decryptedPassword;
		this.description = description;
		this.encryptedPassword = encryptedPassword;
		this.fullName = fullName;
		this.gender = gender;
		this.hashedPassword = hashedPassword;
		this.ipAddress = ipAddress;
		this.ipAddressRange = ipAddressRange;
		this.ipAddressRangeSample = ipAddressRangeSample;
		this.joiningDate = joiningDate;
		this.password = password;
		this.state = state;
		this.workPhone = workPhone;
		this.zipCode = zipCode;
		this.publicKey = publicKey;
		this.emailAddress = emailAddress;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "agentId", unique = true, nullable = false)
	public Integer getAgentId() {
		return this.agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "cell_phone")
	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	@Column(name = "city")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "company_name")
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "decrypted_password")
	public String getDecryptedPassword() {
		return this.decryptedPassword;
	}

	public void setDecryptedPassword(String decryptedPassword) {
		this.decryptedPassword = decryptedPassword;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "encrypted_password")
	public String getEncryptedPassword() {
		return this.encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	@Column(name = "full_name", unique = true, nullable = false)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "gender")
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "hashed_password")
	public String getHashedPassword() {
		return this.hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	@Column(name = "ip_address")
	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name = "ip_address_range")
	public String getIpAddressRange() {
		return this.ipAddressRange;
	}

	public void setIpAddressRange(String ipAddressRange) {
		this.ipAddressRange = ipAddressRange;
	}

	@Column(name = "ip_address_range_sample")
	public String getIpAddressRangeSample() {
		return this.ipAddressRangeSample;
	}

	public void setIpAddressRangeSample(String ipAddressRangeSample) {
		this.ipAddressRangeSample = ipAddressRangeSample;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "JOINING_DATE", length = 10)
	public Date getJoiningDate() {
		return this.joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "work_phone")
	public String getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	@Column(name = "zipCode")
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "public_key", length = 2000)
	public String getPublicKey() {
		return this.publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	@Column(name = "email_address", unique = true)
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCreditNumber() {
		return creditNumber;
	}

	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getExpiringDate() {
		return expiringDate;
	}

	public void setExpiringDate(String expiringDate) {
		this.expiringDate = expiringDate;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getEncryptedCredit() {
		return encryptedCredit;
	}

	public void setEncryptedCredit(String encryptedCredit) {
		this.encryptedCredit = encryptedCredit;
	}

	public String getDecryptedCredit() {
		return decryptedCredit;
	}

	public void setDecryptedCredit(String decryptedCredit) {
		this.decryptedCredit = decryptedCredit;
	}

	public String getEncryptedSecurityCode() {
		return encryptedSecurityCode;
	}

	public void setEncryptedSecurityCode(String encryptedSecurityCode) {
		this.encryptedSecurityCode = encryptedSecurityCode;
	}

	public String getDecryptedSecurityCode() {
		return decryptedSecurityCode;
	}

	public void setDecryptedSecurityCode(String decryptedSecurityCode) {
		this.decryptedSecurityCode = decryptedSecurityCode;
	}

	public String getEncryptedSSO() {
		return encryptedSSO;
	}

	public void setEncryptedSSO(String encryptedSSO) {
		this.encryptedSSO = encryptedSSO;
	}

	public String getDecryptedSSO() {
		return decryptedSSO;
	}

	public void setDecryptedSSO(String decryptedSSO) {
		this.decryptedSSO = decryptedSSO;
	}

}