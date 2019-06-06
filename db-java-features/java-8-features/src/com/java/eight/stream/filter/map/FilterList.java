package com.java.eight.stream.filter.map;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;

public class FilterList {
 	 
	class Person {

	    private String name;
	    private int age;

	    public Person(String name, int age) {
	        this.name = name;
	        this.age = age;
	    }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	    
	}
	@Test
	public void filterString () {
		 List<String> lines = Arrays.asList("spring", "node", "johnzhang");

	        List<String> result = lines.stream()                // convert to stream
	        		.filter(s-> !s.equals("johnzhang"))          // filter 
	        		.collect(Collectors.toList());               // collect(Collectors.toList())  
 
	        result.forEach(System.out::println);                //output : spring, node 
	}
	@Test
	public void filterObject () {
		List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );
		Person result1 = persons.stream().filter(x->"jack".equals(x.name)).findAny().orElse(null);
		
		System.out.println("try to find jack:"+result1.name);
		Person result2 = persons.stream()
                .filter(x -> "ahmook".equals(x.name))
                .findAny()
                .orElse(null);

        System.out.println("try to find ahmook:"+result2);
        
        
	}
	
	@Test
	public void filterObjectMultipleConditions () {
		List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );
		 Person result1 = persons.stream()
	                .filter((p) -> "jack".equals(p.getName()) && 20 == p.getAge())
	                .findAny()
	                .orElse(null);

	        System.out.println("result 1 :" + result1.getName());

	        //or like this
	        Person result2 = persons.stream()
	                .filter(p -> {
	                    if ("jack".equals(p.getName()) && 20 == p.getAge()) {
	                        return true;
	                    }
	                    return false;
	                }).findAny()
	                .orElse(null);

	        System.out.println("result 2 :" + result2.getName());
	}
	@Test
	public void filterNull() {
		    Stream<String> language = Stream.of("java", "python", "node", null, "ruby", null, "php");

	        //List<String> result = language.collect(Collectors.toList());

	      /*  List<String> result = language.filter(x -> x!=null).collect(Collectors.toList());

	        result.forEach((x)->System.out.print(x+" "));
	        System.out.println("");
	        */
		    List<String> result = language.filter(Objects::nonNull).collect(Collectors.toList());
	          
		    System.out.println("result-NonNull:"+result.toString()); 
		    
		    String[] array = {"a", "b", "c", "d", "e"};

	        //Arrays.stream
	        Stream<String> stream1 = Arrays.stream(array);
	        stream1.forEach(x -> System.out.println(x));

	        //Stream.of
	        Stream<String> stream2 = Stream.of(array);
	        stream2.forEach(x -> System.out.println(x));
	}
}
