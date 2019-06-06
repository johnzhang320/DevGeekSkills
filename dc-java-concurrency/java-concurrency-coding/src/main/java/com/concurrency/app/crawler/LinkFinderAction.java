package com.concurrency.app.crawler;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

import org.apache.log4j.Logger;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
 
public class LinkFinderAction extends RecursiveAction {
	static Logger Log = Logger.getLogger(LinkFinderAction.class);

	private String url;
    private LinkHandler cr;
    /**
     * Used for statistics
     */
    private static final long t0 = System.nanoTime();

    public LinkFinderAction(String url, LinkHandler cr) {
        this.url = url;
        this.cr = cr;
    }

    @Override
    public void compute() {
        if (!cr.visited(url)) {
            try {
                List<RecursiveAction> actions = new ArrayList<RecursiveAction>();
                URL uriLink = new URL(url);
                Parser parser = new Parser(uriLink.openConnection());
                NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));

                for (int i = 0; i < list.size(); i++) {
                    LinkTag extracted = (LinkTag) list.elementAt(i);
                    String extractLink = extracted.extractLink();
                    if (!extractLink.isEmpty()
                            && !cr.visited(extractLink)) {

                        actions.add(new LinkFinderAction(extractLink, cr));
                    }
                }
                cr.addVisited(url);

                if (cr.size() == 1500) {
                    Log.info("Time for visit 1500 distinct links= " + (System.nanoTime() - t0));                   
                }
                if ((cr.size() % 1000==0)) {
                	Log.info("Crawled Links: "+cr.size());
                	Log.info("Took "+TimeCounter.getInstance().takeSeconds()+" seconds ");
                }
                //invoke recursively
                invokeAll(actions);
            } catch (Exception e) {
                //ignore 404, unknown protocol or other server errors
            }
        }
    }
}
