package com.send.email.smtp.ssl;

 

import javax.servlet.http.HttpServletRequest;

 
import org.apache.log4j.*;

import com.loan.agent.calculators.vo.EmailLineVO;
import com.loan.agent.mvc.utils.ParserEmailList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class eUtil {
	static Logger LOG = Logger.getLogger(eUtil.class);
	private static HttpServletRequest request;
	
	private static Pattern pattern;
	private static Matcher matcher;
 
	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
	 
	
	public static boolean isNotEmpty(String str) {
		if (str==null || str.trim().length()<5 || str=="") {
			return false;
		}
		return true;
	}
	
	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public static boolean isValidEmail(final String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches(); 
	}
	/**
	 * processEmailLine()
	 * Author Jian Zhang
	 * 
	 * Analysis and valid Email Line and set a Recipient Object
	 * @param emailLine
	 * @return
	 */
		
	public static RecipientVo processEmailLine(final String emailLine) {
		if (!isNotEmpty(emailLine)) {
			LOG.info("processEmailLine email line is empty "+emailLine);
			return null;
		}
	 
		 EmailLineVO lvo= ParserEmailList.parserEmailLine(emailLine);
		 
		 if (!lvo.isValid()) {
			 return null;
		 }
		 RecipientVo vo =null;    
		 
		 vo =new RecipientVo();
		 String fullName = lvo.getFirstName()+" "+lvo.getMiddleName()+" "+lvo.getLastName();
		 vo.setRecipientFullName(fullName);
		 vo.setRecipientFirstName(lvo.getFirstName());
		 vo.setRecipientMiddleName(lvo.getMiddleName());
		 vo.setRecipientLastName(lvo.getLastName());
		 vo.setRecipientEmailAddress(lvo.getEmailAddress());
		return vo;    
	}
	
	public static RecipientVo setRecipientPersonalName(RecipientVo vo,String fullName) {
		
		if (fullName==null) {
			return vo;
		} 
		fullName = fullName.trim();
		
		String firstLast[]=null;
		if (fullName.indexOf(" ")!=-1) {
			firstLast = fullName.split(" ");
			if (firstLast.length==2) {
				vo.setRecipientFirstName(firstLast[0]);
				vo.setRecipientLastName(firstLast[1]);
			} 
			if (firstLast.length==3) {
				vo.setRecipientFirstName(firstLast[0]);
				vo.setRecipientMiddleName(firstLast[1]);
				vo.setRecipientLastName(firstLast[2]);
			}
				vo.setRecipientFullName(fullName);
		}
		if (fullName.indexOf(",")!=-1) {
			firstLast = fullName.split(",");
			if (firstLast.length==2) {
		    	vo.setRecipientFirstName(firstLast[1]);
			    vo.setRecipientLastName(firstLast[0]);
			}
			if (firstLast.length==3) {
				vo.setRecipientFirstName(firstLast[2]);
				vo.setRecipientMiddleName(firstLast[1]);
			    vo.setRecipientLastName(firstLast[0]);
			}
			vo.setRecipientFullName(fullName.replace(",", " "));
		}
		return vo;
		
	}
	 
	/**
	 * generateRecipientVoList()
	 * Parse Email List String , delimiting each email line by '\n', add each line into List<RecipientVo>
	 * @param emailListString : delimited each email line by '\n', suppose all email lines are validated
	 * @return
	 */
    
	public static List<RecipientVo> generateRecipientVoList(String emailListString) {
		
		List<RecipientVo> list = new ArrayList<RecipientVo>(); 
		if (!isNotEmpty(emailListString)) {
			LOG.info("emailListString is empty!");
			return null;
		}
		String allLines[] = emailListString.split("\n");
		if (allLines.length==1) {
			LOG.info("emailListString is one line!");
			RecipientVo vo = processEmailLine(emailListString);
			if (vo!=null) {
				list.add(vo);
			}
			return list; 
		}
		
		for (int i=0;i<allLines.length;i++) {
			LOG.info("allLines["+i+"]=" +allLines[i]);		
			RecipientVo vo = processEmailLine(allLines[i].replace("\n", ""));
	 			
			if (vo!=null) {
				//LOG.info("Recipient Name="+vo.getRecipientFullName()+",Recipient Email Address="+vo.getRecipientEmailAddress());
			 
				list.add(vo);
			} else {
				LOG.info("Failed Recipient vo="+vo);
			}
		}
		return list;
	}
	
	 
	
	public static void main(String args[]) {
		 RecipientVo vo =  null;
		 String email = null;
		email = "john.zhang320@gmail.com" ;
		System.out.println("email "+email+" is "+ isValidEmail(email));
		
		 email = "johnz148@yahoo.com" ;
		System.out.println("email "+email+" is "+ isValidEmail(email));
		
		 email = "john.zh#ang320gmail.com.cn" ;
			System.out.println("email "+email+" is "+ isValidEmail(email));
			
			 email = "john.zh(an&g320gmail.com.cn" ;
				System.out.println("email "+email+" is "+ isValidEmail(email));
				
		 email = "<John Zhang> john.zhang320@gmail.com.cn" ;
		     processEmailLine(email);
		 
		 email = "John   Zhang  ,   john.zhang320@gmail.com.cn" ;
		 processEmailLine(email);  
		 
		 email = "Zhang, Jian  ;   john.simicolorzhang320@gmail.com.cn" ;
		 processEmailLine(email);  
		 email = "john.zhang320@gmail.com.cn" ;
		 vo = processEmailLine(email);  
		 System.out.println("case 1 email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
				 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName());
		 
		 email = "john zhang , john.zhang320@gmail.com.cn" ;
		  vo = processEmailLine(email);  
		 System.out.println("case 2 email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
				 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName());
		 
		 email = "john zhang ; john.zhang320@gmail.com.cn" ;
		 vo = processEmailLine(email);  
		 if (vo!=null) {
		    System.out.println("case 3. email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
					 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName());
	     }
		 
		 email = "john.zhang320@gmail.com.cn;john zhang" ;
		 vo =processEmailLine(email);  
		 if (vo!=null) {
		    System.out.println("case 4. email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
					 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName());
	     }
		 email = "dennie, cha;dennie.cha@financial.com" ;
		 vo = processEmailLine(email);  
		 if (vo!=null) {
		    System.out.println("case 5. email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
					 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName());
	     }
		 email = "suresh, yar, saiyar@sonicwall.com.com";
		 vo =processEmailLine(email);  
		 if (vo!=null) {
		    System.out.println("case 6. email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
					 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName());
	     }
		 email = "\"suresh, yar\", saiyar@sonicwall.com.com";
		 vo =processEmailLine(email);  
		 if (vo!=null) {
		    System.out.println("case 7. email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
					 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName());
	     }
		 email = "<suresh, yar>, saiyar@sonicwall.com.com";
		 vo =processEmailLine(email);  
		 if (vo!=null) {
		    System.out.println("case 8. email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
					 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName());
	     }
		 email = "suresh, yar; saiyar@sonicwall.com.com";
		 vo =processEmailLine(email);  
		 if (vo!=null) {
		    System.out.println("case 9. email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
					 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName());
	     }
		 email = "saiyar@sonicwall.cn, suresh, yar";
		 vo =processEmailLine(email);  
		 if (vo!=null) {
		    System.out.println("case 10  email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
					 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName());
	     }
		 email = "'suresh, yar', saiyar@sonicwall.com.com";
		 vo =processEmailLine(email);  
		 if (vo!=null) {
		    System.out.println("case 11. email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
					 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName());
	     }
		 
		 email = "Gorege,w,bush	goerge-w-bush@sonicwall.com.com";
		 vo =processEmailLine(email);  
		 if (vo!=null) {
		    System.out.println("case 12. email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
					 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName());
	     }
		
		 email = "Gorege,w,bush	(goerge)wbu$sh@sonicwall.com.com";
		 vo =processEmailLine(email);  
		 if (vo!=null) {
		    System.out.println("case 13 . email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
					 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName());
	     } else {
	    	 System.out.println("case 13 . wrong email line: "+  email);
	     }
		 email = "Lou,Goerge,goerge-lou@baycalfinancial.com";
		 vo =processEmailLine(email);  
		 if (vo!=null) {
		    System.out.println("case 14. email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
					 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName());
	     }
		 
		 email = "dennie cha, dennie.cha@baycalfinancial.com";
		 vo =processEmailLine(email);  
		 if (vo!=null) {
		    System.out.println("case 15. email is "+vo.getRecipientEmailAddress()+" greeting name is "+ vo.getRecipientFullName()
					 +",firstName="+vo.getRecipientFirstName()+",lastName="+vo.getRecipientLastName()+",middle name="+vo.getRecipientMiddleName());
	     } else {
	    	 System.out.println("case 15 vo=null");
	     }
		 
	}
	
	 
}

