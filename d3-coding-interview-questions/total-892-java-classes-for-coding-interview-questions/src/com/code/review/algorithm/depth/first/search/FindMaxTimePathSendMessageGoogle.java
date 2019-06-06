package com.code.review.algorithm.depth.first.search;

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

public class FindMaxTimePathSendMessageGoogle {
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
	
	 
	/**
	 *        CEO(6)
               /   \          \      \
          Emp1(4)     Emp2(5) Emp3(3) Emp4(7)
          /   \  \         \
         /     \   \         \
   Emp5(3) Emp6(5) Emp7(8) Emp8(7)
       /  \           \        \
      /     \          \        \
  Emp9(3)   Emp10(1)   Emp11(2)  EMP12(4)
                                  \
	                               \
	                               EMP13(3)  */
    /**
     *  DFS find root to bottom level node which have children but child node does not include
     *  CEO->EMP1->EMP5, CEO->EMP1->EMP7
     *  node.belows==null return
     *  but non-tail recursion call and execute part below the call
     *  means below execute take node stack pop out 	
     *  take max time from top to bottom as message transfer time
     */
	int max=Integer.MIN_VALUE;
	
	
	/**
	 * Find Max Time Path : apply DFS algorithm and horizontal is child loop and add list as result
	 * most important is 
	 * only have boundary condition below horizontal loop
	 * after horizontal loop, we put post condition sum>maxTime, means each result does not contain
	 * last node (this question is not incuding the leaf node at all)
	 * front boundary condition met, all horizontal loop is neighbor nodes which doesn't contain root
	 * therefore we must add root information before jump into dfs call
	 * (1) List.add(root.EmpName) and Call add root.sendTime
	 * (2) in loop for belows, list.add(child.EmpName), Recursion Call and then list.remove(list.size()) (backtrace)
	 * (3) After loop finished, CEO to second bottom level stack pop out check each time and path pair 
	 * (4) Return path to public variable result and max 
	 * 
	 * @param root
	 * @param sum
	 */
	
	
	public void findMaxTopBottomTimePath(Node root,int sum,List<String> list,String result[],int max[]) {
		if (root.belows==null ) {
 			return;
		}
		  
		for (Node child: root.belows) {
			list.add(child.empName);     // path order is same as reverse order of call stack
			findMaxTopBottomTimePath(child,sum+child.sendingTime,list,result,max);
			list.remove(list.size()-1);  // reach node.belows =null , back trace to parent
	 	}
		if (sum>max[0]) {     // sum order is same of calling stack storing order
			result[0] = list.toString();
			max[0]=sum;
		}
		 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindMaxTimePathSendMessageGoogle f = new FindMaxTimePathSendMessageGoogle ();
		Node n11=f.new Node("EMP11",2,null);
		Node n10=f.new Node("EMP10",1,null);
		Node n9=f.new Node("EMP9",3,null);
		Node n12=f.new Node("EMP12",4,null);
		Node n13=f.new Node("EMP13",3,null);
 	
		Node n6=f.new Node("EMP6",5,null);
		Node n8=f.new Node("EMP8",7,null);
		Node n3=f.new Node("EMP3",3,null);
		Node n4=f.new Node("EMP4",7,null);
	 
		List<Node> below8 = new ArrayList<Node>();
		below8.add(n12);
		n8.belows=below8; 
		
		List<Node> below12 = new ArrayList<Node>();
		below12.add(n13);
		n12.belows=below12;
		
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
		//f.findMaxTopBottomTime(root, root.sendingTime);
		List<String> list = new ArrayList<String>();
		list.add(root.empName);
		String result[]= new String[1];
		int max[] = new int[1];
		max [0] = Integer.MIN_VALUE;
		
		f.findMaxTopBottomTimePath(root, root.sendingTime, list, result, max );
		System.out.println("Max Sending time="+max[0]+"\nPath="+result[0]);
	
	}

}
