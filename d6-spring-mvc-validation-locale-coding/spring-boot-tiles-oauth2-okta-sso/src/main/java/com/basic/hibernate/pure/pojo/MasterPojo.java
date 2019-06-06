package com.basic.hibernate.pure.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.basic.hibernate.dao.DmarcMasterReports;
import com.basic.hibernate.service.DmarcReportServiceImpl;

public class MasterPojo {
	private Integer masterId;
	private String orgName;
	private String email;
	private String extraContractInfo;
	private String reportId;
	private Date beginTime;
	private Date endTime;
	private String domain;
	private String adkim;
	private String aspf;
	private String pct;
	private String sp;
	private Date dayForSearch;
	private String transactionDone;
	private String hostName;
	private String ruaFileName;
	private Date initialDatetime;
	private Date finishDatetime;
	private String policyString;
	private List<DetailPojo> details = new ArrayList<DetailPojo>();
	private static DmarcReportServiceImpl dservice = new DmarcReportServiceImpl();
	
	public MasterPojo(DmarcMasterReports d) {
		super();
		this.masterId = d.masterId;
		this.orgName = d.orgName;
		this.email = d.email;
		this.extraContractInfo = d.extraContractInfo;
		this.reportId = d.reportId;
		this.beginTime = d.beginTime;
		this.endTime = d.endTime;
		this.domain = d.domain;
		this.adkim = d.adkim;
		this.aspf = d.aspf;
		this.pct = d.pct;
		this.sp = d.sp;
		this.dayForSearch = d.dayForSearch;
		this.transactionDone = d.transactionDone;
		this.hostName = d.hostName;
		this.ruaFileName = d.ruaFileName;
		this.initialDatetime = d.initialDatetime;
		this.finishDatetime = d.finishDatetime;
		this.policyString = d.policyString;
		this.details = dservice.getDetailPojoList(d.dmarcDetailReportses);
	}
	public MasterPojo(DmarcMasterReports d, boolean lazy) {
		super();
		this.masterId = d.masterId;
		this.orgName = d.orgName;
		this.email = d.email;
		this.extraContractInfo = d.extraContractInfo;
		this.reportId = d.reportId;
		this.beginTime = d.beginTime;
		this.endTime = d.endTime;
		this.domain = d.domain;
		this.adkim = d.adkim;
		this.aspf = d.aspf;
		this.pct = d.pct;
		this.sp = d.sp;
		this.dayForSearch = d.dayForSearch;
		this.transactionDone = d.transactionDone;
		this.hostName = d.hostName;
		this.ruaFileName = d.ruaFileName;
		this.initialDatetime = d.initialDatetime;
		this.finishDatetime = d.finishDatetime;
		this.policyString = d.policyString;
		if (!lazy) {
			this.details = dservice.getDetailPojoList(d.dmarcDetailReportses);
		}  
	}
	public List<DetailPojo> getDetails() {
		return details;
	}

	public void setDetails(List<DetailPojo> details) {
		this.details = details;
	}

	public Integer getMasterId() {
		return masterId;
	}
	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getExtraContractInfo() {
		return extraContractInfo;
	}
	public void setExtraContractInfo(String extraContractInfo) {
		this.extraContractInfo = extraContractInfo;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getAdkim() {
		return adkim;
	}
	public void setAdkim(String adkim) {
		this.adkim = adkim;
	}
	public String getAspf() {
		return aspf;
	}
	public void setAspf(String aspf) {
		this.aspf = aspf;
	}
	public String getPct() {
		return pct;
	}
	public void setPct(String pct) {
		this.pct = pct;
	}
	public String getSp() {
		return sp;
	}
	public void setSp(String sp) {
		this.sp = sp;
	}
	public Date getDayForSearch() {
		return dayForSearch;
	}
	public void setDayForSearch(Date dayForSearch) {
		this.dayForSearch = dayForSearch;
	}
	public String getTransactionDone() {
		return transactionDone;
	}
	public void setTransactionDone(String transactionDone) {
		this.transactionDone = transactionDone;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getRuaFileName() {
		return ruaFileName;
	}
	public void setRuaFileName(String ruaFileName) {
		this.ruaFileName = ruaFileName;
	}
	public Date getInitialDatetime() {
		return initialDatetime;
	}
	public void setInitialDatetime(Date initialDatetime) {
		this.initialDatetime = initialDatetime;
	}
	public Date getFinishDatetime() {
		return finishDatetime;
	}
	public void setFinishDatetime(Date finishDatetime) {
		this.finishDatetime = finishDatetime;
	}
	public String getPolicyString() {
		return policyString;
	}
	public void setPolicyString(String policyString) {
		this.policyString = policyString;
	}
	
	
}
