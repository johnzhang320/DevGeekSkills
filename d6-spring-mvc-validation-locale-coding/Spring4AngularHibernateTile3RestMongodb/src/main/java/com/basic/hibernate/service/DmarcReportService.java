package com.basic.hibernate.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.basic.hibernate.dao.DmarcDetailReports;
import com.basic.hibernate.dao.DmarcDetailReportsDAO;
import com.basic.hibernate.dao.DmarcMasterReports;
import com.basic.hibernate.dao.DmarcMasterReportsDAO;
import com.basic.hibernate.pure.pojo.DetailPojo;
import com.basic.hibernate.pure.pojo.MasterPojo;

public interface DmarcReportService {
	public List<DmarcMasterReports> getMasterReportsByMasterIdRange(int lowId,int highId);
	public List<DmarcDetailReports> getDetailReportsByMasterId(int masterId);
	public DmarcMasterReportsDAO getMasterReportsDAO();
	public DmarcDetailReportsDAO getMasterDetailReportsDAO() ;
	public List<DetailPojo> getDetailPojoList(List<DmarcDetailReports> dlist);
	public List<MasterPojo> getMasterPojoList(List<DmarcMasterReports> dlist);
	public List<MasterPojo> getMasterPojoListLazy(List<DmarcMasterReports> dlist);
	
}
