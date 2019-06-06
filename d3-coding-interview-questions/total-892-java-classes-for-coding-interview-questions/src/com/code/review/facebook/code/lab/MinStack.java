package com.code.review.facebook.code.lab;

public class MinStack {
	/**
	 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

        push(x) – Push element x onto stack.
        pop() – Removes the element on top of the stack.
        top() – Get the top element.
        getMin() – Retrieve the minimum element in the stack.

		Note that all the operations have to be constant time operations.
		
		Questions to ask the interviewer :
		
		Q: What should getMin() do on empty stack? 
		A: In this case, return -1.
		
		Q: What should pop do on empty stack? 
		A: In this case, nothing. 
		
		Q: What should top() do on empty stack?
		A: In this case, return -1
		
		    NOTE : If you are using your own declared global variables, make sure to clear them out in the constructor.
		
			35 min. 	
	 * @param args
	 */
    class Elem {
		 int x;
		 int min;
		 Elem next;
		 public Elem(int value, int min) {
			 this.x = value;
			 this.min = min;
		 }
 	 }
	 public Elem top=null;
	 
    public void push(int x) {
        if (top==null) {
            top = new Elem(x,x);
        } else {
            Elem e = new Elem(x, Math.min(top.min,x));
            e.next = top;
            top = e;
        }
    }

    public void pop() {
        if (top==null) {
            return;
        }
        Elem retVal = top;
        top = top.next;
        retVal.next =null;
        
    }

    public int top() {
        if (top==null) {
            return -1;
        }
        return top.x;
    }

    public int getMin() {
        if (top==null) {
            return -1;
        } 
        return top.min;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
