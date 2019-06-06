package com.code.review.aaa.career.cup.algorithm;

import java.util.LinkedList;

public class ImplementStackByQueue<T> {
    /**
     *  Implement stack with two queues 
     *  Queue can be LinkedList, push mean append element to tail of queueOut , pop mean , if queueIn not
     *  empty, take element from head of queueIn, if queueIn is empty, take element from head of queueOut
     *  move all element append to queueIn 
     *  (1) create queueOut and queueIn using linkedlist of java.io.util (suppose only take data from head, add data to end) 
     *  (2) push() --> add data to end of queueOut O(1)
     *  (3) pop()  --> if queueIn is not empty, move element to queueOut
     *                 if queueIn is not empty, take  
     */
	public LinkedList<T> queueOut = new LinkedList<T>();
	public LinkedList<T> queueIn = new LinkedList<T>();
	private int size=0;
	private T top;
	public void push(T e) {
		queueIn.add(e);
		size++;
	}
	public T pop() {
		
		// check if queueIn is not empty, move all of it to queueOut
		if (!queueIn.isEmpty()) {
			int len = queueIn.size();
			for (int i=0; i<len;i++) {
				T data = queueIn.pop();
				queueOut.push(data);
				
			}
		}
			
		if (!queueOut.isEmpty()) {
			size--;
			top =queueOut.pop();
			return top;
		}
		return null;
		 
		 
	}
	public int size() {
		return size;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImplementStackByQueue ref =new ImplementStackByQueue<Integer>(); 	
		int A[]={1,2,3,4,5,8};
		for (Integer i: A) {
			ref.push(i);
		}
		System.out.println("\nSize1="+ref.size());
		int len = ref.size();
		for (int i=0; i<len; i++) { 
		System.out.print(ref.pop()+" ");
		}
		System.out.println("\n\nSize2="+ref.size());
		
	}

}
