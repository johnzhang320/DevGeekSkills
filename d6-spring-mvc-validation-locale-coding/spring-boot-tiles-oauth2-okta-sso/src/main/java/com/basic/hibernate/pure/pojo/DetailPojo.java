package com.basic.hibernate.pure.pojo;

import com.basic.hibernate.dao.DmarcDetailReports;
import com.basic.hibernate.dao.DmarcMasterReports;

public class DetailPojo {
	private Integer masterId;
	private Integer detailId; 
	private String sourceIp;
	private Integer count;
	private String disposition;
	private String reasonComment;
	private String reasonType;
	private String fromHeaderDomain;
	private String dkimDomain;
	private String dkimResult;
	private String dkimHumanResult;
	private String spfDomain;
	private String spfResult;
	private String alignment;
	private String alignmentDescription;
	private Integer rowCount;
	
	
	
	
	public DetailPojo(DmarcDetailReports d) {
		super();
		this.masterId = d.dmarcMasterReports.masterId;
		this.detailId = d.detailId;
		this.sourceIp = d.sourceIp;
		this.count = d.count;
		this.disposition = d.disposition;
		this.reasonComment = d.reasonComment;
		this.reasonType = d.reasonType;
		this.fromHeaderDomain = d.fromHeaderDomain;
		this.dkimDomain = d.dkimDomain;
		this.dkimResult = d.dkimResult;
		this.dkimHumanResult = d.dkimHumanResult;
		this.spfDomain = d.spfDomain;
		this.spfResult = d.spfResult;
		this.alignment = d.alignment;
		this.alignmentDescription = d.alignmentDescription;
		this.rowCount = d.rowCount;
	}
	
	public Integer getMasterId() {
		return masterId;
	}

	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}

	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getDisposition() {
		return disposition;
	}
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	public String getReasonComment() {
		return reasonComment;
	}
	public void setReasonComment(String reasonComment) {
		this.reasonComment = reasonComment;
	}
	public String getReasonType() {
		return reasonType;
	}
	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}
	public String getFromHeaderDomain() {
		return fromHeaderDomain;
	}
	public void setFromHeaderDomain(String fromHeaderDomain) {
		this.fromHeaderDomain = fromHeaderDomain;
	}
	public String getDkimDomain() {
		return dkimDomain;
	}
	public void setDkimDomain(String dkimDomain) {
		this.dkimDomain = dkimDomain;
	}
	public String getDkimResult() {
		return dkimResult;
	}
	public void setDkimResult(String dkimResult) {
		this.dkimResult = dkimResult;
	}
	public String getDkimHumanResult() {
		return dkimHumanResult;
	}
	public void setDkimHumanResult(String dkimHumanResult) {
		this.dkimHumanResult = dkimHumanResult;
	}
	public String getSpfDomain() {
		return spfDomain;
	}
	public void setSpfDomain(String spfDomain) {
		this.spfDomain = spfDomain;
	}
	public String getSpfResult() {
		return spfResult;
	}
	public void setSpfResult(String spfResult) {
		this.spfResult = spfResult;
	}
	public String getAlignment() {
		return alignment;
	}
	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}
	public String getAlignmentDescription() {
		return alignmentDescription;
	}
	public void setAlignmentDescription(String alignmentDescription) {
		this.alignmentDescription = alignmentDescription;
	}
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	
	
}
