package com.code.review.array.string.easy.duplicate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonDuplicateChar {
	 /**
     *  Find first non repeated data in array , could repeat any times
     *  My approach:
     *  pair (count,index)
     *  Define Map<arr[i], pair> 
     *  after add all array. iterate map  
     */
    public static int findFirstNonRepeatElem(int arr[]) {
   	 
   	 Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
   	 int result=-1;
   	 for (int i=0 ; i<arr.length;i++) {
   		 int key = arr[i];
   		 if (countMap.containsKey(key)) {
   			 countMap.put(key, countMap.get(key)+1);
   		 } else {
   			 countMap.put(key, 1);
   			 
   		 }
   	 }
   	 // search array element, find first count ==1
   	 for (Integer key: arr) {
   		 if (countMap.containsKey(key)) {
   			 if (countMap.get(key)==1) {
   				 result = key;
   				 break;
   			 }
   		 }
   	 }
   	 return result;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {12,4,3,12,4,6,8,3,6,10,20};
		System.out.println(findFirstNonRepeatElem(arr));
	}

}
