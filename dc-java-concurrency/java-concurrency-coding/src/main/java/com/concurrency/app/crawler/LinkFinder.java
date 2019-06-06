package com.concurrency.app.crawler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
/**
 *  
 * The logic of the LinkFinder is simple: (1) we start parsing a URL; (2) after we gather all the 
 * links within the corresponding page, we mark the page as visited; and (3) we send each found link 
 * to a queue by calling the queueLink() method. This method will actually create a new thread and 
 * send it to the ExecutorService. If "free" threads are available in the pool, the thread will be 
 * executed; otherwise it will be placed in a waiting queue. After we reach 1,500 distinct links 
 * visited, we print the statistics and the program continues to run. 
 *
 */

public class LinkFinder implements Runnable {
	static Logger Log = Logger.getLogger(LinkFinder.class);
	 
    private String url;
    private LinkHandler linkHandler;
    /**
     * Used fot statistics
     */
    private static final long t0 = System.nanoTime();

    public LinkFinder(String url, LinkHandler handler) {
        this.url = url;
        this.linkHandler = handler;
    }

    @Override
    public void run() {
        getSimpleLinks(url);
    }

    private void getSimpleLinks(String url) {
        //if not already visited
        if (!linkHandler.visited(url)) {
            try {
                URL uriLink = new URL(url);
                Parser parser = new Parser(uriLink.openConnection());
                NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
                List<String> urls = new ArrayList<String>();

                 for (int i = 0; i < list.size(); i++) {
                    LinkTag extracted = (LinkTag) list.elementAt(i);
                	String newLink = extracted.getLink();
                    if (!newLink.isEmpty()
                            && !linkHandler.visited(newLink)) {                    
                    	//Log.info(Thread.currentThread().getName()+" add new link to url list:"+newLink);	
                        urls.add(newLink);
                    }

                }
                //we visited this url
                linkHandler.addVisited(url);

                if (linkHandler.size() == 1500) {
                    Log.info("Time to visit 1500 distinct links = " + (System.nanoTime() - t0));                   
                }

                for (String l : urls) {
                	if (!l.contentEquals("//")) {
                		//Log.info(Thread.currentThread().getName()+" add url list to queue:"+l);	                    
                		linkHandler.queueLink(l);
                	}
                }
                if ((linkHandler.size() % 1000==0)) {
                	Log.info("Crawled Links: "+linkHandler.size());
                	Log.info("Took "+TimeCounter.getInstance().takeSeconds()+" seconds ");
                }
             } catch (Exception e) {
                //ignore all errors for now
            	// Log.info("Error:"+e.getMessage());
            	// e.printStackTrace();
            }
        }
    }

}
