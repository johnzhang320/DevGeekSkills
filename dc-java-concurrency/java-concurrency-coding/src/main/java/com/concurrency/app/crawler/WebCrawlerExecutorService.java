package com.concurrency.app.crawler;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import java.util.HashSet;
/**
	For the Java 6 web crawler implementation we'll use a fixed-thread pool of 64 threads, 
	which we create by calling the Executors.newFixedThreadPool(int) factory method. 
	Listing 1 shows the main class implementation. 
*/

public class WebCrawlerExecutorService implements LinkHandler {
	static Logger Log = Logger.getLogger(WebCrawlerExecutorService.class);
    private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<String>());
    private String url;
    private ExecutorService execService;
    /**
     * 

		Creates a thread pool that reuses a fixed number of threads operating off a shared 
		unbounded queue. At any point, at most nThreads threads will be active processing tasks. 
		If additional tasks are submitted when all threads are active, they will wait in the queue 
		until a thread is available. If any thread terminates due to a failure during execution 
		prior to shutdown, a new one will take its place if needed to execute subsequent tasks. 
		The threads in the pool will exist until it is explicitly shutdown.
		Parameters:nThreads the number of threads in the poolReturns:the newly created thread 
		poolThrows:IllegalArgumentException - if nThreads <= 0
     * @param startingURL
     * @param maxThreads
     */
    public WebCrawlerExecutorService(String startingURL, int maxThreads) {
        this.url = startingURL;
        execService = Executors.newFixedThreadPool(maxThreads);
    }

    @Override
    public void queueLink(String link) throws Exception {
        startNewThread(link);
    }

    @Override
    public int size() {
        return visitedLinks.size();
    }

    @Override
    public void addVisited(String s) {
        visitedLinks.add(s);
    }

    @Override
    public boolean visited(String s) {
        return visitedLinks.contains(s);
    }

    private void startNewThread(String link) throws Exception {
        execService.execute(new LinkFinder(link, this));
    }

    private void startCrawling() throws Exception {
        startNewThread(this.url);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
    	Log.info("Start Crawling");
    	TimeCounter.getInstance();
        new WebCrawlerExecutorService("http://www.javaworld.com", 64).startCrawling();
        Log.info("All Threads Started !");
    }
}
