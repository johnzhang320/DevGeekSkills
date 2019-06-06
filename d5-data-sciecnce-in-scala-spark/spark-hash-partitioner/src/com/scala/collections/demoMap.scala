package com.scala.collections

/**
 * @author jzhang
 */
object demoMap {
  /**
   * The immutable Map class is in scope by default, so you can create an immutable map without an import, like this:

      val states = Map("AL" -> "Alabama", "AK" -> "Alaska")
      
      To create a mutable Map, import it first:
      
      var states = scala.collection.mutable.Map("AL" -> "Alabama")
   * 
   */
  def main(args:Array[String]) {
     /*
      * Adding, removing, and updating mutable Map elements

        The following examples show how to add, remove, and update elements in a mutable Scala Map:
      */
     println("---------------Mutable Map----------------")
     // create an empty map
      var istates = scala.collection.mutable.Map[String, String]()
      
      // create a map with initial elements
      var mstates = scala.collection.mutable.Map("AL" -> "Alabama", "AK" -> "Alaska")
      println("---original map---")
      mstates.foreach(print)
      // add elements with +=
      println("\n---add elements with +=---")
      mstates += ("AZ" -> "Arizona")
      mstates += ("CO" -> "Colorado", "KY" -> "Kentucky")
      mstates.foreach(print)
      println("\n---remove elements with -=---")
      // remove elements with -=
      mstates -= "KY"
      mstates -= ("AZ", "CO")
      mstates.foreach(print)
      
      // iteration of map
      println ("---------------Map Iteration----------------")
      println("\n---update elements by reassigning them mstates(\"AK\") = \"Alaska, The Big State\"---")
      mstates("AK") = "Alaska, The Big State"
      println ("\n iteration for ((k,v) <- mstates) printf(\"key: %s, value: %s\n\", k, v)")
      
      
      for ((k,v) <- mstates) printf("key: %s, value: %s\n", k, v)
      println ("\n ---iteration (tuples style) mstates foreach (x => println (x._1 + \"-->\" + x._2))---")   // scala style x._1 and x._2
      mstates foreach (x => println (x._1 + "-->" + x._2))
     
      println("\n ---iteration (foreach and case)---")
      mstates foreach {case (key, value) => println (key + "-->" + value)}
     
      
      println("---only iteration key of map---")
      var similarItems=0.0;
      var p1Ratings = scala.collection.mutable.Map(
                    "Lady in the Water"-> 3.0, 
                    "Snakes on a Plane"-> 4.0,
                    "You, Me and Dupree"-> 3.5)
      
      var p2Ratings = scala.collection.mutable.Map("Lady in the Water"-> 3.0, 
                    "Snakes on a Plane"-> 4.0,
                    "Crash"-> 3.5)              
      p1Ratings.foreach( (movie) =>  if (p2Ratings.contains(movie._1)) similarItems +=movie._2)
      
      println("test1 similarItems="+similarItems)    
      
     
    //val predictionsAndRatings = p1Ratings.map(x=>(x._1,x._2)).join(p2Ratings.map(x=>(x._1,x._2))).values
      
      
      
  }
}