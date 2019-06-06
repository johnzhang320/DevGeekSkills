package com.java.eight.optional;

import java.util.Optional;

import org.junit.Test;

public class OptionalTester {
	/**
	 *Java 8 has introduced a new class Optional in java.util package. It is used to represent a value is present or absent. The main 
	 *advantage of this new construct is that No more too many null checks and NullPointerException. It avoids any runtime 
	 *NullPointerExceptions and supports us in developing clean and neat Java APIs or Applications. Like Collections and arrays, 
	 *it is also a Container to hold at most one value. Let us explore this new construct with some useful examples.

		Advantages of Java 8 Optional:
		
		Null checks are not required.
		No more NullPointerException at run-time.
		We can develop clean and neat APIs.
		No more Boiler plate code
		1. Optional Basic example
		
		Optional.ofNullable() method returns a Non-empty Optional if a value present in the given object. Otherwise returns empty Optional.

		Optionaal.empty() method is useful to create an empty Optional object.
	 */
	@Test
	public void OptionString( ) {
		Optional<String> gender = Optional.of("MALE");
        String answer1 = "Yes";
        String answer2 = null;

        System.out.println("1. Non-Empty Optional:" + gender);
        System.out.println("2. Non-Empty Optional: Gender value : " + gender.get());
        System.out.println("3. Empty Optional: " + Optional.empty());
        // if answer1 is present, return Optional[yes] 
        Optional <String> result = Optional.ofNullable(answer1);
        System.out.println("4.1 ofNullable on Non-Empty Optional string : " + result.get());
        // if answer2 is not present, result return Optional.empty
         
        System.out.println("4.2 ofNullable on Non-Empty Optional: " + result);
        // if answer2 is not present, return other string
        result = Optional.ofNullable(answer2);
        String str=result.orElse("No");
        System.out.println("4.3 ofNullable on Non-Empty Optional: " + str);
        // simplified
        System.out.println("5. ofNullable on Empty Optional: " + Optional.ofNullable(answer2).orElse("No"));
        
        // java.lang.NullPointerException
        //System.out.println("6. ofNullable on Non-Empty Optional: " + Optional.of(answer2));
        Integer mya = null;
        Optional<Integer> a = Optional.ofNullable(mya);
        Optional<Integer> b = Optional.ofNullable(12);
        System.out.println("6.sum="+summary(a,b));
	}
	
	 public static Integer summary(Optional<Integer> a, Optional<Integer> b){
		
	    //Optional.isPresent - checks the value is present or not
			
	    System.out.println("First parameter is present: " + a.isPresent());
	    System.out.println("Second parameter is present: " + b.isPresent());
			
	    //Optional.orElse - returns the value if present otherwise returns
	    //the default value passed.
	     Integer value1 = a.orElse(new Integer(0));
	    	
	    //Optional.get - gets the value, value should be present
	    Integer value2 = b.get();
	    
	    System.out.println("Value1="+value1+",value2="+value2);
	    
	    return value1 + value2;
	
	}
	@Test
	public void OptionalMapFlatMap() {
		Optional<String> nonEmptyGender = Optional.of("male");
        Optional<String> emptyGender = Optional.empty();

        System.out.println("Non-Empty Optional:: " + nonEmptyGender.map(String::toUpperCase));
        System.out.println("Empty Optional    :: " + emptyGender.map(String::toUpperCase));

        Optional<Optional<String>> nonEmptyOtionalGender = Optional.of(Optional.of("male"));
        System.out.println("Optional value   :: " + nonEmptyOtionalGender);
        System.out.println("Optional.map     :: " + nonEmptyOtionalGender.map(gender -> gender.map(String::toUpperCase)));
        System.out.println("Optional.flatMap :: " + nonEmptyOtionalGender.flatMap(gender -> gender.map(String::toUpperCase)));
	}
}
