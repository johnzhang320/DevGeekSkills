package com.email.list.reader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.loan.agent.services.Constant;

public class EmailListFactory {
	
	public static Logger LOG  =Logger.getLogger(EmailListFactory.class); 
	
	public static EmailListService handler = null;	 
	
	public static String currentUploadFile = null;
	
	public static List<EmailDynamicVO> buildDynamicEmailList(String fileContent,HttpServletRequest request) {
	
		List<EmailDynamicVO> list =  null;
	 	
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
  	     
		MultipartFile multipartFile = multipartRequest.getFile(fileContent);
		
		request.getSession().setAttribute(Constant.MULTI_PART_FILE,multipartFile);
		
		if (multipartFile==null) {
			LOG.info("fileContent may be wrong!") ;
			return null;
		}
	 	String pathFile = multipartFile.getOriginalFilename();
	 	
	 	currentUploadFile = pathFile;
	 	
 	 	try {
 			InputStream fstream = multipartFile.getInputStream(); 	
 			
 			pathFile = pathFile.toLowerCase();
 			
 			if (pathFile.endsWith(".csv")) {
 				handler = new ReadCSVFile();
 			}
 			if (pathFile.endsWith(".txt")) {
 				handler = new ReadTXTFile();
 			}
 			if (pathFile.endsWith(".xls")) {
 				handler = new ReadXLSFile();
 			}
 			if (pathFile.endsWith(".xlsx")) {
 				handler = new ReadXLSXFile();
 			} 			 
 			if (pathFile.endsWith(".doc")) {
 				handler = new ReadDOCFile();
 			} 		
 			if (pathFile.endsWith(".docx")) {
 				handler = new ReadDOCXFile();
 			} 		
			list =handler.getDynamicEmailInfo(fstream);
 		} catch (Exception e) {
			LOG.info(" readEmailListFromHTML failed !"+e.getMessage()) ;
			return null;
		}
 	 	request.getSession().setAttribute(Constant.DYNAMIC_EMAIL_LIST,list); 	 
		return list;
	}

	public static EmailListService getHandler() {
		return handler;
	}

	public static void setHandler(EmailListService handler) {
		EmailListFactory.handler = handler;
	}
	public static List<SimpleEmailLineVO> getFirstNameEmail(List<EmailDynamicVO> dyList,int firstNamePtr, int emailPtr) {
		List<SimpleEmailLineVO> list = new ArrayList<SimpleEmailLineVO>();
		if (null==dyList || 0==dyList.size()) {
			return null;
		}
		int rowCount=0;
	    for (EmailDynamicVO dVo: dyList) {
	    	List<String> cellList =dVo.getColList();
	    	if (null==cellList || 0==cellList.size()) {
	    		continue;
	    	}
	    	SimpleEmailLineVO sVo = new SimpleEmailLineVO();
	    	for (int i=0; i<cellList.size(); i++) {
	    		if (i==firstNamePtr) {
	    			sVo.setFirstName(cellList.get(i));
	    		}
	    		if (i==emailPtr) {
	    			sVo.setEmailAddress(cellList.get(i));
	    		}
	    	}
	    	
	    	list.add(sVo);
	    	
	    	rowCount++;
	    }
		return list;
	}

	public static String getCurrentUploadFile() {
		return currentUploadFile;
	}

	public static void setCurrentUploadFile(String currentUploadFile) {
		EmailListFactory.currentUploadFile = currentUploadFile;
	}
	
}
