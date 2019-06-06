package com.loan.agent.mvc.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.loan.agent.dao.Agents;
import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;

public class EmailMultipleController extends MultiActionController {
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService;
	
	static final Logger LOG = Logger.getLogger(EmailMultipleController.class);
	
	public ModelAndView previewPictureHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		LOG.info("previewPictureHandler begin");
		
			Utility.setRequest(request,response);
		
		Integer agentId =ui.getSessionAttributeInt(Constant.AGENT_ID);
		if (agentId==null) {
			return null;
		}
	  
	
	    Agents agents = quoteDBService.getAgentsDAO().findById(agentId); 
	    
	    String agentPath = SysPath.getInstance().getAgentPicturePath();
		
		byte[] blobAsBytes = ProcessUploadFile.getFileByteArrayByAgent(agentId, agents.getPictureFilename(), Constant.PREFIX_AGENT_PICTURE_FILE, agentPath);
		
 		
		if (blobAsBytes==null) {
			LOG.info("blobAsBytes=null");
			return null;
		} else {
			LOG.info("blobAsBytes.length="+blobAsBytes.length);
		}
		
		String contentType = "image/jpg";//ui.getSessionAttribute(Constant.IMAGE_FILE_TYPE);
		
		response.setContentType(contentType);
	    response.setHeader("Cache-control", "no-cache, no-store");
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Expires", "-1");
	    response.setStatus(HttpServletResponse.SC_OK);
	    ServletOutputStream out = response.getOutputStream();
	    out.write(blobAsBytes);
	    blobAsBytes=null;
	    out.flush();
	    out.close();			
		
	 
		return null;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView viewEmailSendStatusHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		LOG.info("viewEmailSendStatusHandler begin");
		
		ui.setRequest(request);
		
		  //content type must be set to text/event-stream
       // response.setContentType("text/event-stream");  
 
        //encoding must be set to UTF-8
        //response.setCharacterEncoding("UTF-8");
 
       /* PrintWriter writer = response.getWriter();
 
        for(int i=0; i<10; i++) {
 
            writer.write("data: "+ System.currentTimeMillis() +"\n\n");
 
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writer.close();*/
		/**
		 *  Output json
		 */
		PrintWriter out= Utility.SetPostResponseContent(response);	
				
		/*Date date = new Date();		 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String strDate = sdf.format(date);*/
		
		String emailStatus = ui.getSessionAttribute(Constant.EMAIL_SEND_STATUS);
		String prevEmailStatus = ui.getSessionAttribute(Constant.PREVIOUS_EMAIL_SEND_STATUS);
		String feedback="";
		if (Utility.isEmpty(prevEmailStatus) && Utility.isEmpty(emailStatus)) {
			feedback=Constant.FEEDBACK_NODATA;
		}
		if (Utility.isEmpty(prevEmailStatus) && !Utility.isEmpty(emailStatus)) {
			feedback=emailStatus;
			ui.setSessionAttribute(Constant.PREVIOUS_EMAIL_SEND_STATUS,emailStatus);
		}
		if (!Utility.isEmpty(prevEmailStatus) && !Utility.isEmpty(emailStatus)) {
			if (!prevEmailStatus.equalsIgnoreCase(emailStatus)) {
				feedback=emailStatus;
				ui.setSessionAttribute(Constant.PREVIOUS_EMAIL_SEND_STATUS,emailStatus);
			} else {
				feedback=Constant.FEEDBACK_NODATA;
			}
		}
		if (!Utility.isEmpty(emailStatus)) {
			if (emailStatus.indexOf("Successfully send to All recipients")!=-1) {
				feedback=Constant.FEEDBACK_COMPLETED;
			}
		}
		if (!Utility.isEmpty(emailStatus)) {
			if (emailStatus.indexOf("Error Occured, Terminate the sending!")!=-1) {
				feedback=Constant.FEEDBACK_TERMINATED;
			}
		}
		
		Gson gson = new Gson();
	    JsonObject myObj = new JsonObject();
        JsonElement curElement = gson.toJsonTree(feedback);
        myObj.add("feedback", curElement);	        
        out.println(myObj.toString());        
        out.close(); 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			
		}
		
	    LOG.info("viewEmailSendStatusHandler end");
		return null;
	}
}
