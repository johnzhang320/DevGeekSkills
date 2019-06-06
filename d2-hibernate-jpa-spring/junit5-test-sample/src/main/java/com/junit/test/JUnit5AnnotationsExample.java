package com.junit.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
 
import org.junit.jupiter.api.TestInfo;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
 
@RunWith(JUnitPlatform.class)
public class JUnit5AnnotationsExample {
 
    @Test
    @DisplayName("Add operation test")
   // @RepeatedTest(5)
    void addNumber(TestInfo testInfo) {
    	 System.out.println("Addnumber is called");
    }
     
    @Test
    @DisplayName("Minus operation test")
     void mniusNumber(TestInfo testInfo) {
    	 System.out.println("minus a number is called");
    }
    
    @Test
    @DisplayName("multiple operation test")
     void multipleNumber(TestInfo testInfo) {
    	 System.out.println("multiple a number is called");
    }
    
    @Test
    @DisplayName("divide operation test")
     void divideNumber(TestInfo testInfo) {
    	 System.out.println("divide a number is called");
    }
    
    @Test
    @DisplayName("divide1 operation test")
     void divideNumber1(TestInfo testInfo) {
    	 System.out.println("divide1 a number is called");
    }
    
    
    @Test
    @DisplayName("divide2 operation test")
     void divideNumber2(TestInfo testInfo) {
    	 System.out.println("divide2 a number is called");
    }
    
    @Test
    @DisplayName("divide3 operation test")
     void divideNumber3(TestInfo testInfo) {
    	 System.out.println("divide3 a number is called");
    }
    
    @BeforeAll
    public static void init(){
        System.out.println("Before All method called");
    }
    @AfterAll
    public static void afterall(){
        System.out.println("After All method called");
    }
    
    @BeforeEach
    public void beforeEach(){
        System.out.println("before Each method called");
    }
    @AfterEach
    public void afterEach(){
        System.out.println("After Each method called");
    }
}