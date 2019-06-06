package com.interview.questions.sample.call.employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class CallingHandler {
	private final static int EMP_LEVELS=3;
	private final static int FRESHER_NUMBER=4;
	private final static int TL_NUMBER=2;
	private final static int PM_NUMBER=1;
	
	public List<Employee> emps[] = new ArrayList[EMP_LEVELS];
	
	public Queue<Call> waitQueue = new LinkedBlockingQueue<Call>();
	
	public Queue<Call> handlingQueue = new LinkedBlockingQueue<Call>();
	
	
	public CallingHandler() {
		initialized();
	}
	public void initialized() { // can be overriden
		emps[0] = new ArrayList<Employee>();
		for (int i=0;i<FRESHER_NUMBER;i++) {
			emps[0].add(new Employee(EmpRank.FRESHER,true));
		}
		emps[1] = new ArrayList<Employee>();
		for (int i=0;i<TL_NUMBER;i++) {
			emps[1].add(new Employee(EmpRank.TL,true));
		}
		emps[2] = new ArrayList<Employee>();
		for (int i=0;i<PM_NUMBER;i++) {
			emps[2].add(new Employee(EmpRank.PM,true));
		}
		
 	}
	public Employee incomingCallHandle(Call calling) {
		
		try {
			// process call from current call emp rank 
			for (int i=calling.getRank().rank; i<EMP_LEVELS; i++) {
				try {
					Thread.sleep(500); // delay for simulate escalation
				} catch (InterruptedException e) {}
				for (Employee emp : emps[i]) {
					if (emp.getFree()) {
						synchronized(calling) {
							calling.setHandling(true);
							calling.setDisconnected(false);
							calling.setReceiver(emp.getRank());
							handlingQueue.offer(calling);
							emp.setFree(false);
						}
						return emp;  // found the free employ
					}
				}
			}
			
			waitQueue.offer(calling); // put into waiting list;
			throw new NotFoundFreeEmployeeException("Not Found Free Employee to handle this call");
		} catch ( NotFoundFreeEmployeeException e) {
			System.out.println(e.getMessage());   // if configure log4j, do not need System.out.println 
		}
		return null;
	}
	
	public boolean handleWaitingCalling() {
		 while (!waitQueue.isEmpty()) {
			 Call call = waitQueue.peek();  // fetch not remove
			 if (incomingCallHandle(call)==null) { // if all employees are busy, stop 
				 return false;
			 } else {
				 waitQueue.remove();
			 }
		 }
		 return true;
	}
	public boolean disconnetedOneCalling(Call calling) {
		if (handlingQueue.contains(calling)) {
			handlingQueue.remove(calling);
			return true;
		} 
		return false;
	}
	public void reset() {
		for (int i=0; i<EMP_LEVELS; i++) {
			 
			 emps[i].clear();
			 
		}
		handlingQueue.clear();
		waitQueue.clear();
	}
	public void displayStats() {
		System.out.println("Employees:");
		for (int i=0; i<EMP_LEVELS; i++) {
 			for (Employee emp : emps[i]) {
 				System.out.println(emp.toString());
			}
		}
		System.out.println("Handling Queue:");
		for (Call call: handlingQueue) {
			System.out.println(call.toString());
		}
		System.out.println("Waiting Queue:");
		for (Call call: waitQueue) {
			System.out.println(call.toString());
		}
	}
}
