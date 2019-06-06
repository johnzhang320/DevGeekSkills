package com.concurrency.app.web.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

public class SocketClient {
	static Logger Log = Logger.getLogger(SocketClient.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String files[] = {"c:/temp/ExampleOfCassandra_MusicService.txt",
				          "c:/temp/John_Zhang_Resume_Updated_BE_04_23_2017.txt",
				          "c:/temp/John_Zhang_Resume_Updated_Senior_Software_Engineer_04_14_2017.txt"}; 
		try {
		    for(int i=0;i<files.length;i++) {
				Socket echoSocket = new Socket("localhost", 8099);
				
		    	OutputStream os =echoSocket.getOutputStream();
		    	DataOutputStream dos = new DataOutputStream(os);
		    	
		    	FileReader fr = null;
				BufferedReader br = null;
				 
				fr = new FileReader(files[i]);
				br = new BufferedReader(fr);
				String line;
				while ((line=br.readLine())!=null) {
					Log.info(line);
					dos.writeUTF(line+"\n");
				}
				Log.info("sent file:"+files[i]);
			    fr.close();
			    br.close();
			    echoSocket.close();
		    }
		    
		} catch (Exception e) {
			Log.info("Send file to Server error"+e.getMessage());
			e.printStackTrace();
		}
		
		 
	}

}
