package com.interview.questions.send.messages;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * Company CEO send message to his underneath director or manager, they also resent the message to 
their underneath employees so on so forth, each level people need there own time to send message
and various below employees, suppose one person is able to send message to multiple underneadth persons at same time
Question: find how long the message send to all company employee. if not send message out, would not sum their sending time

               CEO(6)
               /   \          \      \
          Emp1(4)     Emp2(5) Emp3(3) Emp4(7)
          /   \  \         \
         /     \   \         \
   Emp5(3) Emp6(5) Emp7(3) Emp8(7)
       /  \           \
      /     \          \
  Emp9(3)   Emp10(1)   Emp11(2)
  
  
  Analysis:
  Suppose current level is n and parent level is n-1 and child level is n+1
  we always calculate n-1 level sending time as sum time of current level
  in other words , we check if each node in tree has their child node, if yes
  sum += node sending time,  CEO level has four children, sum+=6, Emp1 has 3 children , sum+=4
  Emp7 has one child sum+=3
  
  Algorithm
  We use BFS to find node and check if they have child, because message sending directory always
  from up to bottom, no need visited check
  Implementation
  (1) create Node with time, name and list<Node> belowNodes;
  (2) use a queue to implement level access BFS by check belowsNode
  (3) sum=0; check if node.belowNode!=null sum+=node.time
  
 *
 */

public class SendingMessageTimeSum {
	public class Node {
		String empName;
		int sendingTime;
		List<Node> belows=null;
		 
		public Node(String empName, int sendingTime, List<Node> belows) {
			super();
			this.empName = empName;
			this.sendingTime = sendingTime;
			this.belows = belows;
		}
		
	}
	public int findSumTime(Node root) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		int sum=0;
		while (!q.isEmpty()) {
			Node n = q.poll();
		
			if (n.belows!=null) {
			
				sum+=n.sendingTime;
				System.out.println(n.empName+"("+sum+")");
				for (Node s:n.belows) {
					q.add(s);
				}
			}
			
		}
		return sum;
	}
	/**
	 *           CEO(6)
               /   \          \      \
          Emp1(4)     Emp2(5) Emp3(3) Emp4(7)
          /   \  \         \
         /     \   \         \
   Emp5(3) Emp6(5) Emp7(3) Emp8(7)
       /  \           \
      /     \          \
  Emp9(3)   Emp10(1)   Emp11(2)
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SendingMessageTimeSum f = new SendingMessageTimeSum ();
		Node n11=f.new Node("EMP11",2,null);
		Node n10=f.new Node("EMP10",1,null);
		Node n9=f.new Node("EMP9",3,null);
		
 	
		Node n6=f.new Node("EMP6",5,null);
		Node n8=f.new Node("EMP8",7,null);
		Node n3=f.new Node("EMP3",3,null);
		Node n4=f.new Node("EMP4",7,null);
	 
		
		List<Node> below5 = new ArrayList<Node>();
		below5.add(n9);
		below5.add(n10);
		Node n5=f.new Node("EMP5",3,below5);
		List<Node> below7 = new ArrayList<Node>();
		below7.add(n11);
		Node n7=f.new Node("EMP7",3,below7);
		
		List<Node> below1 = new ArrayList<Node>();
		below1.add(n5);
		below1.add(n6);
		below1.add(n7);
		Node n1=f.new Node("EMP1",4,below1);
		List<Node> below2 = new ArrayList<Node>();
		below2.add(n8);
		Node n2=f.new Node("EMP2",5,below2);
		
		List<Node> below0 = new ArrayList<Node>();
		below0.add(n1);
		below0.add(n2);
		below0.add(n3);
		below0.add(n4);
		Node root=f.new Node("CEO",6,below0);
		
		System.out.println("total sinding time="+f.findSumTime(root));
	}

}
