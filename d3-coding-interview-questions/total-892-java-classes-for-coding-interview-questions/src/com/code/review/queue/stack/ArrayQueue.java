package com.code.review.queue.stack;

public class ArrayQueue<T> {
	  	private int front;
	    private int rear;
	    int size;
	    T[] queue;

	    public ArrayQueue(int inSize) {
	        size = inSize;
	        queue = (T[]) new Object[size];
	        front = -1;
	        rear = -1;
	    }

	    public boolean isempty() {
	        return (front == -1 && rear == -1);
	    }

	    public void enQueue(T value) {
	        if ((rear+1)%size==front) {
	            throw new IllegalStateException("Queue is full");

	        } else if (isempty()) {
	            front++;
	            rear++;
	            queue[rear] = value;

	        } else {
	            rear=(rear+1)%size;
	            queue[rear] = value;

	        }
	    }

	    public T deQueue() {
	        T value = null;
	        if (isempty()) {
	            throw new IllegalStateException("Queue is empty, cant dequeue");
	        } else if (front == rear) {
	            value = queue[front];
	            front = -1;
	            rear = -1;

	        } else {
	            value = queue[front];
	            front=(front+1)%size;

	        }
	        return value;

	    }

	  
}
