package com.core.java.utils;

public class CoreJavaException extends Exception {
	 public CoreJavaException(String str) {
		 System.err.print(str+"\n");
		 new Exception();
	 }
}
