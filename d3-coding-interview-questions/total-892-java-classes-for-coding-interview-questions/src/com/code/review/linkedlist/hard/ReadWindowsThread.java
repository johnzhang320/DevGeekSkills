package com.code.review.linkedlist.hard;

import java.io.File;

import com.sun.istack.internal.logging.Logger;

public class ReadWindowsThread implements Runnable {
	static Logger Log = Logger.getLogger(ReadWindowsThread.class);
	private static final String SYS_PATH = "C:/Program Files";
	 
	private static MyLRU<String,String> lru = new MyLRU<String,String>();
	 
	public void run() {
		Log.info("Thread:"+Thread.currentThread().getName()+" Starting");
		dfs(SYS_PATH);
		Log.info("Thread:"+Thread.currentThread().getName()+" find LRU size is "+lru.size());
		
		dfsGet(SYS_PATH);
		Log.info("Thread:"+Thread.currentThread().getName()+" End");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {}
		
	}
	public void dfs(String filePath) {
		File content[] = new File(filePath).listFiles();
		if (content==null) return;
		for (int i=0; i<content.length;i++) {
			if (!content[i].isDirectory()) {
				if (content[i]!=null) {
					lru.set(content[i].getAbsolutePath(), content[i].getName());
				}
			} else {
				dfs(content[i].getAbsolutePath());
			}
		}
	}
	public void dfsGet(String filePath) {
		File content[] = new File(filePath).listFiles();
		if (content==null) return;
		for (int i=0; i<content.length;i++) {
			if (!content[i].isDirectory()) {
				if (content[i]!=null) {
					String filename = lru.get(content[i].getAbsolutePath());
					if (filename!=null) {
						System.out.println(filename);
					}
				}
			} else {
				dfsGet(content[i].getAbsolutePath());
			}
		}
	}
}
