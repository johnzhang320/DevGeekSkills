package com.scala.collections

/**
 *  Problem

    For a variety of reasons, including removing null values from your code, you want to use what I call the Option/Some/None pattern.
    Or, if you’re interested in a problem (exception) that occurred while processing code, you may want to return Try/Success/Failure 
    from a method instead of Option/Some/None.
 */
 /**
  * Solution

    There is some overlap between this recipe and the previous recipe, “Eliminate null Values from Your Code”. That recipe shows 
    how to use Option instead of null in the following situations:

    Using Option in method and constructor parameters
    Using Option to initialize class fields (instead of using null)
    Converting null results from other code (such as Java code) into an Option
  * 
  */
  /**
   * See that recipe for examples of how to use an Option in those situations.

    This recipe adds these additional solutions:

    Returning an Option from a method
    Getting the value from an Option
    Using Option with collections
    Using Option with frameworks
    Using Try/Success/Failure when you need the error message (Scala 2.10 and newer)
    Using Either/Left/Right when you need the error message (pre-Scala 2.10)
   * 
   */
object DemoOption {
  /*
   * Returning an Option from a method

      The toInt method used in this book shows how to return an Option from a method. It takes a String as input and 
      returns a Some[Int] if the String is successfully converted to an Int, otherwise it returns a None:
   */
   def toInt(s: String): Option[Int] = {
        try {
            Some(Integer.parseInt(s.trim))
        } catch {
            case e: Exception => None
        }
    }
    
   def main(args:Array[String]) {
      // input null , return None
      val s1=null
      
      println (toInt(s1))  //None
      
      
      
      val s2 = "10"
      val s4= toInt(s2)
      println("s4+hehe="+s4.get+"heheh")
      println(toInt(s2))  // Some(10)
      
      val s3 = "1AS0"
      println(toInt(s3))  // None
      
      
   }
}