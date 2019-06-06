package com.email.list.reader;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger; 

import com.loan.agent.mvc.utils.Utility;

public class ParserEmailListOnPlainText {
	public static Logger LOG  =Logger.getLogger(ParserEmailListOnPlainText.class); 
	private static final String delimitor[] = {"\\|",";","\",\"","\',\',","(","<"};
	
	public static List<SimpleEmailLineVO> getPlainTextEmailList(String pathFile) {
		List<SimpleEmailLineVO> list = new ArrayList<SimpleEmailLineVO>();
		InputStream fis=null;
		BufferedReader br = null;
		try
        {
			File file = new File(pathFile);
			if (!file.exists()) {
				LOG.error("File can not be openned on path:"+pathFile);
				return null;
			}
		    
			fis = new FileInputStream(file);
			list= getPlainTextEmailListFromStream(fis);	    	
			 
          }  catch (Exception e)
		  {
		       LOG.error("Read Excel file failure, message is "+e.getMessage());
		        return null;
		  } finally {
		   	try {        		
		       		 
		       		fis.close();
		        		
		       	} catch (Exception e) {
		       		 LOG.error("Read Excel File, normall Close failure, message is "+e.getMessage());
		       		 return null;
		       	}
		  }
	 
		  return list;
	}
	public static List<SimpleEmailLineVO> getPlainTextEmailListFromStream(InputStream fis) {
		List<SimpleEmailLineVO> list = new ArrayList<SimpleEmailLineVO>();
		List<EmailDynamicVO> dyList = new ArrayList<EmailDynamicVO>();
    	BufferedReader br = null;
		 DataInputStream in = null;
		try
        {
			  
			 in = new DataInputStream(fis);
			  
			 br = new BufferedReader(new InputStreamReader(in));
	 
		 
		    int rowCount=0;
		    String line=null;
		    int totalCols=0;
		    while ((line=br.readLine())!=null) {	
		    	 
		    	SimpleEmailLineVO vo = parserEmailLine(line);
		    	
		    	
		    	if (vo!=null && vo.isValid()) {
		    		vo.setLineId(new Integer(rowCount+1).toString());
		    		String oldFirstName = SimpleEmailLineVO.rltrim(vo.getFirstName());	
		    		int spos = oldFirstName.indexOf(" ");
		    		
		    	
		    	   if (spos!=-1) {
		    			String name[] = oldFirstName.split(" ");
		    			vo.setFirstName(SimpleEmailLineVO.rltrim(name[0]));
		    			if (name.length>0) {
		    				vo.setLastName(SimpleEmailLineVO.rltrim(name[1]));
		    			}
		    		}
		    	   if (rowCount<10) {
		    		    EmailDynamicVO dvo = new EmailDynamicVO();
		    		    totalCols=0;
			    		if (!Utility.isEmpty(vo.getFirstName())) {
			    			 dvo.addCol(vo.getFirstName());
			    			 totalCols++;
			    		}
			    		if (!Utility.isEmpty(vo.getLastName())) {
			    			 dvo.addCol(vo.getLastName());
			    			 totalCols++;
			    		}
			    		if (!Utility.isEmpty(vo.getEmailAddress())) {
			    			 dvo.addCol(vo.getEmailAddress());
			    			 totalCols++;
			    		}
			    		dvo.setTotalCols(totalCols);
			    		dyList.add(dvo);
			    		 
		    	   }
		    	   list.add(vo);
		    	}
		    	rowCount++;
		    	
			}
		    if (list.size()>0) {
		    	list.get(0).setDynaList(dyList);
		    }
          }  catch (Exception e)
		  {
		       LOG.error("Read Excel file failure, message is "+e.getMessage());
		        return null;
		  } finally {
		   	try {        		
		       		br.close(); 
		       		in.close();
		        		
		       	} catch (Exception e) {
		       		 LOG.error("Read Excel File, normall Close failure, message is "+e.getMessage());
		       		 return null;
		       	}
		  }
	 
		  return list;
	}	
	
	public static SimpleEmailLineVO parserEmailLine(String emailLine) {
		SimpleEmailLineVO vo = new SimpleEmailLineVO();
		/**
		 *  Check if @ symbol exists or not
		 */
		
		String line[] = emailLine.split("@");
		if (line.length<=1) {
			return vo;
		} else {
			/**
			 *  temporary valid is true
			 */
			vo.setValid(true);
		}
		String front = line[0];
		String tail = line[1];
		/**
		 *  Check if '.' exists in email side or not
		 */
		String tailValid[]=tail.split("\\.");
		if (tailValid.length<=1) {
			
			vo.setValid(false);
			return vo;
		}
		/**
		 *  From @ symbol to left , find ' ', ',' or '\t' to get email address 
		 */
		int at = emailLine.indexOf("@");
		int len = emailLine.length();
		char strChar[] = emailLine.toCharArray();
		int i=0;
		for (i=at;i>0;i--) {
			if (strChar[i]==' ' || strChar[i]==','|| strChar[i]=='\t'|| strChar[i]=='|' || strChar[i]=='(' || strChar[i]=='<') {		
			 
			  i++;
			  break;	
			}
		}
	    String emailAddress = emailLine.substring(i,len);
	    String email = emailAddress.replace("\"", "").replace("\'", "").replace(")", "").replace(">", "");
	    email=findEmail(SimpleEmailLineVO.rltrim(email));
	    
	 	    
		vo.setEmailAddress(SimpleEmailLineVO.rltrim(email));
		
		/**
		 *  If no First Name, Middle Name, Last name, i must be zero
		 */
		if (i==0) {
			 //System.out.println("i==0 front="+emailLine.substring(0,at));
			vo.setFirstName(emailLine.substring(0,at));
			vo.setLastName(emailLine.substring(0,at));
			return vo;
		} else {
			front = emailLine.substring(0,i-1);
			/**
			 *  From email first character -1 location to right, find ' ', ',' or '\t' to continue otherwise find names part
			 */
			 int j =0 ;
			 for (j=i;j>0;j--) {
				 if (strChar[j]==' ' || strChar[j]==',' || strChar[j]=='\t' || strChar[i]=='|' || strChar[i]=='(' || strChar[i]=='<') {	 
				//if (checkDilimitor(strChar[j],delimitor)) {
					 continue;
				 }
				 front = SimpleEmailLineVO.rltrim(emailLine.substring(0,j));
				 break;
			 }
			 /**
			  *  If find ',' in names part, last one is first name, first one is last name
			  */
			 front = front.trim();
			 front = front.replace("\"", "").replace("\'","");
			 len = front.length();
			 
			 
			 if (front.endsWith(",") || front.endsWith("(") || front.endsWith("<")) {
				 front = front.substring(0,len-1);
				 
			 }
			// System.out.println("begin front="+front);
			 String names[] = front.split(",");
			 int ln = names.length;
			 if (ln==1) {
				 /**
				  *  if no ',' find, check space between names parts, first one is first name , last one is last name
				  */
				 
				 for (int k=0; k<delimitor.length; k++) {
					 SimpleEmailLineVO tmp =  getEmailNamesByDelimitor(front, delimitor[k]);
					 if (tmp.isValid()) {
						 vo.setFirstName(tmp.getFirstName());
						 vo.setMiddleName(tmp.getMiddleName());
						 vo.setLastName(tmp.getLastName());
						 return vo;
					 }
				 }
				 
			 } else if (ln==2) {
				 /**
				  *  two names parts delimited by ',', first one is last name, last one is first name 
				  */
				// System.out.println("ln==2 front="+front);
				 vo.setFirstName(names[0]);
				 vo.setLastName(names[1]);
			 } else if (ln==3) {
				 /**
				  *  three names parts delimited by ' ', first one is last name, second one is middle name, last one is first name 
				  */
				// System.out.println("ln==3 front="+front);
				 vo.setFirstName(names[0]);
				 vo.setMiddleName(names[1]);
				 vo.setLastName(names[2]);
			 }
		}
		
		return vo;
	}
	
	public static SimpleEmailLineVO getEmailNamesByDelimitor(String front, String delimitor) {
		 SimpleEmailLineVO vo = new SimpleEmailLineVO();
		 String spaceNames[] = front.split(delimitor); 
		
		 int ln2 = spaceNames.length;
		 if (ln2==1) {
			 /**
			  *  no ' ' and ',' between names parts, names part is first name
			  */
			 //System.out.println("ln2==1 front="+front);
			 vo.setFirstName(front);
			 vo.setLastName(front);
			 vo.setValid(true);
			 return vo;
		 } else if (ln2==2) {
			 /**
			  *  two names parts delimited by ' ', first one is first name, last one is last name 
			  */
			 //System.out.println("ln2==2 front="+front);
			 vo.setFirstName(spaceNames[0]);
			 vo.setLastName(spaceNames[1]);
			 vo.setValid(true);
			 return vo;
		 } else if (ln2==3) {
			 /**
			  *  three names parts delimited by ' ', first one is first name, second one is middle name, last one is last name 
			  */
			 //System.out.println("ln2==3 front="+front);
			 vo.setFirstName(spaceNames[0]);
			 vo.setMiddleName(spaceNames[1]);
			 vo.setLastName(spaceNames[2]);
			 vo.setValid(true);
			 return vo;
		 }
		 return vo;
	}
    public static String findEmail(String oldEmail) {
    	oldEmail = SimpleEmailLineVO.rltrim(oldEmail);
    	
    	int spos = oldEmail.indexOf(" ");
    	if (spos==-1) {
    		spos = oldEmail.indexOf(",");
    	}
    	if (spos!=-1) {
    		oldEmail = oldEmail.substring(0,spos);
    	}
    	
	    return oldEmail;
    }
	public static void main(String args[]) {
		String line[] ={"john,johnz148yahoo.com","Dennie,denniecha@yahoo.com",
				"yinhe, cao,    yinhecao@yahoo.com","Suresh,Aiyer,saiyer@sonicwall.com",
				" Scott,    	    Eikenberry,  scotte@SonicWALL.com",
				"jzhang@sonicwall.com", "Jian, Yang, yangJian@yahoo.com",
				"bhuvan@sonicwall","Catherine, Zeta, Jones, czetaJones@gmail.com",
				"Gorege, W, Push, gwPush@gmail.com"," Rabort   ,Delino  , 	delinoRabort@gmail.com",
				"Smith | zer | lee | lzsmith@gmail.com","\"Jing, Lan\",\"jlan@sonicwall.com\"",
				"\'Jing, Lan\',\'jlan@sonicwall.com\'","Christine, Lee<leeChrist@sonicwall.com>",
				"Christine, Lee(leeChrist@sonicwall.com)","\"Christine, Lee\"(leeChrist@sonicwall.com)",
				" Wilson and Grance, Smith,wgsmith@gmail.com"
		};
		
		for (int i=0;i<line.length;i++) {
			SimpleEmailLineVO vo = parserEmailLine(line[i]);
			System.out.println(line[i]+" will be parsed to Valid:"+vo.isValid()+",first name:"+vo.getFirstName()
					                  +",middle name="+vo.getMiddleName()+",last name:"+vo.getLastName()+",email:"+vo.getEmailAddress());	
			
		}
	}
}
