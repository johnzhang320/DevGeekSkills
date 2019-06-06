package com.java.eight.stream.filter.map;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

public class MapList {
 	 
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
	class Staff {

	    private String name;
	    private int age;
	    private BigDecimal salary;
		public String getName() {
			return name;
		}
		
		public Staff(String name, int age, BigDecimal salary) {
			super();
			this.name = name;
			this.age = age;
			this.salary = salary;
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
		public BigDecimal getSalary() {
			return salary;
		}
		public void setSalary(BigDecimal salary) {
			this.salary = salary;
		}
	    
	}
	@Test
	public void MapString () {
		 List<String> alpha = Arrays.asList("a", "b", "c", "d");

		 List<String> collect = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
	     System.out.println("Uppercase:"+collect); //[A, B, C, D]
	     
	  // Extra, streams apply to any data type.
	        List<Integer> num = Arrays.asList(1,2,3,4,5);
	        List<Integer> collect1 = num.stream().map(n -> n * 2).collect(Collectors.toList());
	        System.out.println("Time by 2:"+collect1); //[2, 4, 6, 8, 10]
	}
	@Test
	public void MapObject2 () {
		List<Staff> staffs =Arrays.asList( 
				    new Staff("mkyong", 30, new BigDecimal(10000)),
	                new Staff("jack", 27, new BigDecimal(20000)),
	                new Staff("lawrence", 33, new BigDecimal(30000))
        );
		 List<String> collect = staffs.stream().map(x -> x.getName()).collect(Collectors.toList());
	     System.out.println("Display staffs:"+collect);
        
	}

	public class StaffPublic {
	
	    private String name;
	    private int age;
	    private String extra;
	    //...
	  
		public StaffPublic(String name, int age, String extra) {
			super();
			this.name = name;
			this.age = age;
			this.extra = extra;
		}
		public StaffPublic() {
			super();
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
		public String getExtra() {
			return extra;
		}
		public void setExtra(String extra) {
			this.extra = extra;
		}
		@Override
		public String toString() {
			return "StaffPublic [name=" + name + ", age=" + age + ", extra=" + extra + "]\n";
		}
	    
	}
	//This example shows you how to convert a list of staff objects into a list of StaffPublic objects.
	@Test
	public void Java7ConvertFromOneToAnother () {
		List<Staff> staffs =Arrays.asList( 
			    new Staff("mkyong", 30, new BigDecimal(10000)),
                new Staff("jack", 27, new BigDecimal(20000)),
                new Staff("lawrence", 33, new BigDecimal(30000))
        );
		 List<StaffPublic> result = new ArrayList<>();

	        for (Staff temp : staffs) {

	            StaffPublic obj = new StaffPublic();
	            obj.setName(temp.getName());
	            obj.setAge(temp.getAge());
	            if ("mkyong".equals(temp.getName())) {
	                obj.setExtra("this field is for mkyong only!");
	            }

	            result.add(obj);
	        }
	        System.out.println("Java7 convert:"+result.toString());
	}
	@Test
	public void Java8MapConvertFromOneToAnother () {
		List<Staff> staffs =Arrays.asList( 
			    new Staff("mkyong", 30, new BigDecimal(10000)),
                new Staff("jack", 27, new BigDecimal(20000)),
                new Staff("lawrence", 33, new BigDecimal(30000))
        );
		// convert inside the map() method directly.
        List<StaffPublic> result = staffs.stream().map(temp -> { //parentheses
            StaffPublic obj = new StaffPublic();
            obj.setName(temp.getName());
            obj.setAge(temp.getAge());
            if ("mkyong".equals(temp.getName())) {
                obj.setExtra("this field is for mkyong only!");
            }
            return obj;  // return as object within Collectors.toList() 
        }).collect(Collectors.toList());

        System.out.println("Java8 convert:"+result);
	}
}
