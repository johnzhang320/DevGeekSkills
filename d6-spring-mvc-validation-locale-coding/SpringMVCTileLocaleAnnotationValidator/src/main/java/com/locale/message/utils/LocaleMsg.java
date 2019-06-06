package com.locale.message.utils;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;



public class LocaleMsg {
	private static Logger LOG = Logger.getLogger(LocaleMsg.class);
	/**
	 * LocaleMsg retrieve message from locale property files by message code, which will be used in front end validation and 
	 * back end validation
	 *  
	 * We define two types of property files for International language environment:
	 * application_xx.propertie --message with arguments (Sometimes arguments must be international too) and form field labels
	 * Instead of explicitly defining:
	 * Email is required
	 * Full Name is required
	 * Email is invalid
	 * Phone number is invalid
	 * We define:
	 * required.field ={0} is required
	 * invalid.value.field ={0} is invalid {1}
	 * invalid.field ={0} is invalid 
	 * Purposes: 
	 * (1) We will simplify the message definition and eliminate the a lot of repeating information
	 * (2) Pass locale message fetched from locale message code as arguments to form international message sentence.
	 * 
	 */

	 protected static HttpServletRequest request=null;
	 protected static String lang=null;

	 private static Properties applicationProperties=null;
		
	 protected static void loadProperties(String language) {		
		 LocaleMsg.lang = language;
         LoadPropertiesUtil lp = new LoadPropertiesUtil();      
         applicationProperties = lp.getProperties("application_"+LocaleMsg.lang +".properties");
     }
	/**
	 * Whenever switch language or obtain the message, call it 
	 * @param request
	 */
     public static void initProperties(HttpServletRequest request) {
    	 LocaleMsg.request = request;
         SessionLocaleResolver sessionLocale= new SessionLocaleResolver();		
         Locale locale = sessionLocale.resolveLocale(request);
         String language = locale.getLanguage();
         if (language.equalsIgnoreCase("zh")) {
        	 language = "zh_"+locale.getCountry();
         }
         if (null==LocaleMsg.lang || !language.equalsIgnoreCase(LocaleMsg.lang)) {
             loadProperties(language);
         }
     }
     /**
      * Get message from message code only
      * @param messageCode
      * @return
      */
     public static String getMessage(String messageCode) {
    	if (null ==LocaleMsg.request) {
    		LOG.info("Call initProperties(Request) first");
    		return null;
    	}
    	 return applicationProperties.getProperty(messageCode);
	 }    
     /**
      * Get message from message code and request
      * @param messageCode
      * @return
      */
     public static String getMessage(HttpServletRequest request, String messageCode) {
    	if (null ==LocaleMsg.request) {
    		initProperties(request);
    		
    	}
    	 return applicationProperties.getProperty(messageCode);
	 } 
     /**
      * Form message sentence from message code , List<String> Arguments Value 
      * @param messageCode
      * @return
      */
     
     public static String getMessage(String messageCode,List<String> msgArgs ) {
    	if (null ==LocaleMsg.request) {
    		LOG.info("Call initProperties(Request) first");
    		return null;
    	}
    	String messageSentence =  applicationProperties.getProperty(messageCode);
    	if (null==messageSentence || messageSentence.indexOf("{0}")==-1) {
    		return messageSentence;    		
    	}
    	Integer count = 0;
    	for (String m:msgArgs) {
    		String arg = "{"+count.toString()+"}";
    		messageSentence = messageSentence.replace(arg, m);
    		count++;
    	}
    	return messageSentence;
	 } 
 
     public static String getMessage(String messageCode,String msgArg1 ) {
     	if (null ==LocaleMsg.request) {
     		LOG.info("Call initProperties(Request) first");
     		return null;
     	}
     	String messageSentence =  applicationProperties.getProperty(messageCode);
     	if (null==messageSentence || messageSentence.indexOf("{0}")==-1) {
     		return messageSentence;    		
     	}
     	return messageSentence.replace("{0}", msgArg1);
     	 
 	 } 
     public static String getMessage(String messageCode,String msgArg1,String msgArg2) {
     	if (null ==LocaleMsg.request) {
     		LOG.info("Call initProperties(Request) first");
     		return null;
     	}
     	String messageSentence =  applicationProperties.getProperty(messageCode);
     	if (null==messageSentence || messageSentence.indexOf("{0}")==-1) {
     		return messageSentence;    		
     	}
     	return messageSentence.replace("{0}", msgArg1).replace("{1}", msgArg2);
     	 
     	 
 	 } 
     public static String getMessage(String messageCode,String msgArg1,String msgArg2,String msgArg3) {
      	if (null ==LocaleMsg.request) {
      		LOG.info("Call initProperties(Request) first");
      		return null;
      	}
      	String messageSentence =  applicationProperties.getProperty(messageCode);
      	if (null==messageSentence || messageSentence.indexOf("{0}")==-1) {
      		return messageSentence;    		
      	}
      	return messageSentence.replace("{0}", msgArg1).replace("{1}", msgArg2).replace("{3}", msgArg3);
     	 
  	 } 
     /**
      * Form message sentence from message code , List<String> Arguments Value and Request
      * @param messageCode
      * @return
      */
     
     public static String getMessage(HttpServletRequest request, String messageCode,String msgArg1,String msgArg2,String msgArg3 ) {
    	if (null ==LocaleMsg.request) {
    		initProperties(request);
    	}
    	String messageSentence =  applicationProperties.getProperty(messageCode);
    	if (null==messageSentence || messageSentence.indexOf("{0}")==-1) {
    		return messageSentence;    		
    	}
    	Integer count = 0;
    	return messageSentence.replace("{0}", msgArg1).replace("{1}", msgArg2).replace("{2}", msgArg3);
     	 
	 } 
     /**
      * Form message sentence from message code , List<String> Arguments Message Code for international arguments 
      * @param messageCode
      * @return
      */
     
     public static String getMessageFromCode(String messageCode,List<String> msgCodeArgs ) {
    	if (null ==LocaleMsg.request) {
    		LOG.info("Call initProperties(Request) first");
    		return null;
    	}
    	String messageSentence =  applicationProperties.getProperty(messageCode);
    	if (null==messageSentence || messageSentence.indexOf("{0}")==-1) {
    		return messageSentence;    		
    	}
    	Integer count = 0;
    	for (String code:msgCodeArgs) {
    		String m= getMessage(code);
    		String arg = "{"+count.toString()+"}";
    		messageSentence = messageSentence.replace(arg, m);
    		count++;
    	}
    	return messageSentence;
	 } 
     /**
      * Form message sentence from message code , List<String> Arguments Code for international arguments and Request
      * @param messageCode
      * @return
      */
     
     public static String getMessageFromCode(HttpServletRequest request, String messageCode,List<String> msgCodeArgs ) {
    	if (null ==LocaleMsg.request) {
    		initProperties(request);
    	}
    	String messageSentence =  applicationProperties.getProperty(messageCode);
    	if (null==messageSentence || messageSentence.indexOf("{0}")==-1) {
    		return messageSentence;    		
    	}
    	Integer count = 0;
    	for (String code:msgCodeArgs) {
    		String m= getMessage(code);
    		String arg = "{"+count.toString()+"}";
    		messageSentence =messageSentence.replace(arg, m);
    		count++;
    	}
    	return messageSentence;
	 } 
     /**
      * Form message sentence from message code , String Arguments Code1 for international arguments and Request
      * @param messageCode
      * @return
      */
     
     public static String getMessageFromCode( String messageCode,String msgCodeArg1 ) {
    	if (null ==LocaleMsg.request) {
    		initProperties(request);
    	}
    	String messageSentence =  applicationProperties.getProperty(messageCode);
    	if (null==messageSentence || messageSentence.indexOf("{0}")==-1) {
    		return messageSentence;    		
    	}
    	 
    	String m= getMessage(msgCodeArg1);
    	 
     
    	 
     	return messageSentence.replace("{0}", m);
	 } 
     /**
      * Form message sentence from message code , String Arguments Code1,Code2 for international arguments and Request
      * @param messageCode
      * @return
      */
     
     public static String getMessageFromCode( String messageCode,String msgCodeArg1, String msgCodeArg2) {
    	if (null ==LocaleMsg.request) {
    		initProperties(request);
    	}
    	String messageSentence =  applicationProperties.getProperty(messageCode);
    	if (null==messageSentence || messageSentence.indexOf("{0}")==-1) {
    		return messageSentence;    		
    	}
    	 
    	String m1= getMessage(msgCodeArg1);
       	String m2= getMessage(msgCodeArg2); 
    	return messageSentence.replace("{0}", m1).replace("{1}", m2);
     	 
	 } 
     /**
      * Form message sentence from message code , String Arguments Code1,Code2,Code3 for international arguments and Request
      * @param messageCode
      * @return
      */
     
     public static String getMessageFromCode( String messageCode,String msgCodeArg1, String msgCodeArg2,String msgCodeArg3) {
    	if (null ==LocaleMsg.request) {
    		initProperties(request);
    	}
    	String messageSentence =  applicationProperties.getProperty(messageCode);
    	if (null==messageSentence || messageSentence.indexOf("{0}")==-1) {
    		return messageSentence;    		
    	}
    	 
    	String m1= getMessage(msgCodeArg1);
       	String m2= getMessage(msgCodeArg2); 
       	String m3= getMessage(msgCodeArg3); 
       	return messageSentence.replace("{0}", m1).replace("{1}", m2).replace("{2}", m3);
      
	 } 
}
