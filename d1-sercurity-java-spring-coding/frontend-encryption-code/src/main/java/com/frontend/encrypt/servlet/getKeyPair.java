package com.frontend.encrypt.servlet;
/*
 *   Copyright 2018, 2019 John Zhang (Or Jian Zhang)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
import java.io.IOException;
import java.security.KeyPair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.frontend.encrypt.utils.Constants;
import com.frontend.encrypt.utils.KeyPairManager;
import com.frontend.encrypt.utils.JCryptionUtil;

/**
 * Servlet implementation class getPublicKey
 */
public class getKeyPair extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger Log = Logger.getLogger(getKeyPair.class);   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getKeyPair() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Log.info("doGet() Begin:");
		doPost(request, response);
		Log.info("doGet() End:");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Log.info("doPost() Begin:");
			KeyPair keys = KeyPairManager.getInstance().getKeyPair();
		    request.getSession().setAttribute(Constants.KEY_PAIR, keys);  		    
		    String keyString =  KeyPairManager.getInstance().getKeyString();		   
		    response.getOutputStream().print(keyString);  
		    
			} catch (Exception e) {
				Log.info("Generate Key Failed because of "+e.getMessage());
				response.getOutputStream().println("Generate Key Failed because of "
				+e.getMessage());
			}
		    Log.info("doPost() End:");
	}
}
