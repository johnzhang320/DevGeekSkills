package com.loan.agent.mvc.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.loan.agent.dao.Agents;
import com.loan.agent.mvc.controller.UploadDocFileController;
import com.loan.agent.services.Constant;

public class ProcessUploadFile {
	
	static Logger LOG = Logger.getLogger( ProcessUploadFile.class);
	/**
	 * fileReaderOnly Read Email List File (text) file only without saving
	 * @param fileContent
	 * @param request
	 * @return
	 */
	
	public static Files fileReaderOnly(String fileContent,HttpServletRequest request) {
		Files files = new Files();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
  	     
		MultipartFile multipartFile = multipartRequest.getFile(fileContent);
		
		if (multipartFile==null) {
			LOG.info("fileContent may be wrong!") ;
			return null;
		}
		
		
		String origName = multipartFile.getOriginalFilename();
		int len = origName.length();
		String fileExt = origName.substring(len-3);
		String contentType = fileExt ;
		LOG.info("file type = "+ contentType+",origFilename="+multipartFile.getOriginalFilename()+",fileExt="+fileExt) ;
		
		try {
			 
			files.setFile(multipartFile.getBytes());
			files.setFilename(multipartFile.getOriginalFilename());
			files.setType(multipartFile.getContentType());
			files.setFileExt(fileExt);
			if (contentType.startsWith("txt") || contentType.startsWith("csv"))  {				 
			 
				LOG.info("Read Line by Line from InputStream!");
				InputStream fstream = multipartFile.getInputStream();
			 
				DataInputStream in = new DataInputStream(fstream);
				 
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				 
				  String strLine;
				  StringBuffer buf = new StringBuffer();
				  //Read File Line By Line
				  int notEmailcount=0;
				  int total=0;
				  List<String> list = new ArrayList<String> (); 
				  while ((strLine = br.readLine()) != null)   {
					  if (strLine==null || strLine.trim().length()==0) {
						  continue;
					  }
				     // Print the content on the console
					  strLine =strLine.replace("&Acirc;&#160;", "");
				      buf.append(strLine+"\n");
				      if (strLine.indexOf("@")==0) {
				    	  notEmailcount++;			
				    	  list.add(strLine);
				      }
				     //LOG.info(strLine);
				      total++;
				      
				  }
				  LOG.info("total line =" + total+",error line="+notEmailcount);
				  if (total==0) {
					  files.setAssciContent(null); 
					  files.setEmailListFile(false);
				  }
				  double percent = (double) (notEmailcount*100)/total;
				  if (percent>50.0) {
					  files.setEmailListFile(false);
				  } else {
					  files.setWrongEmails(list);
					  LOG.info("Set Stream Content now!");
					  files.setEmailListFile(true);
					  files.setAssciContent(buf.toString()); 
				  }
				  
				
			} else if (contentType.startsWith("xls")) {
			      InputStream fstream = multipartFile.getInputStream();
				  String buffer = ReadExcelFile.read(fstream);
				  files.setEmailListFile(true);
				  files.setAssciContent(buffer); 
			}
			
		} catch (Exception e) {
			 
			LOG.info(" fileReade exception !"+e.getMessage()) ;
			return null;
		}
		  
		return files;
	}
	

	/**
	 * emailContentReaderOnly Read Email Content File (text and html) file only without saving
	 * @param fileContent
	 * @param request
	 * @return
	 */
	
	public static Files emailContentReadOnly(String fileContent,HttpServletRequest request) {
		Files files = new Files();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
  	     
		MultipartFile multipartFile = multipartRequest.getFile(fileContent);
		
		if (multipartFile==null) {
			LOG.info("fileContent may be wrong!") ;
			return null;
		}
		
		String contentType = multipartFile.getContentType();
		LOG.info("file type = "+ contentType) ;
		try {
			files.setFilename(multipartFile.getContentType());
			files.setFile(multipartFile.getBytes());
			files.setFilename(multipartFile.getOriginalFilename());
			files.setType(contentType);
			if (contentType.startsWith("text") || contentType.startsWith("html") )  {				 
			 
				LOG.info("Read Line by Line from InputStream!");
				InputStream fstream = multipartFile.getInputStream();
			 
				DataInputStream in = new DataInputStream(fstream);
				 
				  BufferedReader br = new BufferedReader(new InputStreamReader(in));
				 
				  String strLine;
				  StringBuffer buf = new StringBuffer();
				   
				  List<String> list = new ArrayList<String> (); 
				  while ((strLine = br.readLine()) != null)   {
					  if (strLine==null || strLine.trim().length()==0) {
						  continue;
					  }
				      
				      buf.append(strLine+"\n");			       
				      
				  }
				  String retStr = buf.toString();
				  retStr  = ui.deCodeHTML(retStr);
				  LOG.info("retStr="+retStr);
				  files.setAssciContent(retStr); 
			}
			
		} catch (Exception e) {
			 
			LOG.info(" fileReade exception !"+e.getMessage()) ;
			return null;
		}
		  
		return files;
	}
	
	public static boolean isDocument(String contentType) {
		boolean retVal=false;
		if (Constant.TEXT_HTML_CONTENT.equalsIgnoreCase(contentType) ||
				Constant.TEXT_PLAIN_CONTENT.equalsIgnoreCase(contentType) ||
				Constant.TEXT_APPL_CONTENT.equalsIgnoreCase(contentType)) {
			retVal=true;
		}
		return retVal;
	}
	
	public static Files fileReaderAndWriter(String fileContent,String destFile, HttpServletRequest request, boolean deleteDestFile) {
		Files files = new Files();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
  	     
		MultipartFile multipartFile = multipartRequest.getFile(fileContent);
		
		if (multipartFile==null) {
			Log.info("fileContent may be wrong!") ;
			return null;
		}
		
		String contentType = multipartFile.getContentType();
		try {
			files.setFilename(multipartFile.getContentType());
		 
			files.setFilename(multipartFile.getOriginalFilename());
			files.setType(contentType);
			File file = new File(destFile); 
			multipartFile.transferTo(file); 
			
			byte[] fileContents = getImageByteArrayByFile(destFile);
			
			if (fileContents!=null) {
				files.setFile(fileContents);
			}
			if (deleteDestFile) {
				file.delete();
			}
		} catch (Exception e) {
			Log.info(" fileReaderAndWriter exception !"+e.getMessage()) ;
			return null;
		}
		
		return files;
	}
	
	
	public static Files WriterOriginalFileNameToServer(String fileContent,String destPath, HttpServletRequest request, boolean deleteDestFile) {
		Files files = new Files();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
  	     
		MultipartFile multipartFile = multipartRequest.getFile(fileContent);
		
		if (multipartFile==null) {
			Log.info("fileContent may be wrong!") ;
			return null;
		}
		
		String contentType = multipartFile.getContentType();
		try {
			files.setFilename(multipartFile.getContentType());
			files.setFile(multipartFile.getBytes());
			files.setFilename(multipartFile.getOriginalFilename());
			files.setType(contentType);
			LOG.info("Dest File="+destPath+multipartFile.getOriginalFilename());
			File file = new File(destPath+multipartFile.getOriginalFilename()); 
			multipartFile.transferTo(file); 		
			 
			if (deleteDestFile) {
				file.delete();
			}
		} catch (Exception e) {
			Log.info(" fileReaderAndWriter exception !"+e.getMessage()) ;
			return null;
		}
		
		return files;
	}
	public static void deleteAttachmentFile(String destPath,String orginalFile)  {
	      File file = new File(destPath+orginalFile);
	      if (file.exists()) {
	    	  file.delete();
	      }
	}
	public static boolean isAllowedEmailFileType(String type) {
		return ("txt".equalsIgnoreCase(type) || "csv".equalsIgnoreCase(type) || "xls".equalsIgnoreCase(type) );
	}	
	
	
	public static void deleteAgentFile(Integer AgentId)  {		
		  
		  String [] extName={".jpg",".png",".gif",".jepg",".tiff","bmp"};	     
		 for (int i = 0 ; i<extName.length;i++) {   
			 String uploadToFile = SysPath.getInstance().getAgentPicturePath()+Constant.PREFIX_AGENT_PICTURE_FILE+AgentId.toString()+extName[i];
		  
			 File file = new File(uploadToFile);
			 if (file.exists()) {
				 file.delete();
			 }
		 }
	}
	
 
	 
	
	public static Files uploadCodedFile(String codeId, String fileContent,String filePrefix,String savePath, HttpServletRequest request ) {
		
		  LOG.info("ProcessUploadFile.uploadCodedFile() begin!");
		  LOG.info("ProcessUploadFile.uploadCodedFile() fileContent="+fileContent);
		   
		  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		     
		  MultipartFile multipartFile = multipartRequest.getFile(fileContent);		     
		  
		  LOG.info("ProcessUploadFile.uploadCodedFile() : check   multipartFile="+multipartFile);
		   
		  if (null==multipartFile || multipartFile.isEmpty() || null== multipartFile.getName() || 0==multipartFile.getName().trim().length()) {
		   	 LOG.info("ProcessUploadFile.uploadCodedFile() : uploadAgentImage().multipartFile==null exit from agentSignupProcess....");
		   	 return null;
		  }
		  String orgFile = multipartFile.getOriginalFilename().replace(" ", "-");
		  
		  LOG.info("ProcessUploadFile.uploadCodedFile() multipartFile.getOriginalFilename()="+orgFile);
		  if ( null==orgFile || orgFile.trim().length()==0) {
			  LOG.info("multipartFile.getOriginalFilename()==null, not selected image file this time, keep old one  exit from agentSignupProcess....");
			  return null;
		  }
			  
		  String contentType =   multipartFile.getContentType();		  		  
		  
		  String fileExt =Utility.getFileExtention(orgFile);
		    
		  String uploadToFile = savePath+filePrefix+codeId+fileExt;
		  
		  LOG.info("File Extension="+fileExt+", uploadToFile="+uploadToFile);		     
		
		    		 
		  File file = new File(uploadToFile);
		     
		//  LOG.info("Save picture begin .....");
		  Files files = new Files();
		  try {   
			      /**
			       *  delete all potential original files under this agent id
			       */
			   
				 if (file.exists()) {
				  	  LOG.info("ProcessUploadFile.uploadCodedFile() Delete current file:"+file.getAbsolutePath());
				  	
					 file.delete();
				 }
			      
			  	  files.setFile(multipartFile.getBytes());
			  	  files.setFilename(multipartFile.getOriginalFilename());
			  	  files.setType(multipartFile.getContentType());
			  	  files.setFilesize(multipartFile.getSize());	
			  	  LOG.info("ProcessUploadFile.uploadCodedFile() Save current file:"+orgFile+" to Dest File ="+ uploadToFile);
				  multipartFile.transferTo(file);
			  } catch (Exception e) {
				  LOG.info("Error to save picture "+e.getMessage());
				  return null;
			  }
		//  LOG.info("ProcessUploadFile.uploadCodedFile() end!");
		  return files;
    
	}
	
 
	
	
	
 
	
	 
	public static byte[] getFileByteArrayByAgent(Integer AgentId,String orgFile,String prefix, String storedPath) {
		//LOG.info("getFileByteArrayByAgent begin");
		 if (AgentId==null) {			 
			 LOG.info("getFileByteArrayByAgent AgentId="+AgentId);
			 return null;
		 }
		 String fileExt = Utility.getFileExtention(orgFile);		 
		  
		 String uploadToFile =null;
		 boolean found = false; 
		 
		 uploadToFile = storedPath+prefix+AgentId.toString()+fileExt;
		 //LOG.info("getAgentImageByteArray():uploadToFile="+uploadToFile); 
		 File file = new File(uploadToFile);
		 if (file.exists()) {
			//LOG.info("getAgentImageByteArray(): Found uploadToFile="+uploadToFile); 
			found=true;
				 
		} else {
			LOG.info("getAgentImageByteArray(): Not Found uploadToFile="+uploadToFile); 
			found=false;
		}
		 
	//	LOG.info("getAgentImageByteArray end");
		if (found) {
		  return getImageByteArrayByFile(uploadToFile);
		}
		return null;
	}
	
	 
	
	public static byte[] getImageByteArrayByFile(String origfile) {
		//LOG.info("getImageByteArray begin");
		 
		byte[] bytes=null;
		
		
		File file= null;
		 
		
		file= new File(origfile);
		if (!file.exists()) {
		     LOG.info("Can not find the picture file! ");
		     return null;
	    }
		 
		 
		 FileInputStream fis=null;
		 ByteArrayOutputStream bos=null;
		 
		 try {
			   bos = new ByteArrayOutputStream();
		       byte[] buf = new byte[1024];
		       
		       fis= new FileInputStream(file);
		       
		       for (int readNum; (readNum = fis.read(buf)) != -1;) {
		          //Writes to this byte array output stream
		           bos.write(buf, 0, readNum); 
		       }
		       bytes = bos.toByteArray();       
		      
		 } catch (Exception e) {
			 LOG.info("Error read image byte! "+e.getMessage());
		 } finally {
			 if (fis!=null)
			 try {
				 fis.close();
				 bos.close();
			 } catch (Exception e)
			 {}
		 
		 }
		// file.delete();
		// LOG.info("getImageByteArray end");
		 return bytes;
	}
}
