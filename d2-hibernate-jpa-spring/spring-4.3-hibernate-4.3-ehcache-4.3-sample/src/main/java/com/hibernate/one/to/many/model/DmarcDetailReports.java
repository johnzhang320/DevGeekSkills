package com.hibernate.one.to.many.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * DmarcDetailReports entity. 
 * @author MyEclipse Persistence Tools
 * @modifier john.zhang320@gmail.com
 * usage =  CacheConcurrencyStrategy.READ_ONLY and  usage =  CacheConcurrencyStrategy.NONSTRICT_READ_WRITE
 * if we clear up session , we are able to get second level hit, which means read from second level cache
 * if usage =  CacheConcurrencyStrategy.READ_WIRTE  we are NOT are to get second level hit if clear session
 * 
 * 	READ_ONLY: Used only for entities that never change (exception is thrown if an attempt to update such an entity is made). 
 * 				It is very simple and performant. Very suitable for some static reference data that don’t change
	NONSTRICT_READ_WRITE: Cache is updated after a transaction that changed the affected data has been committed. Thus, strong 
				consistency is not guaranteed and there is a small time window in which stale data may be obtained from cache. This kind of
	 			strategy is suitable for use cases that can tolerate eventual consistency
	READ_WRITE: This strategy guarantees strong consistency which it achieves by using ‘soft’ locks: When a cached entity 
				is updated, a soft lock is stored in the cache for that entity as well, which is released after the transaction is
	 			committed. All concurrent transactions that access soft-locked entries will fetch the corresponding data directly 
	 			from database
	TRANSACTIONAL: Cache changes are done in distributed XA transactions. A change in a cached entity is either committed or
	 			rolled back in both database and cache in the same XA transaction

 * 
 */
@Entity
@Cacheable
@Cache(usage =  CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="dmarcMasterReports")  
@Table(name = "dmarc_detail_reports")
public class DmarcDetailReports { // implements java.io.Serializable {
	//private static final long serialVersionUID=01L;
	// Fields

	public Integer detailId;
	public DmarcMasterReports dmarcMasterReports;
	public String sourceIp;
	public Integer count;
	public String disposition;
	public String reasonComment;
	public String reasonType;
	public String fromHeaderDomain;
	public String dkimDomain;
	public String dkimResult;
	public String dkimHumanResult;
	public String spfDomain;
	public String spfResult;
	public String alignment;
	public String alignmentDescription;
	public Integer rowCount;

	// Constructors

	/** default constructor */
	public DmarcDetailReports() {
	}

	/** minimal constructor */
	public DmarcDetailReports(DmarcMasterReports dmarcMasterReports,
			String sourceIp, Integer count, String disposition,
			String fromHeaderDomain, Integer rowCount) {
		this.dmarcMasterReports = dmarcMasterReports;
		this.sourceIp = sourceIp;
		this.count = count;
		this.disposition = disposition;
		this.fromHeaderDomain = fromHeaderDomain;
		this.rowCount = rowCount;
	}

	/** full constructor */
	public DmarcDetailReports(DmarcMasterReports dmarcMasterReports,
			String sourceIp, Integer count, String disposition,
			String reasonComment, String reasonType, String fromHeaderDomain,
			String dkimDomain, String dkimResult, String dkimHumanResult,
			String spfDomain, String spfResult, String alignment,
			String alignmentDescription, Integer rowCount) {
		this.dmarcMasterReports = dmarcMasterReports;
		this.sourceIp = sourceIp;
		this.count = count;
		this.disposition = disposition;
		this.reasonComment = reasonComment;
		this.reasonType = reasonType;
		this.fromHeaderDomain = fromHeaderDomain;
		this.dkimDomain = dkimDomain;
		this.dkimResult = dkimResult;
		this.dkimHumanResult = dkimHumanResult;
		this.spfDomain = spfDomain;
		this.spfResult = spfResult;
		this.alignment = alignment;
		this.alignmentDescription = alignmentDescription;
		this.rowCount = rowCount;
	}

	// Property accessors
 
	@Id	 
	@Column(name = "detail_id", unique = true, nullable = false)
	public Integer getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "master_id", nullable = false)
	public DmarcMasterReports getDmarcMasterReports() {
		return this.dmarcMasterReports;
	}

	public void setDmarcMasterReports(DmarcMasterReports dmarcMasterReports) {
		this.dmarcMasterReports = dmarcMasterReports;
	}

	@Column(name = "source_ip", nullable = false)
	public String getSourceIp() {
		return this.sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	@Column(name = "count", nullable = false)
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "disposition", nullable = false, length = 25)
	public String getDisposition() {
		return this.disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	@Column(name = "reason_comment")
	public String getReasonComment() {
		return this.reasonComment;
	}

	public void setReasonComment(String reasonComment) {
		this.reasonComment = reasonComment;
	}

	@Column(name = "reason_type", length = 45)
	public String getReasonType() {
		return this.reasonType;
	}

	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}

	@Column(name = "from_header_domain", nullable = false)
	public String getFromHeaderDomain() {
		return this.fromHeaderDomain;
	}

	public void setFromHeaderDomain(String fromHeaderDomain) {
		this.fromHeaderDomain = fromHeaderDomain;
	}

	@Column(name = "dkim_domain")
	public String getDkimDomain() {
		return this.dkimDomain;
	}

	public void setDkimDomain(String dkimDomain) {
		this.dkimDomain = dkimDomain;
	}

	@Column(name = "dkim_result", length = 25)
	public String getDkimResult() {
		return this.dkimResult;
	}

	public void setDkimResult(String dkimResult) {
		this.dkimResult = dkimResult;
	}

	@Column(name = "dkim_human_result", length = 45)
	public String getDkimHumanResult() {
		return this.dkimHumanResult;
	}

	public void setDkimHumanResult(String dkimHumanResult) {
		this.dkimHumanResult = dkimHumanResult;
	}

	@Column(name = "spf_domain")
	public String getSpfDomain() {
		return this.spfDomain;
	}

	public void setSpfDomain(String spfDomain) {
		this.spfDomain = spfDomain;
	}

	@Column(name = "spf_result", length = 25)
	public String getSpfResult() {
		return this.spfResult;
	}

	public void setSpfResult(String spfResult) {
		this.spfResult = spfResult;
	}

	@Column(name = "alignment", length = 25)
	public String getAlignment() {
		return this.alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	@Column(name = "alignment_description", length = 512)
	public String getAlignmentDescription() {
		return this.alignmentDescription;
	}

	public void setAlignmentDescription(String alignmentDescription) {
		this.alignmentDescription = alignmentDescription;
	}

	@Column(name = "rowCount", nullable = false)
	public Integer getRowCount() {
		return this.rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	@Override
	public String toString() {
		return "DmarcDetailReports [detailId=" + detailId + ", sourceIp="
				+ sourceIp + ", count=" + count + ", disposition=" + disposition + ", reasonComment=" + reasonComment
				+ ", reasonType=" + reasonType + ", fromHeaderDomain=" + fromHeaderDomain + ", dkimDomain=" + dkimDomain
				+ ", dkimResult=" + dkimResult + ", dkimHumanResult=" + dkimHumanResult + ", spfDomain=" + spfDomain
				+ ", spfResult=" + spfResult + ", alignment=" + alignment + ", alignmentDescription="
				+ alignmentDescription + ", rowCount=" + rowCount + "]";
	}

}