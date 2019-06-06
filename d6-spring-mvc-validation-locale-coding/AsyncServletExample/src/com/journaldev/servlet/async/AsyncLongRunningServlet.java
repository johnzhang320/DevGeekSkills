package com.journaldev.servlet.async;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * Prior to Servlet 3.0, there were container specific solution for these long running threads where 
 * we can spawn a separate worker thread to do the heavy task and then return the response to client.
 *  The servlet thread returns to the servlet pool after starting the worker thread. Tomcat’s Comet, 
 *  WebLogic’s FutureResponseServlet and WebSphere’s Asynchronous Request Dispatcher are some of the 
 *  example of implementation of asynchronous processing.

	The problem with container specific solution is that we can’t move to other servlet container 
	without changing our application code, that’s why Async Servlet support was added in Servlet 3.0 
	to provide standard way for asynchronous processing in servlets.
	
	
	
	Asynchronous Servlet Implementation
	
	Let’s see steps to implement async servlet and then we will provide async supported servlet for 
	above example.

    (1) First of all the servlet where we want to provide async support should have @WebServlet annotation
        with asyncSupported value as true.
    (2) Since the actual work is to be delegated to another thread, we should have a thread pool 
    	implementation. We can create thread pool using Executors framework and use servlet context 
    	listener to initiate the thread pool.
    (3) We need to get instance of AsyncContext through ServletRequest.startAsync() method. AsyncContext 
	    provides methods to get the ServletRequest and ServletResponse object references. It also provides
	    method to forward the request to another resource using dispatch() method.
	(4) We should have a Runnable implementation where we will do the heavy processing and then use 
	    AsyncContext object to either dispatch the request to another resource or write response 
	    using ServletResponse object. Once the processing is finished, we should call AsyncContext.
	    complete() method to let container know that async processing is finished.
    (5) We can add AsyncListener implementation to the AsyncContext object to implement callback 
	    methods – we can use this to provide error response to client in case of error or timeout
	    while async thread processing. We can also do some cleanup activity here.

	   Once we will complete our project for Async servlet Example, it will look like below image. 
	   
	   Now when we will run above servlet with URL as 
	   
	   http://localhost:8080/AsyncServletExample/AsyncLongRunningServlet?time=8000 
	   
	   we get the same response and logs as:
		AsyncLongRunningServlet Start::Name=http-bio-8080-exec-50::ID=124
		AsyncLongRunningServlet End::Name=http-bio-8080-exec-50::ID=124::Time Taken=1 ms.
		Async Supported? true
		AppAsyncListener onComplete
 */
@WebServlet(urlPatterns = "/AsyncLongRunningServlet", asyncSupported = true)
public class AsyncLongRunningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long startTime = System.currentTimeMillis();
		System.out.println("AsyncLongRunningServlet Start::Name="
				+ Thread.currentThread().getName() + "::ID="
				+ Thread.currentThread().getId());    
		
		/*----------Step 1: Set org.apache.catalina.ASYNC_SUPPORTED to true-------------*/
		request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);

		String time = request.getParameter("time");
		int secs = Integer.valueOf(time);
		// max 10 seconds
		if (secs > 10000)
			secs = 10000;

		/*----------Step 2: Start AsynnContext by request.startAsync() method-------------*/
		AsyncContext asyncCtx = request.startAsync();
		
		/*----------Step 3: use asynchcContext to add a AynchcListener (interface contain onComplete() onErrot() etc callback funtion-------------*/
		asyncCtx.addListener(new AppAsyncListener());
		asyncCtx.setTimeout(9000);

		/*----------Step 4: Start Aynchronized Thread Pool and threads from "executor" request attribute----------*/
		ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");
		
		/*----------Step 5  AsyncRequestProcessor is hard work thread which is placed to thread pool   ----------*/
		executor.execute(new AsyncRequestProcessor(asyncCtx, secs));
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("AsyncLongRunningServlet End::Name="
				+ Thread.currentThread().getName() + "::ID="
				+ Thread.currentThread().getId() + "::Time Taken="
				+ (endTime - startTime) + " ms.");
		
		PrintWriter out = response.getWriter();
		out.write("Asynchronized servlet thread finished his work at:");
		out.write("AsyncLongRunningServlet End::Name="
				+ Thread.currentThread().getName() + "::ID="
				+ Thread.currentThread().getId() + "::Time Taken="
				+ (endTime - startTime) + " ms.");
	}

}
