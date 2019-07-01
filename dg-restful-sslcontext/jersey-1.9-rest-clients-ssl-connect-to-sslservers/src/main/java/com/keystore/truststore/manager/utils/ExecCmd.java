package com.keystore.truststore.manager.utils;
/**
 *  Created by John Zhang (john.zhang320@gmail.com)
 *  Created Date: 12/2017
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ExecCmd {
	
	static Logger Log = Logger.getLogger(ExecCmd.class);
	
	private String outputString ="";
	private StringBuffer msgBuf = new StringBuffer();
	public int runCmd(String commandLine,String workdir) {
		return runCmd(commandLine,workdir, true,true);
	}
	public int runCmd(String commandLine,String workdir, boolean printOutput,boolean printError) {
		int retVal=-1;
		Process process = null;
	    try {
	    	
	    
	    	Runtime runtime = Runtime.getRuntime();
	    	if (workdir!=null) {
		    	File dir = new File(workdir);
		    	if (dir.isDirectory()) {
		    		Log.info("Run CommandLine "+commandLine+", working directory="+dir.getAbsolutePath());
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
				outputString =  outputReader.readLine();
				Log.info("Output:  " + outputString);
				String line;
				while((line=outputReader.readLine())!=null) {
					Log.info("Execute Message:  " + line);
					msgBuf.append("Execute Message:  " + line+"\n");
				}
				 
			}
			if (printError)
			{
				BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
				String line;
				while((line=errorReader.readLine())!=null) {
					Log.info("Execute Message:  " + line);
					msgBuf.append("Execute Message:  " + line+"\n");
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
	public String getOutputString() {
		return outputString;
	}
	public void setOutputString(String outputString) {
		this.outputString = outputString;
	}
	public StringBuffer getMsgBuf() {
		return msgBuf;
	}
	public void setMsgBuf(StringBuffer msgBuf) {
		this.msgBuf = msgBuf;
	}
	
}
