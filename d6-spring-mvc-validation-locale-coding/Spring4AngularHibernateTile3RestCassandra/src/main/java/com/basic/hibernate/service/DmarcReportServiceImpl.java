package com.basic.hibernate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.basic.hibernate.dao.BaseHibernateDAO;
import com.basic.hibernate.dao.DmarcDetailReports;
import com.basic.hibernate.dao.DmarcDetailReportsDAO;
import com.basic.hibernate.dao.DmarcMasterReports;
import com.basic.hibernate.dao.DmarcMasterReportsDAO;
import com.basic.hibernate.pure.pojo.DetailPojo;
import com.basic.hibernate.pure.pojo.MasterPojo;

public class DmarcReportServiceImpl extends BaseHibernateDAO implements DmarcReportService {
	
	Logger Log = Logger.getLogger(DmarcReportServiceImpl.class);
	
	public List<DmarcMasterReports> getMasterReportsByMasterIdRange(int lowId,int highId) {
		Criteria cr = getSession().createCriteria(DmarcMasterReports.class);
		cr.add(Restrictions.between("masterId", lowId, highId));
		List<DmarcMasterReports> results= cr.list();
		if (results==null || results.isEmpty()) {
			Log.info("Did not find Dmarc Master Reports data!");
		}
		return results;
	}
	public List<DmarcDetailReports> getDetailReportsByMasterId(int masterId) {
		DmarcMasterReports instance =(DmarcMasterReports) getSession().get(DmarcMasterReports.class, masterId);
		if (null==instance) {
			Log.info("Did not find Dmarc Master Reports data!");
		}
	    List<DmarcDetailReports> results =(List<DmarcDetailReports>) instance.getDmarcDetailReportses();
		return results;
	}
	
	public List<DetailPojo> getDetailPojoList(List<DmarcDetailReports> dlist) {
		List<DetailPojo> list = new ArrayList<DetailPojo>();
		for (DmarcDetailReports d:dlist) {
			list.add(new DetailPojo(d));
		}
		return list;
	}
	public List<MasterPojo> getMasterPojoList(List<DmarcMasterReports> dlist) {
		List<MasterPojo> list = new ArrayList<MasterPojo>();
		for (DmarcMasterReports d:dlist) {
			list.add(new MasterPojo(d));
		}
		return list;
	}
	public List<MasterPojo> getMasterPojoListLazy(List<DmarcMasterReports> dlist) {
		List<MasterPojo> list = new ArrayList<MasterPojo>();
		for (DmarcMasterReports d:dlist) {
			list.add(new MasterPojo(d,true));
		}
		return list;
	}
	public DmarcMasterReportsDAO getMasterReportsDAO() {
		return new DmarcMasterReportsDAO();
	}
	public DmarcDetailReportsDAO getMasterDetailReportsDAO() {
		return new DmarcDetailReportsDAO();
	}
}
