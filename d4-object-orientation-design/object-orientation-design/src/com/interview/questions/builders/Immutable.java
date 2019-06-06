package com.interview.questions.builders;

public final class Immutable {
 /**
  *  1) Immutable objects are by default thread safe, can be shared without synchronization in concurrent environment.
	2) Immutable object simplifies development, because its easier to share between multiple threads without external 
	synchronization.
	
	3) Immutable object boost performance of Java application by reducing synchronization in code.
	
	4) Another important benefit of Immutable objects is reusability, you can cache Immutable object and reuse them,
	 much like String literals and Integers.  You can use static factory methods to provide methods like valueOf(), 
	 which can return an existing Immutable object from cache, instead of creating a new one.
	
	Apart from above advantages, immutable object has disadvantage of creating garbage as well. Since immutable object 
	can not be reused and they are just a use and throw. String being a prime example, which can create lot of garbage 
	and can potentially slow down application due to heavy garbage collection, but again that's extreme
	
	Read more: http://javarevisited.blogspot.com/2013/03/how-to-create-immutable-class-object-java-example-tutorial.
	html#ixzz4jkJn2vub

  */

    private final String name;
    private final String mobile;

    public Immutable (String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }
  
    public String getName(){
        return name;
    }
  
    public String getMobile(){
        return mobile;
    }
}
 
