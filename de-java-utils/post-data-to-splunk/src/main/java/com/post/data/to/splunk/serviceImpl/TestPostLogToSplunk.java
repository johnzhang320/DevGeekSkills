package com.post.data.to.splunk.serviceImpl;
 
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.log4j.Logger;
import org.junit.Test;
 
 
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.post.data.to.splunk.model.ResultVO;
import com.post.data.to.splunk.model.SplunkEventVO;
import com.post.data.to.splunk.model.SplunkPostingDataVO;
import com.post.data.to.splunk.model.TaskFieldsVO;

public class TestPostLogToSplunk {
	private static Logger Log = Logger.getLogger(TestPostLogToSplunk.class);
 	@Test
 	public void GetSplunkConnection() throws Exception {
 		String earliest = "-24h@h"; //"-2h@h"; //"-2h@h";//to replace
		String latest = "-1h@h"; //"-1h@h";//to replace
		String searchString="index=pay* sourcetype=\"ssl_cert_info\"";
 		HttpsURLConnection conn= GetSplunkHttpsURLConnection2(searchString,earliest,latest);
 		int statusCode = conn.getResponseCode(); 
		Log.info("Response Status Code="+statusCode);
		 
	   if (statusCode==200) {
	    	   Log.info("Response OK");
	    		BufferedReader 	br = new BufferedReader(
		 			new InputStreamReader(conn.getInputStream()));
		 	String line=null;
		 	StringBuffer buf = new StringBuffer();
		 	buf.append("Response content:");
		 	while((line=br.readLine())!=null) {
		 		buf.append(line);
		 	}
		 	 
		 	Log.info(buf.toString());
		 	br.close();
	    }
 	}
	//@Test
	public void PostLogToSplunk() {
		
		HttpsURLConnection conn= GetSplunkHttpsURLConnection();
		try {
			conn.connect();
			
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			
			TaskFieldsVO task_fields = new TaskFieldsVO();
			task_fields.setAction("shell");
			task_fields.setAlways_run(null);
			task_fields.setAny_errors_fatal(false);
			 
			ResultVO result = new ResultVO();
			result.set_ansible_no_log(false);
			result.setChanged(false);
			result.setSkip_reason("Conditional result was changed");
			
			SplunkEventVO event = new SplunkEventVO();
			event.setFailed(true);
			event.setPlaybook_file("/Users/intelliswift/ankpython/sre-ansible/playbooks/splunk/deploy.yml");
			event.setResult(result);
			event.setTask_fields(task_fields);
			event.setTask("TASK: Running Apple Pay CME Tools");

			SplunkPostingDataVO vo = new SplunkPostingDataVO();
			vo.setEvent(event);
			vo.setHost("	nc1-ios-pt05-p-app014.corp.apple.com");
			vo.setSource("/Users/intelliswift/ankpython/sre-ansible/playbooks/splunk/get_version.yml");
			String postData = getJSONStringPostRequest(vo);
			
			wr.writeBytes(postData);
			
		    wr.flush();
		    wr.close();
		    int statusCode = conn.getResponseCode(); 
		    Log.info("Response Status Code="+statusCode);
		    if (statusCode==200) {
		    	   Log.info("Response OK");
		    		BufferedReader 	br = new BufferedReader(
			 			new InputStreamReader(conn.getInputStream()));
			 	String line=null;
			 	StringBuffer buf = new StringBuffer();
			 	buf.append("Response content:");
			 	while((line=br.readLine())!=null) {
			 		buf.append(line);
			 	}
			 	 
			 	Log.info(buf.toString());
			 	br.close();
		    }
 		} catch(Exception e) {
			Log.error("Error:"+e.getMessage());
			e.getStackTrace();
		} 
	}
	
	public String getJSONStringPostRequest(SplunkPostingDataVO vo) {
		String retVal=null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			StringWriter stringEmp = new StringWriter();
	        objectMapper.writeValue(stringEmp, vo);
	        retVal=stringEmp.toString();
	        Log.info("POJO to JSON String:\n"+retVal);
		} catch (Exception e) {
            Log.error("POJO to JSON String error:"+e.getMessage());
            
		}
		return retVal;
	}
	
	//  https://splunk-ioss-us1.corp.apple.com/cgi-bin/callrestapi.pl
	//	String splunkAppIdKey = "270af80ba8ddae2a012424e3af0c7ab3a7f9259010aca26af4d4dcf4bb16440a";//used for DS user authentication to replace

 	public HttpsURLConnection GetSplunkHttpsURLConnection() {
		   String method="POST";
		  // String https_url="https://splunk-hec-uat.rno.apple.com";   // return 200 even no token
		  // String https_url="https://splunk-hec.corp.apple.com/services/collector"; // return 502 if no token and using UAT token, return 400 only if using token in Python
		   String https_url="https://splunk-hec.corp.apple.com/services/collector";
		   // String https_url="https://certificatemanager-dev.apple.com:9000/api/v1/reporting/reports/cert-report";  // return 401
		   Log.info("https URL="+https_url);
		   URL url;
		   HttpsURLConnection con =null;
		   try {
			   HttpsURLConnection.getDefaultSSLSocketFactory(); 

		 	    url = new URL(https_url);
		 	    con = (HttpsURLConnection)url.openConnection();

		 	    // Guard against "bad hostname" errors during handshake.
	             con.setHostnameVerifier(new HostnameVerifier() {
	                 public boolean verify(String host, SSLSession sess) {
	                	 return true;
	                   
	                 }
	             });
		         con.setRequestMethod(method);
		         con.setRequestProperty("Content-Type", "application/json");
		         con.setRequestProperty("Accept", "application/json");
		         con.setRequestProperty("Authorization", "Splunk "+ "A2C51CED-8EE8-4597-BF84-937D46A37C29");    // Token from Python Code
		         con.setDoOutput(true);
		 		 con.setDoInput(true);
	 			 con.setAllowUserInteraction(true);
	 			 
		         //con.connect();

	 	 } catch (MalformedURLException e) {
	 		e.printStackTrace();
	 	 } catch (IOException e) {
	 		e.printStackTrace();
	 	 } 
		 return con;
	}
 	
	public HttpsURLConnection GetSplunkHttpsURLConnection2(String searchString,String earliest, String latest) {
		   String method="GET";
		  // String https_url="https://splunk-hec-uat.rno.apple.com";   // return 200 even no token
		  // String https_url="https://splunk-hec.corp.apple.com/services/collector"; // return 502 if no token and using UAT token, return 400 only if using token in Python
		   //String https_url="https://splunk-hec.corp.apple.com/services/collector";
		   // String https_url="https://certificatemanager-dev.apple.com:9000/api/v1/reporting/reports/cert-report";  // return 401
		   String https_url="https://splunk-ioss-us1.corp.apple.com/cgi-bin/callrestapi.pl";
		   //https_url = "https://splunk-ioss-us1.corp.apple.com/en-US";
		   Log.info("https URL="+https_url);
		   URL url;
		   HttpsURLConnection con =null;
		   try {
			   HttpsURLConnection.getDefaultSSLSocketFactory(); 

		 	    url = new URL(https_url);
		 	    con = (HttpsURLConnection)url.openConnection();

		 	    // Guard against "bad hostname" errors during handshake.
	             con.setHostnameVerifier(new HostnameVerifier() {
	                 public boolean verify(String host, SSLSession sess) {
	                	 return true;
	                   
	                 }
	             });
		         con.setRequestMethod("POST");
		        // con.setRequestProperty("Content-Type", "application/json");
		        // con.setRequestProperty("Accept", "application/json");
		         con.setRequestProperty("Authorization", "Splunk "+ "A2C51CED-8EE8-4597-BF84-937D46A37C29");    // Token from Python Code
		         con.setDoOutput(true);
		 		 con.setDoInput(true);
	 			 con.setAllowUserInteraction(true);
	 			 
				 
				 con.setInstanceFollowRedirects(true);

				String searchStringEncoded = URLEncoder.encode(searchString, "UTF-8");
				
				String earliestEncoded = URLEncoder.encode(earliest, "UTF-8");
				String latestEncoded = URLEncoder.encode(latest, "UTF-8");
				
				String content = "searchString=" + searchStringEncoded+ "&earliest=" + earliestEncoded + "&latest="
						+ latestEncoded ;
				 

				DataOutputStream outSplunk = new DataOutputStream(con.getOutputStream());
				outSplunk.writeBytes(content);
				outSplunk.flush();
				outSplunk.close(); 
				 

	 	 } catch (MalformedURLException e) {
	 		e.printStackTrace();
	 	 } catch (IOException e) {
	 		e.printStackTrace();
	 	 } 
		 return con;
	}
}
