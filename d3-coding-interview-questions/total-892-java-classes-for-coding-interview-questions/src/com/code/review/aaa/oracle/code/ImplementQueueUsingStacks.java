package com.code.review.aaa.oracle.code;

import java.util.Stack;

public class ImplementQueueUsingStacks {
	/**
	 *  Solution one deQueue costly natively
	 *  (1) create stackOut and stackIn, 
	 *  (2) when we enQueue queue, we just add data to stackIn 
	 *  (3) when we deQueue queue, if stackIn is not empty, remove almost all data of stackIn to stackOut 
	 *  (4) return stackOut.pop if stackOut not empty  
	 *  
	 * 
	 */
	private Stack<Integer> stackOut = new Stack<Integer>();
	private Stack<Integer> stackIn = new Stack<Integer>();
	private int size=0;
    public void enqueue (int val) {
    	stackIn.push(val);
    	size++;
    }
    public int dequeue() {
    	int ret=-1;
    	if (!stackIn.isEmpty()) {
    		int len = stackIn.size();
    		for (int i=0;i<len;i++) {
    			int val = stackIn.pop();
    			stackOut.push(val);  // last one is one that should pop out
    		}
    	}
    	if (!stackOut.isEmpty()) {    		
    	    size--;
    	    ret = stackOut.pop();
    	}  
    	 
    	return ret;
    	
    }
    public int size() {
    	return size;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImplementQueueUsingStacks ref = new ImplementQueueUsingStacks();
		int A[] = {1,2,3,4,5,6,7,10};
		for (int i=0; i<A.length-2;i++) {
			
			ref.enqueue(A[i]);
		}
		int len = ref.size();
		System.out.println("queue size="+len);
		
		/*for (int i=0;i<len;i++) {
			System.out.print(ref.dequeue()+" ");
		}*/
		for (Integer i: A) {
			ref.enqueue(i);
		}
		System.out.println("");
		 len = ref.size();
		for (int i=0;i<len;i++) {
			System.out.print(ref.dequeue()+" ");
		}
	}

}
