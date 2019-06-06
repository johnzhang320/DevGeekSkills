package com.basic.hibernate.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * DmarcMasterReports entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dmarc_master_reports", catalog = "rua_receiverdb", uniqueConstraints = @UniqueConstraint(columnNames = {
		"org_name", "report_id" }))
public class DmarcMasterReports { //implements java.io.Serializable {

	// Fields

	public Integer masterId;
	public String orgName;
	public String email;
	public String extraContractInfo;
	public String reportId;
	public Date beginTime;
	public Date endTime;
	public String domain;
	public String adkim;
	public String aspf;
	public String pct;
	public String sp;
	public Date dayForSearch;
	public String transactionDone;
	public String hostName;
	public String ruaFileName;
	public Date initialDatetime;
	public Date finishDatetime;
	public String policyString;
	
	public List<DmarcDetailReports> dmarcDetailReportses = new ArrayList<DmarcDetailReports>(0);
	// Constructors

	/** default constructor */
	public DmarcMasterReports() {
	}

	/** minimal constructor */
	public DmarcMasterReports(Integer masterId, String orgName,
			String reportId, Date beginTime, Date endTime, String domain,
			String adkim, String aspf, String pct, Date dayForSearch) {
		this.masterId = masterId;
		this.orgName = orgName;
		this.reportId = reportId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.domain = domain;
		this.adkim = adkim;
		this.aspf = aspf;
		this.pct = pct;
		this.dayForSearch = dayForSearch;
	}

	/** full constructor 
	public DmarcMasterReports(Integer masterId, String orgName, String email,
			String extraContractInfo, String reportId, Date beginTime,
			Date endTime, String domain, String adkim, String aspf, String pct,
			String sp, Date dayForSearch, String transactionDone,
			String hostName, String ruaFileName, Date initialDatetime,
			Date finishDatetime, String policyString,
			List<DmarcDetailReports> dmarcDetailReportses) {
		this.masterId = masterId;
		this.orgName = orgName;
		this.email = email;
		this.extraContractInfo = extraContractInfo;
		this.reportId = reportId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.domain = domain;
		this.adkim = adkim;
		this.aspf = aspf;
		this.pct = pct;
		this.sp = sp;
		this.dayForSearch = dayForSearch;
		this.transactionDone = transactionDone;
		this.hostName = hostName;
		this.ruaFileName = ruaFileName;
		this.initialDatetime = initialDatetime;
		this.finishDatetime = finishDatetime;
		this.policyString = policyString;
		this.dmarcDetailReportses = dmarcDetailReportses;
	}
*/
	// Property accessors
	@Id
	@Column(name = "master_id", unique = true, nullable = false)
	public Integer getMasterId() {
		return this.masterId;
	}

	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}

	@Column(name = "org_name", nullable = false)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "extra_contract_info", length = 512)
	public String getExtraContractInfo() {
		return this.extraContractInfo;
	}

	public void setExtraContractInfo(String extraContractInfo) {
		this.extraContractInfo = extraContractInfo;
	}

	@Column(name = "report_id", nullable = false)
	public String getReportId() {
		return this.reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	@Column(name = "begin_time", nullable = false, length = 19)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "end_time", nullable = false, length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "domain", nullable = false)
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "adkim", nullable = false, length = 2)
	public String getAdkim() {
		return this.adkim;
	}

	public void setAdkim(String adkim) {
		this.adkim = adkim;
	}

	@Column(name = "aspf", nullable = false, length = 2)
	public String getAspf() {
		return this.aspf;
	}

	public void setAspf(String aspf) {
		this.aspf = aspf;
	}

	@Column(name = "pct", nullable = false, length = 65)
	public String getPct() {
		return this.pct;
	}

	public void setPct(String pct) {
		this.pct = pct;
	}

	@Column(name = "sp", length = 45)
	public String getSp() {
		return this.sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	@Column(name = "day_for_search", nullable = false, length = 19)
	public Date getDayForSearch() {
		return this.dayForSearch;
	}

	public void setDayForSearch(Date dayForSearch) {
		this.dayForSearch = dayForSearch;
	}

	@Column(name = "transaction_done", length = 10)
	public String getTransactionDone() {
		return this.transactionDone;
	}

	public void setTransactionDone(String transactionDone) {
		this.transactionDone = transactionDone;
	}

	@Column(name = "host_name", length = 125)
	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	@Column(name = "rua_file_name", length = 512)
	public String getRuaFileName() {
		return this.ruaFileName;
	}

	public void setRuaFileName(String ruaFileName) {
		this.ruaFileName = ruaFileName;
	}

	@Column(name = "initial_datetime", length = 19)
	public Date getInitialDatetime() {
		return this.initialDatetime;
	}

	public void setInitialDatetime(Date initialDatetime) {
		this.initialDatetime = initialDatetime;
	}

	@Column(name = "finish_datetime", length = 19)
	public Date getFinishDatetime() {
		return this.finishDatetime;
	}

	public void setFinishDatetime(Date finishDatetime) {
		this.finishDatetime = finishDatetime;
	}

	@Column(name = "policy_string", length = 225)
	public String getPolicyString() {
		return this.policyString;
	}

	public void setPolicyString(String policyString) {
		this.policyString = policyString;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dmarcMasterReports")
	public List<DmarcDetailReports> getDmarcDetailReportses() {
		return this.dmarcDetailReportses;
	}

	public void setDmarcDetailReportses(
			List<DmarcDetailReports> dmarcDetailReportses) {
		this.dmarcDetailReportses = dmarcDetailReportses;
	}
}