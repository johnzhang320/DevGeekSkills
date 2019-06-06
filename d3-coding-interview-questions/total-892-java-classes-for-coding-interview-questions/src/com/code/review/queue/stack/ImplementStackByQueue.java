package com.code.review.queue.stack;

import java.util.LinkedList;

public class ImplementStackByQueue<T> {
	LinkedList<Integer> queue1 = new LinkedList<Integer>();
    LinkedList<Integer> queue2 = new LinkedList<Integer>();
    int size=0;
    // Push element x onto stack.
    public void push(int x) {
        if(empty()){
            queue1.offer(x);
        }else{
            if(queue1.size()>0){
                queue2.offer(x);
                int size = queue1.size();
                while(size>0){
                    queue2.offer(queue1.poll());
                    size--;
                }
            }else if(queue2.size()>0){
                queue1.offer(x);
                int size = queue2.size();
                while(size>0){
                    queue1.offer(queue2.poll());
                    size--;
                }
            }
        }
        this.size++;
    }
 
    // Removes the element on top of the stack.
    public int pop() {
    	int result=0;
        if(queue1.size()>0){
           result= queue1.poll();
        }else if(queue2.size()>0){
          result=  queue2.poll();
        }
        this.size--;
        return result;
    }
 
    // Get the top element.
    public int top() {
       if(queue1.size()>0){
            return queue1.peek();
        }else if(queue2.size()>0){
            return queue2.peek();
        }
        return 0;
    }
    public int size() {
    	return size;
    }
    // Return whether the stack is empty.
    public boolean empty() {
        return queue1.isEmpty() & queue2.isEmpty();
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
