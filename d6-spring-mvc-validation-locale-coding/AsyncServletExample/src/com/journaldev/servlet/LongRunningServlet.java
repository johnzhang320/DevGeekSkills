package com.journaldev.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * If we hit above servlet through browser with URL as 
 * 
    http://localhost:8080/AsyncServletExample/LongRunningServlet?time=8000
 	
 	we get response as “Processing done for 8000 milliseconds!!” after 8 seconds. 
 	Now if you will look into server logs, you will get following log:
	
		
	LongRunningServlet Start::Name=http-bio-8080-exec-34::ID=103
	LongRunningServlet Start::Name=http-bio-8080-exec-34::ID=103::Time Taken=8002 ms.
	
	So our Servlet Thread was running for ~8+ seconds, although most of the processing has 
	nothing to do with the servlet request or response.
	
	This can leads to Thread Starvation – Since our servlet thread is blocked until all the 
	processing is done, if server gets a lot of requests to process, it will hit the maximum 
	servlet thread limit and further requests will get Connection Refused errors because if a new request
    comes in servlet container will spawn a thread to call service() method which call doGet or doPost
    method, if current thread is blocked, million requests are coming in, hit thread pool limit at once
 *
 */
@WebServlet("/LongRunningServlet")
public class LongRunningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long startTime = System.currentTimeMillis();
		System.out.println("LongRunningServlet Start::Name="
				+ Thread.currentThread().getName() + "::ID="
				+ Thread.currentThread().getId());

		String time = request.getParameter("time");
		int secs = Integer.valueOf(time);
		// max 10 seconds
		if (secs > 10000)
			secs = 10000;

		longProcessing(secs);

		PrintWriter out = response.getWriter();
		long endTime = System.currentTimeMillis();
		out.write("Processing done for " + secs + " milliseconds!!");
		System.out.println("LongRunningServlet Start::Name="
				+ Thread.currentThread().getName() + "::ID="
				+ Thread.currentThread().getId() + "::Time Taken="
				+ (endTime - startTime) + " ms.");
	}

	private void longProcessing(int secs) {
		// wait for given time before finishing
		try {
			Thread.sleep(secs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
