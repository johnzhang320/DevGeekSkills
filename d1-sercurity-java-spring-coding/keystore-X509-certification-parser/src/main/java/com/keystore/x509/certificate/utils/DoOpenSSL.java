package com.keystore.x509.certificate.utils;
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 12/2017
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class DoOpenSSL {
	static Logger Log = Logger.getLogger(DoOpenSSL.class);
	public int runCmd(String commandLine,String workdir, boolean printOutput,boolean printError) {
		int retVal=-1;
		Process process = null;
	    try {
	    	Runtime runtime = Runtime.getRuntime();
	    	if (workdir!=null) {
		    	File dir = new File(workdir);
		    	if (dir.isDirectory()) {
		    		Log.info("Run CommandLine "+commandLine+",dir="+dir.getAbsolutePath());
		    		 process =runtime.exec(commandLine,null,dir);
		    		 
		    	} else {
		    		Log.error("Work directory is not directory!");
		    		return retVal;
		    	}
	    	} else {
	    		 process =runtime.exec(commandLine);
	    	}
			
			if (printOutput) {
				BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				Log.info("Output:  " + outputReader.readLine());
			}
			if (printError)
			{
				BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
				String line;
				while((line=errorReader.readLine())!=null) {
					Log.info("Execute Message:  " + line);
				}
			}
			
			ProcessWithTimeout pwt =new ProcessWithTimeout(process);
			retVal =pwt.waitForProcess(1000);
			 
	    } catch (IOException e) {
	    	Log.error ("IOException :"+e.getMessage());
	    } 
	  
	    
		Log.info("Returned value="+retVal); 
	    return retVal;	
			
	}
	public class ProcessWithTimeout extends Thread
	{
	    private Process m_process;
	    private int m_exitCode = Integer.MIN_VALUE;

	    public ProcessWithTimeout(Process p_process)
	    {
	        m_process = p_process;
	    }

	    public int waitForProcess(int p_timeoutMilliseconds)
	    {
	        this.start();
	       
	        try
	        {
	            this.join(p_timeoutMilliseconds);
	            Log.info("Joined thread finished normally....");
	            
	        }
	        catch (InterruptedException e)
	        {
	            this.interrupt();
	             
	        }

	        return m_exitCode;
	    }

	    @Override
	    public void run()
	    {
	        try
	        { 
	        	
	            m_exitCode = m_process.waitFor();
	            Log.info("command finished normally....exit value="+m_exitCode);
	        }
	        catch (InterruptedException ignore)
	        {
	            Log.info("Process Interrupted");
	        }
	        catch (Exception ex)
	        {
	        	Log.info("Unexpected Exception!");
	        }
	    }
	}
	
}
