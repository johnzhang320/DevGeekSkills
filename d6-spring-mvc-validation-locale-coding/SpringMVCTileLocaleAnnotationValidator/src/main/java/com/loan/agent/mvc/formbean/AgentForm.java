package com.loan.agent.mvc.formbean;

import java.io.Serializable;
import java.sql.Timestamp;
import org.apache.log4j.Logger; 
import com.loan.agent.dao.Agents;
import java.sql.Blob;
import java.util.Date;

public class AgentTable implements Serializable {
	 private static final long serialVersionUID = 1L;
	public static final Logger Log = Logger.getLogger(AgentTable.class);		
	/**
	 *  In SpringMVCValidation project we only use below fields
	 */
	private String agentTableAction;
	private Integer agentId=null;
	private Integer age;
	private String firstName=null;
	private String lastName=null;
	private String emailAddress=null;
	private String username=null;
	private String password=null;
	private String confirmPassword=null;
	private String companyName=null;
	private String workPhone=null;
	private String cellPhone=null;
	 
	/**
	 *  In SpringMVCValidation project we only use above fields
	 */
	private String gendar="";//null;
	private String address=null;
	private String city=null;	
	private String state=null;
	private String zipCode=null;
	private String description=null;
	private String licenseNo=null;
	private String dreNo=null;
	private String nmlsNo=null;
	private String licenseEligibleState[]=null;
	private Timestamp modifiedDate=null;
	/**
	 *  Must define the byte[] data type for file
	 */
	private byte[] pictureContent;
	
	private String pictureType;//null;
	private String appFormType;
	 
	private String pictureFilename; 
	private String applicationFormFilename;
	
	
	
	public void renderAgents(Agents a) {
		
		//doc =new FileDocument(a.getPictureFilename(),a.getPictureType(),a.getPictureContent());
		this.firstName = a.getFirstName();
		this.lastName = a.getLastName();
		this.emailAddress = a.getEmailAddress();
		this.password = a.getPassword();
		this.companyName = a.getCompanyName();
		this.workPhone = a.getWorkPhone();
		this.cellPhone = a.getCellPhone();
		this.pictureType = a.getPictureType();
		this.appFormType = a.getAppFormType();
		//this.pictureContent = a.getPictureContent();
		this.pictureFilename = a.getPictureFilename();
		this.applicationFormFilename = a.getApplicationFormFilename();
		this.gendar = a.getGendar();
		this.address = a.getAddress();
		this.city = a.getCity();
		this.state = a.getState();
		this.zipCode = a.getZipCode();
		this.description = a.getDescription();
		this.licenseNo = a.getLicenseNo();
		this.dreNo = a.getDreNo();
		this.nmlsNo = a.getNmlsNo();	 
		
		this.modifiedDate = a.getModifiedDate();
		//return doc;
	}
	
	public Agents modelAgents() {
		Agents vo = new Agents();
		vo.setFirstName(this.firstName);
		vo.setLastName(this.lastName);
		vo.setEmailAddress(this.emailAddress);
		vo.setPassword(getPassword());		    
		vo.setCompanyName(this.companyName);
		vo.setWorkPhone(this.workPhone);
		vo.setCellPhone(this.cellPhone);
		vo.setPictureType(this.pictureType);
		vo.setAppFormType(this.appFormType);
		vo.setPictureFilename(this.pictureFilename);
		vo.setApplicationFormFilename(this.applicationFormFilename);
		vo.setGendar(this.gendar);
		vo.setAddress(this.address);
		vo.setCity(this.city);
		vo.setState(this.state);
		vo.setZipCode(this.zipCode);
		vo.setDescription(this.description);
		StringBuffer buf = new StringBuffer();
		String str = null;
		if (licenseEligibleState!=null) {
			int len = licenseEligibleState.length;
			if (len>1) {
				for (int i=0; i <licenseEligibleState.length;i++) {
					buf.append(licenseEligibleState[i]+", ");
				}
				str = buf.toString();
				str = str.substring(0, str.length()-2);
			} else {
				buf.append(licenseEligibleState[0]);
				str = buf.toString();
			}
		}
		vo.setLicenseEligibleState(str);
		vo.setDreNo(this.dreNo);
		vo.setNmlsNo(this.nmlsNo);	
		
		return vo;
	}
	
	
 

 

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAgentTableAction() {
		return agentTableAction;
	}

	public void setAgentTableAction(String agentTableAction) {
		this.agentTableAction = agentTableAction;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	

	public String getPictureFilename() {
		return pictureFilename;
	}

	public void setPictureFilename(String pictureFilename) {
		this.pictureFilename = pictureFilename;
	}

	

	public String getApplicationFormFilename() {
		return applicationFormFilename;
	}

	public void setApplicationFormFilename(String applicationFormFilename) {
		this.applicationFormFilename = applicationFormFilename;
	}

	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		/**
		 *  descrypt password and hash it
		 */
		
	/*	String encrypted = password;
		String decrypted = KeyPairManager.getInstance().decrypt(encrypted);	
		String hashedPassword=ui.convertToSHA256(decrypted);
		Log.info("Encrypted Password="+encrypted+"\n,decrypted password="+decrypted+"\n Hashed Password="+ hashedPassword);
		return hashedPassword;*/
		return password; 
	}
	/**
	 *  get original password only saved in session not in database
	 * @return
	 */
	public String getOriginalPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	
	public String getPictureType() {
		return pictureType;
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
		return gendar;
	}
	public void setGendar(String gendar) {
		this.gendar = gendar;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String[] getLicenseEligibleState() {
		return licenseEligibleState;
	}
	public void setLicenseEligibleState(String licenseEligibleState[]) {
		this.licenseEligibleState = licenseEligibleState;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

 

	public byte[] getPictureContent() {
		return pictureContent;
	}

	public void setPictureContent(byte[] pictureContent) {
		this.pictureContent = pictureContent;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}



	 

	

	
	
	
}
