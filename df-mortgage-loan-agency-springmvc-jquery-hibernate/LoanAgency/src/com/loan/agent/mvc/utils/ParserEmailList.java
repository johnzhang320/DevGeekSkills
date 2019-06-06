package com.loan.agent.mvc.utils;

import com.loan.agent.calculators.vo.EmailLineVO;

public class ParserEmailList {
	
	private static final String delimitor[] = {"\\|",";","\",\"","\',\',","(","<"};
	
	public static EmailLineVO parserEmailLine(String emailLine) {
		EmailLineVO vo = new EmailLineVO();
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
			if (strChar[i]==',' || strChar[i]=='\t'|| strChar[i]=='|' 
					|| strChar[i]=='(' || strChar[i]=='<' ||  strChar[i]==';') {		
			 
			  i++;
			  break;	
			}
		}
	    String emailAddress = emailLine.substring(i,len);
	    
		vo.setEmailAddress(emailAddress.replace("\"", "").replace("\'", "").replace(")", "").replace(">", ""));
		
		/**
		 *  If no First Name, Middle Name, Last name, i must be zero
		 */
		if (i==0) {
			 System.out.println("i==0 front="+emailLine.substring(0,at));
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
				 if (strChar[j]==',' || strChar[j]=='\t' || strChar[i]=='|' 
						 || strChar[i]=='(' || strChar[i]=='<' || strChar[i]==';') {	 
				//if (checkDilimitor(strChar[j],delimitor)) {
					 continue;
				 }
				 front = EmailLineVO.rltrim(emailLine.substring(0,j));
				 break;
			 }
			 /**
			  *  If find ',' in names part, last one is first name, first one is last name
			  */
			 front = front.trim();
			 front = front.replace("\"", "").replace("\'","");
			 len = front.length();
			 
			 
			 if (front.endsWith(",") || front.endsWith("(") || front.endsWith("<") || front.endsWith(";")) {
				 front = front.substring(0,len-1);
				 
			 }
			 System.out.println("begin front="+front);
			 String names[] = front.split(",");
			 int ln = names.length;
			 if (ln==1) {
				 /**
				  *  if no ',' find, check space between names parts, first one is first name , last one is last name
				  */
				 
				 for (int k=0; k<delimitor.length; k++) {
					 EmailLineVO tmp =  getEmailNamesByDelimitor(front, delimitor[k]);
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
				 System.out.println("ln==2 front="+front);
				 vo.setFirstName(names[0]);
				 vo.setLastName(names[1]);
			 } else if (ln==3) {
				 /**
				  *  three names parts delimited by ' ', first one is last name, second one is middle name, last one is first name 
				  */
				 System.out.println("ln==3 front="+front);
				 vo.setFirstName(names[0]);
				 vo.setMiddleName(names[1]);
				 vo.setLastName(names[2]);
			 }
		}
		
		return vo;
	}
	
	public static EmailLineVO getEmailNamesByDelimitor(String front, String delimitor) {
		 EmailLineVO vo = new EmailLineVO();
		 String spaceNames[] = front.split(delimitor); 
		
		 int ln2 = spaceNames.length;
		 if (ln2==1) {
			 /**
			  *  no ' ' and ',' between names parts, names part is first name
			  */
			 System.out.println("ln2==1 front="+front);
			 vo.setFirstName(front);
			 vo.setLastName(front);
			 vo.setValid(true);
			 return vo;
		 } else if (ln2==2) {
			 /**
			  *  two names parts delimited by ' ', first one is first name, last one is last name 
			  */
			 System.out.println("ln2==2 front="+front);
			 vo.setFirstName(spaceNames[0]);
			 vo.setLastName(spaceNames[1]);
			 vo.setValid(true);
			 return vo;
		 } else if (ln2==3) {
			 /**
			  *  three names parts delimited by ' ', first one is first name, second one is middle name, last one is last name 
			  */
			 System.out.println("ln2==3 front="+front);
			 vo.setFirstName(spaceNames[0]);
			 vo.setMiddleName(spaceNames[1]);
			 vo.setLastName(spaceNames[2]);
			 vo.setValid(true);
			 return vo;
		 }
		 return vo;
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
				" Wilson and Grance, Smith,wgsmith@gmail.com",	" Wilson and Grance2; Smith; wgsmith@gmail.com"
		};
		
		for (int i=0;i<line.length;i++) {
			EmailLineVO vo = parserEmailLine(line[i]);
			System.out.println(line[i]+" will be parsed to Valid:"+vo.isValid()+",first name:"+vo.getFirstName()
					                  +",middle name="+vo.getMiddleName()+",last name:"+vo.getLastName()+",email:"+vo.getEmailAddress());	
			
		}
	}
}

