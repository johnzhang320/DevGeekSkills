package com.java.eight.streams.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
public class StreamsFilterTester {
	/**
	 * /**
	 * When do we go for Java 8 Stream API? Why do we need to use Java 8 Stream API in our projects?
	  When our Java project wants to perform the following operations, it’s better to use Java 8 Stream 
	  API to get lot of benefits:
	
	    When we want perform Database like Operations. For instance, we want perform groupby operation,
	    orderby operation etc.
	    When want to Perform operations Lazily.
	    When we want to write Functional Style programming.
	    When we want to perform Parallel Operations.
	    When want to use Internal Iteration
	    When we want to perform Pipelining operations.
	    When we want to achieve better performance.
	 * Explain Differences between Collection API and Stream API?

 	 
	 * use of Streams filter() with collect(), findAny() and orElse()
	 */
	 public static void testManualFilter() {
		 List<String> lines = Arrays.asList("spring", "node", "mkyong");
		 List<String> result = getFilterOutput(lines, "mkyong");
		 for (String temp : result) {
		 	System.out.println("Manual filter:"+temp);	//output : spring node
		 }
	 }
	
	 private static List<String> getFilterOutput(List<String> lines, String filter) {
        List<String> result = new ArrayList<String>();
        for (String line : lines) {
            if (!"mkyong".equals(line)) {
                result.add(line);
            }
        }
        return result;
	}
	 /** Convert to Streams API: The equivalent example in Java 8, using stream.filter() to filter a List,
	 	 and collect() to convert a stream.
	 */
	public static void testStreamsFilter() { 
		List<String> lines = Arrays.asList("spring", "node", "mkyong","johnZhang","denniecha");

		List<String> result = lines.stream() 			//convert list to stream
			.filter(line -> !"mkyong". equals (line))	//filters the line, equals to "mkyong"
			.collect(Collectors.toList());			//collect the output and convert streams to a List

		result.forEach(System.out::println);			//output : spring node
	}
	public static void testStreamsMap() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		List<Integer> twoEvenSquares = 
		    numbers.stream()
		           .filter(n -> {
		                    System.out.println("filtering " + n); 
		                    return n % 2 == 0;
		                  })
		           .map(n -> {
		                    System.out.println("mapping " + n);
		                    return n * n;
		                  })
		           .limit(2)
		           .collect(Collectors.toList());
		twoEvenSquares.forEach(System.out::println);
	}
	public static void main(String[] args) {
		// testManualFilter() 
		
		testManualFilter();
		
		// Equivalent example
		testStreamsFilter();
		
		testStreamsMap();
		/**
		 * The ‘map’ method is used to map each element to its corresponding result.
		 *  The following code segment prints unique squares of numbers using map.
		 */
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		//get list of unique squares
		List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
		
		squaresList.forEach(System.out::println);
		/**
		 *  The ‘filter’ method is used to eliminate elements based on a criteria. The following code segment prints 
		 *  a count of empty strings using filter.
		 */
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		//get count of empty string
		long count = strings.stream().filter(string -> string.isEmpty()).count();
		
		System.out.println("Count="+count);
		
		List<String> result1 = strings.stream().filter(string -> !string.isEmpty()).sorted().collect(Collectors.toList());
		result1.forEach(System.out::println);
	}

}
