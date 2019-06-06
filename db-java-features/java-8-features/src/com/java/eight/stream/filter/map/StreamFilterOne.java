package com.java.eight.stream.filter.map;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamFilterOne {
 /*
  * Classes to support functional-style operations on streams of elements, Stream contains classes for processing sequences of elements.
  * 
 
	String[] arr = new String[]{"a", "b", "c"};
	Stream<String> stream = Arrays.stream(arr);
	stream = Stream.of("a", "b", "c");
	A stream() default method is added to the Collection interface and allows creating a Stream<T> using any collection as an element source:
	 
	Stream<String> stream = list.stream();
    ArrayList<String> list = new ArrayList<>();
	list.add("One");
	list.add("OneAndOnly");
	list.add("Derek");
	list.add("Change");
	list.add("factory");
	list.add("justBefore");
	list.add("Italy");
	list.add("Italy");
	list.add("Thursday");
	list.add("");
	list.add("");
	The following code creates a Stream<String> of the List<String>, finds all elements of this stream which contain 
	char “d” and creates a new stream containing only the filtered elements:
	
	Stream<String> stream = list.stream().filter(element -> element.contains("d"));
	As we have seen in the above example, the working of stream can be explained in three stages:
	1. Create a stream
	
	2. Perform intermediate operations on the initial stream to transform it into another stream and so on on further 
	intermediate operations. In the above example, the filter() operation is intermediate operation, there can be more 
	than one intermediate operations.
	
	3. Perform terminal operation on the final stream to get the result. In the above example, the count() operation 
	is terminal operation.

	Java Stream Features
	
	1. Stream does not store the elements. it simply performs the aggregate operations(such as filter() and count() 
	that we have seen in the above example) to get the desired stream of data.
	
	2. The aggregate operations that we perform on the collection, array or any other data source do not change the 
	data of the source, they simply return a new stream. For example the code we have seen above is filtering the 
	strings with length less than 6 using the stream operations but it didn’t change the elements of the list.
	
	3. All the stream operations are lazy in nature which means they are not executed until they are needed. For example, 
	if we want to display only the first 2 elements of a list using stream, the stream operation would stop at the end of 
	second iteration after displaying the second element of list.
  */
   @Test	
   public void basicStreamFilter() {
	   // Original code to find length <6
	   List<String> names = new ArrayList<String>();
		names.add("Ajeet");
		names.add("Negan");
		names.add("Aditya");
		names.add("Steve");
		int count = 0;
		for (String str : names) {
		   if (str.length() < 6) 
			count++; 
		}
	    System.out.println("There are "+count+" strings with length less than 6");
	  //Using Stream and Lambda expression
	    /*
	     * the stream() method returns a stream of all the names, the filter() method returns another stream of 
	     * names with length less than 6, the count() method reduces this stream to the result. All these operations 
	     * are happening parallelly which means we are able to parallelize the code with the help of streams. 
	     * Parallel execution of operations using stream is faster than 
	     */
		long count2 = names.stream().filter(str->str.length()<6).count();
		System.out.println("There are "+count2+" strings with length less than 6");
		System.out.println("----------All----------- ");
		 names.stream().forEach(System.out::println);
		 System.out.println("-----------length<6---------- ");
		names.stream().filter(x->x.length()<6).forEach(System.out::println);
		System.out.println("-----------match steve---------- ");
		Stream<String> steve=names.stream().filter(x->x.equals("Steve"));
		steve.forEach(System.out::println);
		System.out.println("--------------------- ");
		System.out.println("Count of Reduce by filter="+names.stream().filter(x->x.length()<6).count());
   }
}
