package com.send.email.smtp.ssl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.loan.agent.mvc.utils.Utility;

public class UpdateEnvelop {
	public static Logger LOG = Logger.getLogger(UpdateEnvelop.class);	
	private static HttpServletRequest request=null;
	
	public static HttpServletRequest getRequest() {
		return request;
	}

	public static void setRequest(HttpServletRequest request) {
		UpdateEnvelop.request = request;
	}

	public static List<RecipientVo> UpdateRecipicientList(String emailList) {
		List<RecipientVo> rList = new ArrayList<RecipientVo>();
		String [] line = emailList.split("\n");
		if (line==null || line.length==0) {
			LOG.info("There is no enter for email list, wrong!");
			return null;
		}
		for (int i=0;i<line.length;i++) {
			String currentLine = line[i].replace(";", ",");
			currentLine = currentLine.replace(" ","").trim();
			String [] emailLine = currentLine.split(",");
			String emailAddress = null;
			StringBuffer greeting = new StringBuffer();
			if (emailLine==null || emailLine.length==0) {
				if (currentLine.indexOf("@")==-1) {
					emailAddress = currentLine;
					String greetings[] = emailAddress.split("@");
					greeting.append(greetings[0]);
				}
				 
			} else {
				
				for (int j=0;j<emailLine.length;i++) {
					if (emailLine[j].indexOf("@")!=-1) {
						emailAddress = emailLine[j];
					} else {
						greeting.append(emailLine[j]+",");
					}
				}
			}
			if (emailAddress!=null) {
				RecipientVo vo = new RecipientVo();
				vo.setRecipientFullName(greeting.toString());
				vo.setRecipientEmailAddress(emailAddress);
				rList.add(vo);
			}
			
		}
		return rList;
		
	}
	public static void UpdateRecipicientList(HttpServletRequest request,String emailList) {
		UpdateEnvelop.request = request;
		UpdateRecipicientList(emailList);
	}
}
