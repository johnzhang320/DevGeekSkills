package com.frontend.encrypt.servlet;

import java.io.IOException;
import java.security.KeyPair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.*;

import com.frontend.encrypt.utils.KeyPairManager;
import com.frontend.encrypt.utils.JCryptionUtil;
    
/**
 * Servlet implementation class decryptPassword
 */
public class decryptPassword extends HttpServlet {
	
	private static Logger Log = Logger.getLogger(decryptPassword.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public decryptPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			  
			 String pass = (String) request.getParameter("password");//login pass  
			 Log.info("Received Encryption Password is "+pass);			 
		 
			 response.getOutputStream().println("Encrypted password is "+pass);
			// KeyPair keys = KeyPairManager.getInstance().getKeyPair(); //(KeyPair) request.getSession().getAttribute("keys");  
			 String decryptedPass =KeyPairManager.getInstance().decrypt(pass);  
			 request.setAttribute("pass", decryptedPass);  
			 response.getOutputStream().println("Decrypted password is "+decryptedPass); 
			 Log.info("Decrypted password is "+decryptedPass);
		} catch (Exception e) {
			Log.info("Decryption failed because of "+e.getMessage());
			response.getOutputStream().println("Decryption failed because of "+e.getMessage());
		}
	}

}
