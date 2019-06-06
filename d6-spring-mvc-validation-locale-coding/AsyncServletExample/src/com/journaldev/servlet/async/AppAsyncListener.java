package com.journaldev.servlet.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;
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
	    methods – we can use this to provide error response to client incase of error or timeout
	    while async thread processing. We can also do some cleanup activity here.

	   Once we will complete our project for Async servlet Example, it will look like below image. *
 */
@WebListener
public class AppAsyncListener implements AsyncListener {

	@Override
	public void onComplete(AsyncEvent asyncEvent) throws IOException {
		System.out.println("AppAsyncListener onComplete");
		// we can do resource cleanup activity here
		ServletResponse response = asyncEvent.getAsyncContext().getResponse();
		PrintWriter out = response.getWriter();
		out.write("Right now Callback function: onComplete() be called <br>Hard working thread just completes his work!");
	}

	@Override
	public void onError(AsyncEvent asyncEvent) throws IOException {
		System.out.println("AppAsyncListener onError");
		//we can return error response to client
	}

	@Override
	public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
		System.out.println("AppAsyncListener onStartAsync");
		//we can log the event here
	}

	@Override
	public void onTimeout(AsyncEvent asyncEvent) throws IOException {
		System.out.println("AppAsyncListener onTimeout");
		//we can send appropriate response to client
		ServletResponse response = asyncEvent.getAsyncContext().getResponse();
		PrintWriter out = response.getWriter();
		out.write("TimeOut Error in Processing");
	}

}
