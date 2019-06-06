package com.loan.agent.mvc.formbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.loan.agent.dao.RateSheet;
import com.loan.agent.dao.Users;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.services.Constant;

public class RateSheetForm implements Serializable {
	 private static final long serialVersionUID = 1L;
	public static final Logger LOG = Logger.getLogger(RateSheetForm.class);		
	
    private String conf30yr=null; 
    private String superConf30yr=null; 
    private String jumbo30yr=null; 
    private String credit30yr=null; 
    private String LTV30yr=null; 
    private String conf30apr=null;
    private String superConf30apr=null;
    private String jumbo30apr=null;
        
    private String conf15yr=null;             
    private String superConf15yr=null;     
    private String jumbo15yr=null; 
    private String credit15yr=null; 
    private String LTV15yr=null; 
    private String conf15apr=null;
    private String superConf15apr=null;
    private String jumbo15apr=null;
    
    private String conf10yr=null;          
    private String superConf10yr=null; 
    private String jumbo10yr=null; 
    private String credit10yr=null; 
    private String LTV10yr=null; 
    private String conf10apr=null;
    private String superConf10apr=null;
    private String jumbo10apr=null;
    
    private String conf7yr=null;          
    private String superConf7yr=null; 
    private String jumbo7yr=null; 
    private String credit7yr=null; 
    private String LTV7yr=null; 
    private String conf7apr=null;
    private String superConf7apr=null;
    private String jumbo7apr=null;

    private String conf5yr=null;          
    private String superConf5yr=null; 
    private String jumbo5yr=null; 
    private String credit5yr=null; 
    private String LTV5yr=null; 
    private String conf5apr=null;
    private String superConf5apr=null;
    private String jumbo5apr=null;

    private String conf3yr=null;          
    private String superConf3yr=null; 
    private String jumbo3yr=null; 
    private String credit3yr=null; 
    private String LTV3yr=null; 
    private String conf3apr=null;
    private String superConf3apr=null;
    private String jumbo3apr=null;
    
    
    public List<RateSheet>  modelRateSheet(Integer agentId) {
    	List<RateSheet> list = new ArrayList<RateSheet> ();  
    	RateSheet r = new RateSheet();
    	r.setTerm(Constant.TERM_30_YEARS); 
    	r.setTermId(1);
    	r.setAgentId(agentId);    	
        r.setConformRate(conf30yr);
        r.setSuperConformRate(superConf30yr);
        r.setJumboRate(jumbo30yr);
        r.setCreditScore(credit30yr);
        r.setLoanToValue(LTV30yr);
        r.setConformApr(conf30apr);
        r.setSuperConformApr(superConf30apr);
        r.setJumboApr(jumbo30apr);
      
        if (!Utility.isEmpty(r.getConformRate()) && !Utility.isEmpty(r.getSuperConformRate())) {
        	list.add(r);
    	}
        
    	r = new RateSheet();
    	r.setTerm(Constant.TERM_15_YEARS); 
    	r.setTermId(2);
    	r.setAgentId(agentId);    	
        r.setConformRate(conf15yr);
        r.setSuperConformRate(superConf15yr);
        r.setJumboRate(jumbo15yr);
        r.setCreditScore(credit15yr);
        r.setLoanToValue(LTV15yr);
        r.setConformApr(conf15apr);
        r.setSuperConformApr(superConf15apr);
        r.setJumboApr(jumbo15apr);
        
        if (!Utility.isEmpty(r.getConformRate()) && !Utility.isEmpty(r.getSuperConformRate())) {
        	list.add(r);
    	}
               
    	r = new RateSheet();
    	r.setTerm(Constant.TERM_10_YEARS); 
    	r.setTermId(3);
    	r.setAgentId(agentId);  	
        r.setConformRate(conf10yr);
        r.setSuperConformRate(superConf10yr);
        r.setJumboRate(jumbo10yr);
        r.setCreditScore(credit10yr);
        r.setLoanToValue(LTV10yr);
        r.setConformApr(conf10apr);
        r.setSuperConformApr(superConf10apr);
        r.setJumboApr(jumbo10apr);
        
        if (!Utility.isEmpty(r.getConformRate()) && !Utility.isEmpty(r.getSuperConformRate())) {
        	list.add(r);
    	}
                
    	r = new RateSheet();
    	r.setTerm(Constant.TERM_7_YEARS); 
    	r.setTermId(4);
    	r.setAgentId(agentId);   	
        r.setConformRate(conf7yr);
        r.setSuperConformRate(superConf7yr);
        r.setJumboRate(jumbo7yr);
        r.setCreditScore(credit7yr);
        r.setLoanToValue(LTV7yr);
        r.setConformApr(conf7apr);
        r.setSuperConformApr(superConf7apr);
        r.setJumboApr(jumbo7apr);
        
        if (!Utility.isEmpty(r.getConformRate()) && !Utility.isEmpty(r.getSuperConformRate())) {
        	list.add(r);
    	}
                
    	r = new RateSheet();
    	r.setTerm(Constant.TERM_5_YEARS);     	
    	r.setTermId(5);
    	r.setAgentId(agentId);    	
        r.setConformRate(conf5yr);
        r.setSuperConformRate(superConf5yr);
        r.setJumboRate(jumbo5yr);
        r.setCreditScore(credit5yr);
        r.setLoanToValue(LTV5yr);
        r.setConformApr(conf5apr);
        r.setSuperConformApr(superConf5apr);
        r.setJumboApr(jumbo5apr);
        
        if (!Utility.isEmpty(r.getConformRate()) && !Utility.isEmpty(r.getSuperConformRate())) {
        	list.add(r);
    	}
                
    	r = new RateSheet();
    	r.setTerm(Constant.TERM_3_YEARS);     	
    	r.setTermId(6);
    	r.setAgentId(agentId);        
       r.setConformRate(conf3yr);
       r.setSuperConformRate(superConf3yr);
       r.setJumboRate(jumbo3yr);
       r.setCreditScore(credit3yr);
       r.setLoanToValue(LTV3yr);
       r.setConformApr(conf3apr);
       r.setSuperConformApr(superConf3apr);
       r.setJumboApr(jumbo3apr);
       
       if (!Utility.isEmpty(r.getConformRate()) && !Utility.isEmpty(r.getSuperConformRate())) {
       	list.add(r);
   	   }
       

		return list;
	}
	
    public void renderRateSheet(List<RateSheet> list) {
    	 if (list==null || list.size()==0) {
    		 return;
    	 }
    	 for (RateSheet r:list) {
    		if (Constant.TERM_30_YEARS.equalsIgnoreCase(r.getTerm())) {
    	  	      conf30yr=r.getConformRate(); 
                  superConf30yr=r.getSuperConformRate(); 
                  jumbo30yr=r.getJumboRate(); 
                  credit30yr=r.getCreditScore(); 
                  LTV30yr=r.getLoanToValue(); 
                  conf30apr=r.getConformApr();
                  superConf30apr=r.getSuperConformApr();
                  jumbo30apr=r.getJumboApr();
            }  
    		if (Constant.TERM_15_YEARS.equalsIgnoreCase(r.getTerm())) {
    	  	      conf15yr=r.getConformRate(); 
                  superConf15yr=r.getSuperConformRate(); 
                  jumbo15yr=r.getJumboRate(); 
                  credit15yr=r.getCreditScore(); 
                  LTV15yr=r.getLoanToValue(); 
                  conf15apr=r.getConformApr();
                  superConf15apr=r.getSuperConformApr();
                  jumbo15apr=r.getJumboApr();
    	    }  
    		if (Constant.TERM_10_YEARS.equalsIgnoreCase(r.getTerm())) {    
    	  	      conf10yr=r.getConformRate(); 
                  superConf10yr=r.getSuperConformRate(); 
                  jumbo10yr=r.getJumboRate(); 
                  credit10yr=r.getCreditScore(); 
                  LTV10yr=r.getLoanToValue(); 
                  conf10apr=r.getConformApr();
                  superConf10apr=r.getSuperConformApr();
                  jumbo10apr=r.getJumboApr();
    	    }  
    		if (Constant.TERM_7_YEARS.equalsIgnoreCase(r.getTerm())) {      
    	  	      conf7yr=r.getConformRate(); 
                  superConf7yr=r.getSuperConformRate(); 
                  jumbo7yr=r.getJumboRate(); 
                  credit7yr=r.getCreditScore(); 
                  LTV7yr=r.getLoanToValue(); 
                  conf7apr=r.getConformApr();
                  superConf7apr=r.getSuperConformApr();
                  jumbo7apr=r.getJumboApr();
    	    }  
    		if (Constant.TERM_5_YEARS.equalsIgnoreCase(r.getTerm())) {
    	  	      conf5yr=r.getConformRate(); 
                  superConf5yr=r.getSuperConformRate(); 
                  jumbo5yr=r.getJumboRate(); 
                  credit5yr=r.getCreditScore(); 
                  LTV5yr=r.getLoanToValue(); 
                  conf5apr=r.getConformApr();
                  superConf5apr=r.getSuperConformApr();
                  jumbo5apr=r.getJumboApr();
    	    }  
    		if (Constant.TERM_3_YEARS.equalsIgnoreCase(r.getTerm())) {
    	  	      conf3yr=r.getConformRate(); 
                  superConf3yr=r.getSuperConformRate(); 
                  jumbo3yr=r.getJumboRate(); 
                  credit3yr=r.getCreditScore(); 
                  LTV3yr=r.getLoanToValue(); 
                  conf3apr=r.getConformApr();
                  superConf3apr=r.getSuperConformApr();
                  jumbo3apr=r.getJumboApr();
    		}

    	 }
    	    
    }
   
    private List<RateSheet> conformingList;
    private List<RateSheet> superConformingList;
    private List<RateSheet> jumboList;
    
    
    
    public List<RateSheet> getConformingList() {
		return conformingList;
	}

	public void setConformingList(List<RateSheet> conformingList) {
		this.conformingList = conformingList;
	}

	public List<RateSheet> getSuperConformingList() {
		return superConformingList;
	}

	public void setSuperConformingList(List<RateSheet> superConformingList) {
		this.superConformingList = superConformingList;
	}

	public List<RateSheet> getJumboList() {
		return jumboList;
	}

	public void setJumboList(List<RateSheet> jumboList) {
		this.jumboList = jumboList;
	}

	public void publishRateToSession(HttpServletRequest request,List<RateSheet> list) {
		List<RateSheet> cfList= this.getConformingList(list);
		List<RateSheet> scfList= this.getSuperConformingList(list);
		List<RateSheet> jbfList= this.getJumboList(list);
		
		request.getSession().setAttribute(Constant.CONFORMING_RATE_LIST, cfList);
		request.getSession().setAttribute(Constant.SUPER_CONFORMING_RATE_LIST, scfList);
		request.getSession().setAttribute(Constant.JUMBO_RATE_LIST, jbfList);
    }
    
    public List<RateSheet> getConformingList(List<RateSheet> list) {
    	 if (list==null || list.size()==0) {
    		 return null;
    	 }
    	 List<RateSheet> cList = new ArrayList<RateSheet>();
    	 
    	 for (RateSheet r:list) {
    		RateSheet v = new RateSheet();
     		v.setTerm(r.getTerm());
     	  	v.setConformRate(r.getConformRate()); 
     	  	v.setConformApr(r.getConformApr()); 
     	  	v.setLoanToValue(r.getLoanToValue());      	
            v.setCreditScore(r.getCreditScore()); 
            cList.add(v);
    	 }
         return cList;
    }
    
    public List<RateSheet> getSuperConformingList(List<RateSheet> list) {
   	 if (list==null || list.size()==0) {
   		 return null;
   	 }
   	 List<RateSheet> cList = new ArrayList<RateSheet>();
   	 
   	 for (RateSheet r:list) {
   		RateSheet v = new RateSheet();
    		v.setTerm(r.getTerm());
    	  	v.setSuperConformRate(r.getConformRate()); 
    	  	v.setSuperConformApr(r.getConformApr()); 
    	  	v.setLoanToValue(r.getLoanToValue());      	
           v.setCreditScore(r.getCreditScore()); 
           cList.add(v);
   	 }
        return cList;
   } 
    
    public List<RateSheet> getJumboList(List<RateSheet> list) {
   	 if (list==null || list.size()==0) {
   		 return null;
   	 }
   	 List<RateSheet> cList = new ArrayList<RateSheet>();
   	 
   	 for (RateSheet r:list) {
   		RateSheet v = new RateSheet();
    		v.setTerm(r.getTerm());
    	  	v.setJumboRate(r.getConformRate()); 
    	  	v.setJumboApr(r.getConformApr()); 
    	  	v.setLoanToValue(r.getLoanToValue());      	
           v.setCreditScore(r.getCreditScore()); 
           cList.add(v);
   	 }
        return cList;
   } 
	public String getConf30yr() {
		return conf30yr;
	}
	public void setConf30yr(String conf30yr) {
		this.conf30yr = conf30yr;
	}
	public String getSuperConf30yr() {
		return superConf30yr;
	}
	public void setSuperConf30yr(String superConf30yr) {
		this.superConf30yr = superConf30yr;
	}
	public String getJumbo30yr() {
		return jumbo30yr;
	}
	public void setJumbo30yr(String jumbo30yr) {
		this.jumbo30yr = jumbo30yr;
	}
	public String getCredit30yr() {
		return credit30yr;
	}
	public void setCredit30yr(String credit30yr) {
		this.credit30yr = credit30yr;
	}
	public String getLTV30yr() {
		return LTV30yr;
	}
	public void setLTV30yr(String lTV30yr) {
		LTV30yr = lTV30yr;
	}
	public String getConf30apr() {
		return conf30apr;
	}
	public void setConf30apr(String conf30apr) {
		this.conf30apr = conf30apr;
	}
	public String getSuperConf30apr() {
		return superConf30apr;
	}
	public void setSuperConf30apr(String superConf30apr) {
		this.superConf30apr = superConf30apr;
	}
	public String getJumbo30apr() {
		return jumbo30apr;
	}
	public void setJumbo30apr(String jumbo30apr) {
		this.jumbo30apr = jumbo30apr;
	}
	public String getConf15yr() {
		return conf15yr;
	}
	public void setConf15yr(String conf15yr) {
		this.conf15yr = conf15yr;
	}
	public String getSuperConf15yr() {
		return superConf15yr;
	}
	public void setSuperConf15yr(String superConf15yr) {
		this.superConf15yr = superConf15yr;
	}
	public String getJumbo15yr() {
		return jumbo15yr;
	}
	public void setJumbo15yr(String jumbo15yr) {
		this.jumbo15yr = jumbo15yr;
	}
	public String getCredit15yr() {
		return credit15yr;
	}
	public void setCredit15yr(String credit15yr) {
		this.credit15yr = credit15yr;
	}
	public String getLTV15yr() {
		return LTV15yr;
	}
	public void setLTV15yr(String lTV15yr) {
		LTV15yr = lTV15yr;
	}
	public String getConf15apr() {
		return conf15apr;
	}
	public void setConf15apr(String conf15apr) {
		this.conf15apr = conf15apr;
	}
	public String getSuperConf15apr() {
		return superConf15apr;
	}
	public void setSuperConf15apr(String superConf15apr) {
		this.superConf15apr = superConf15apr;
	}
	public String getJumbo15apr() {
		return jumbo15apr;
	}
	public void setJumbo15apr(String jumbo15apr) {
		this.jumbo15apr = jumbo15apr;
	}
	public String getConf10yr() {
		return conf10yr;
	}
	public void setConf10yr(String conf10yr) {
		this.conf10yr = conf10yr;
	}
	public String getSuperConf10yr() {
		return superConf10yr;
	}
	public void setSuperConf10yr(String superConf10yr) {
		this.superConf10yr = superConf10yr;
	}
	public String getJumbo10yr() {
		return jumbo10yr;
	}
	public void setJumbo10yr(String jumbo10yr) {
		this.jumbo10yr = jumbo10yr;
	}
	public String getCredit10yr() {
		return credit10yr;
	}
	public void setCredit10yr(String credit10yr) {
		this.credit10yr = credit10yr;
	}
	public String getLTV10yr() {
		return LTV10yr;
	}
	public void setLTV10yr(String lTV10yr) {
		LTV10yr = lTV10yr;
	}
	public String getConf10apr() {
		return conf10apr;
	}
	public void setConf10apr(String conf10apr) {
		this.conf10apr = conf10apr;
	}
	public String getSuperConf10apr() {
		return superConf10apr;
	}
	public void setSuperConf10apr(String superConf10apr) {
		this.superConf10apr = superConf10apr;
	}
	public String getJumbo10apr() {
		return jumbo10apr;
	}
	public void setJumbo10apr(String jumbo10apr) {
		this.jumbo10apr = jumbo10apr;
	}
	public String getConf7yr() {
		return conf7yr;
	}
	public void setConf7yr(String conf7yr) {
		this.conf7yr = conf7yr;
	}
	public String getSuperConf7yr() {
		return superConf7yr;
	}
	public void setSuperConf7yr(String superConf7yr) {
		this.superConf7yr = superConf7yr;
	}
	public String getJumbo7yr() {
		return jumbo7yr;
	}
	public void setJumbo7yr(String jumbo7yr) {
		this.jumbo7yr = jumbo7yr;
	}
	public String getCredit7yr() {
		return credit7yr;
	}
	public void setCredit7yr(String credit7yr) {
		this.credit7yr = credit7yr;
	}
	public String getLTV7yr() {
		return LTV7yr;
	}
	public void setLTV7yr(String lTV7yr) {
		LTV7yr = lTV7yr;
	}
	public String getConf7apr() {
		return conf7apr;
	}
	public void setConf7apr(String conf7apr) {
		this.conf7apr = conf7apr;
	}
	public String getSuperConf7apr() {
		return superConf7apr;
	}
	public void setSuperConf7apr(String superConf7apr) {
		this.superConf7apr = superConf7apr;
	}
	public String getJumbo7apr() {
		return jumbo7apr;
	}
	public void setJumbo7apr(String jumbo7apr) {
		this.jumbo7apr = jumbo7apr;
	}
	public String getConf5yr() {
		return conf5yr;
	}
	public void setConf5yr(String conf5yr) {
		this.conf5yr = conf5yr;
	}
	public String getSuperConf5yr() {
		return superConf5yr;
	}
	public void setSuperConf5yr(String superConf5yr) {
		this.superConf5yr = superConf5yr;
	}
	public String getJumbo5yr() {
		return jumbo5yr;
	}
	public void setJumbo5yr(String jumbo5yr) {
		this.jumbo5yr = jumbo5yr;
	}
	public String getCredit5yr() {
		return credit5yr;
	}
	public void setCredit5yr(String credit5yr) {
		this.credit5yr = credit5yr;
	}
	public String getLTV5yr() {
		return LTV5yr;
	}
	public void setLTV5yr(String lTV5yr) {
		LTV5yr = lTV5yr;
	}
	public String getConf5apr() {
		return conf5apr;
	}
	public void setConf5apr(String conf5apr) {
		this.conf5apr = conf5apr;
	}
	public String getSuperConf5apr() {
		return superConf5apr;
	}
	public void setSuperConf5apr(String superConf5apr) {
		this.superConf5apr = superConf5apr;
	}
	public String getJumbo5apr() {
		return jumbo5apr;
	}
	public void setJumbo5apr(String jumbo5apr) {
		this.jumbo5apr = jumbo5apr;
	}
	public String getConf3yr() {
		return conf3yr;
	}
	public void setConf3yr(String conf3yr) {
		this.conf3yr = conf3yr;
	}
	public String getSuperConf3yr() {
		return superConf3yr;
	}
	public void setSuperConf3yr(String superConf3yr) {
		this.superConf3yr = superConf3yr;
	}
	public String getJumbo3yr() {
		return jumbo3yr;
	}
	public void setJumbo3yr(String jumbo3yr) {
		this.jumbo3yr = jumbo3yr;
	}
	public String getCredit3yr() {
		return credit3yr;
	}
	public void setCredit3yr(String credit3yr) {
		this.credit3yr = credit3yr;
	}
	public String getLTV3yr() {
		return LTV3yr;
	}
	public void setLTV3yr(String lTV3yr) {
		LTV3yr = lTV3yr;
	}
	public String getConf3apr() {
		return conf3apr;
	}
	public void setConf3apr(String conf3apr) {
		this.conf3apr = conf3apr;
	}
	public String getSuperConf3apr() {
		return superConf3apr;
	}
	public void setSuperConf3apr(String superConf3apr) {
		this.superConf3apr = superConf3apr;
	}
	public String getJumbo3apr() {
		return jumbo3apr;
	}
	public void setJumbo3apr(String jumbo3apr) {
		this.jumbo3apr = jumbo3apr;
	}
	public static Logger getLog() {
		return LOG;
	}
    
    
    


}
