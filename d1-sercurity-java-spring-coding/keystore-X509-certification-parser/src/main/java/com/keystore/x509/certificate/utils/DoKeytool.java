package com.keystore.x509.certificate.utils;
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 12/2017
 */
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;

public class DoKeytool {
	
	static Logger Log = Logger.getLogger(DoKeytool.class);
    public static String execute(String command){
	    	return execute(command, true);
	    }
    
    
    // Execute the commands
    public static String execute(String command, boolean display){
    	  
	    	ByteArrayOutputStream baos = null;
	    	PrintStream ps = null;
        try{
          //  printCommand(command);
         // Create a stream to hold the output
            baos = new ByteArrayOutputStream();
            ps = new PrintStream(baos);
            // IMPORTANT: Save the old System.out!
            PrintStream old = System.out;
            // Tell Java to use your special stream
            System.setOut(ps);
             
            // we use customized keytool library inheriated from JDK 1.8, which can throw Exception
            com.sun.security.tools.keytool.Main.main(parse(command));
            
            // Put things back
            System.out.flush();
            System.setOut(old);
            if (display) {
	            // Show what happened
	            Log.info(baos.toString());
            }
            
            return baos.toString();
       
        } catch (Exception ex){
        	// once internal failed , display and return the error message
           // Log.error(ex.getMessage());
            return baos.toString();
        } finally {
        	try {
        		if (baos!=null) {
        			baos.close();
        			ps.close();
        		}
        	} catch(Exception e) {}
        }
       
        
    }
     
    // Parse command
    private static String[] parse(String command){
        String[] options = Utils.lrTrim(command).split("\\s+");
        return options;
    }
     
    // Print the command
    private static void printCommand(String command){
        Log.info(command);
    }
}
