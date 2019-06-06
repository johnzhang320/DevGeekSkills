package com.loan.agent.mvc.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

 
public class ProcessDownloadFile {
	
	static Logger LOG = Logger.getLogger( ProcessDownloadFile.class);
	
	public static void downloadEmailContent(HttpServletResponse response,String contentType,byte[] content,String finalFilename) {
		    ServletOutputStream sos = null;
		    if (finalFilename==null || finalFilename.trim().length()==0) {
		    	finalFilename="yourEmailContent.html";
		    }
		    finalFilename  =finalFilename.replace(":", "-").replace(" ", "-").replace(".", "-").replace(",", "-").replace(";", "-").replace("'", "-").replace('"', '-');
            if ("text/plain".equalsIgnoreCase(contentType)) {
            	finalFilename =finalFilename+".txt";
            } else if ("text/html".equalsIgnoreCase(contentType)) {
            	finalFilename =finalFilename+".html";
            }  
            		 
	        try {
	           
	            // e.g. application/octet-stream; application/msexcel
	            response.setContentType(contentType + ";name=" + finalFilename);
	            response.setHeader("pragma", "public");
	            response.setHeader("Cache-Control", "public");
	            response.setHeader("Content-Disposition", "attachment; filename=\"" + finalFilename + "\"");

	            LOG.info("createPDFDownload(): set Content-disposition: " + "attachment; filename=\"" + finalFilename + "\"");

	            long size = content.length;
	            response.setHeader("Content-Length", Long.toString(size));
	            sos = response.getOutputStream();
	            
	             ;
                for (int i=0; i<content.length;i++) {
                
	                sos.write(content[i]);
	            }
	        }
	        catch (Exception e) {
	            LOG.info("createPDFDownload: UNABLE to download " + "/" + finalFilename);
	        }
	        finally {
	            
	            try {
	                if (sos != null) sos.close();
	            }
	            catch (Exception se) {
	                LOG.info(" UNABLE to close FileInputStream for download.", se);
	            }
	        }
	   }
	
	
	
	public static void downloadFile(HttpServletResponse response,String contentType,String originFile, String finalFilename) {
		 ServletOutputStream sos = null;
	       BufferedInputStream bis = null;
	       
	       File file = new File(originFile) ;
	       if (!file.exists()) {
	    	   LOG.info("Original File doesn't exist !");
	    	   return ;
	       }
	       

	        try {
	          

	            // e.g. application/octet-stream; application/msexcel
	            response.setContentType(contentType + ";name=" + finalFilename);
	            response.setHeader("pragma", "public");
	            response.setHeader("Cache-Control", "public");
	            response.setHeader("Content-Disposition", "attachment; filename=\"" + finalFilename + "\"");

	          //  LOG.info(" set Content-disposition: " + "attachment; filename=\"" + finalFilename + "\"");

	            long size = file.length();
	            
	            response.setHeader("Content-Length", Long.toString(size));
	           
	            sos = response.getOutputStream();
	           
	            bis = new BufferedInputStream(new FileInputStream(file));
	            int data = 0;
	            
	            while((data = bis.read()) != -1) {
	                sos.write(data);
	            }
	            
	        }
	        catch (Exception e) {
	            LOG.info(" UNABLE to download " +  finalFilename);
	        }
	        finally {
	            try {
	                if (bis != null) bis.close();
	            }
	            catch (Exception be) {
	                LOG.info("Dowloader.starts(): UNABLE to close BufferedInputStream for download.", be);
	            }
	            try {
	                if (sos != null) sos.close();
	            }
	            catch (Exception se) {
	                LOG.info(" UNABLE to close FileInputStream for download.", se);
	            }
	        }

	        LOG.debug(" EXIT");
	    }
	
}
