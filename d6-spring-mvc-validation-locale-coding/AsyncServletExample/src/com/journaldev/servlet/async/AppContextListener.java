   package com.journaldev.servlet.async;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
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
public class AppContextListener implements ServletContextListener {
    /* --------------When Servlet container start , create 100 threads as working thread in thread pool ----------*/
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		/*--------- create the thread pool , thread will be stored in Block Array Queue ----------------------*/
		ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L,
				TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));
		
		// place thread pool and place executor into ServletContext
		servletContextEvent.getServletContext().setAttribute("executor", executor);

	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) servletContextEvent
				.getServletContext().getAttribute("executor");
		executor.shutdown();
	}

}
