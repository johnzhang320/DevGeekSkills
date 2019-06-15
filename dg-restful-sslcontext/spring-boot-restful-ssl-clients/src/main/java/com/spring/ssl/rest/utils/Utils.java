package com.spring.ssl.rest.utils;

import org.apache.log4j.Logger;

 

public class Utils {
	public static Logger Log =Logger.getLogger(Utils.class.getName());
	public static String JsonPretty(String s) {
			
		 String result =s;
		 try {
			 //result = mapper.defaultPrettyPrintingWriter().writeValueAsString(s);
			 result=result.replace(",", ",\n").replace("[","{\n").replace("}","}\n").replace("[","[\n").replace("]","]\n");
			 
		 } catch (Exception e) {
			 Log.info("Failed to convert Json to Pretty Format "+e.getLocalizedMessage());
		 }
		 return result;
	 }
}
