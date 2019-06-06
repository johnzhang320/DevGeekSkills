package com.code.review.queue.stack;
/*
 * Below solution passed leetcode 
   Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
	
	push(x) -- Push element x onto stack.
	pop() -- Removes the element on top of the stack.
	top() -- Get the top element.
	getMin() -- Retrieve the minimum element in the stack.
	Example:
	
	MinStack minStack = new MinStack();
	minStack.push(-2);
	minStack.push(0);
	minStack.push(-3);
	minStack.getMin();   --> Returns -3.
	minStack.pop();
	minStack.top();      --> Returns 0.
	minStack.getMin();   --> Returns -2.
 */
class MinStack {
	   public class Stack {
	        int val;
	        int min;
	        Stack next;
	        public Stack() {}
	        public Stack(int val) {
	            this.val=val;
	        }
	    }
	    Stack top;
	    /** initialize your data structure here. */
	    public MinStack() {
	        top = null;
	    }
	    
	    public void push(int val) {
	        if (top==null) {
	            top = new Stack(val);
	            top.min = val;
	        } else if (top!=null) {
	            Stack tmp = new Stack(val);
	            tmp.min = top.min>val ? val:top.min;
	            tmp.next = top;
	            top = tmp;
	        }
	    }
	    
	    public void pop() {
	        if (top!=null) {
	            top = top.next;
	        }
	    }
	    
	    public int top() {
	        if (top!=null) {
	            
	            return top.val;
	        }  
	        return 0;
	    }
	    
	    public int getMin() {
	        if (top!=null) {
	            return top.min;
	        }
	        return 0;
	    }
	}

	/**
	 * Your MinStack object will be instantiated and called as such:
	 * MinStack obj = new MinStack();
	 * obj.push(x);
	 * obj.pop();
	 * int param_3 = obj.top();
	 * int param_4 = obj.getMin();
	 */