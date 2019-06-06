package com.scala.collections

/**
 * we can use Map to convert List or calculate List to different List
 */
object listMapFlatMap {
  def main(args:Array[String]) {
      val l =List(1,2,3,4,5,6,8)
      println("-------Using Map anonymous function to time each element by 2 and then create new list----")
      val l2 = l.map(x => x*2)
      l2.foreach ( x => print(x+" ") )
      println("-------Using function and function inside of Map to change each element and then create new list----")
      def f(x:Int) = if (x >2) Some(x) else None
      l.map(x=>f(x)).foreach { x => print(x+" ") } 
      println("\n-------Using case class and map to change 2 dim matrix each element and then create new matrix----")
      val twodim: List[List[Int]] =
         List(
            List(1, 0, 0),
            List(0, 1, 0),
            List(0, 0, 1)
      )
    
      case class Person(left: Int, center: Int, right:Int)   // case class
      val rdd =twodim.map(x=>Person(x(0),x(1),x(2))) 
      rdd.foreach { print }
      
      println("\n-------Using function and map to create new three element list for each elements of list----")
      def g(v:Int) = List(v-1,v,v+1)
      l.map(x=>g(x)).foreach { x => print(x+" ") }    // each original element to create three element list
      
      println("\n-------Using function and flatMap to create new three element only for each elements of list----")
      l.flatMap(x=>g(x)).foreach { x => print(x+" ") }  // each original element to create three elements to new one list 
 
      println("\n-------Using toMap method to create a new list----")
  
      val m = Map(1->2,2->4,3->6)
      m.toList.foreach(x=>print(x+" "))  //(1,2) (2,4) (3,6)
      println("\n-------Using flatMap to print out map value list for map----")
      m.flatMap(e=>List(e._2)).foreach(x=>print(x+" "))
      println("\n-------Using function and flatMap to print out map which value is greater than 2---")
      
      def h(k:Int,v:Int) = if (v>2) Some(k->v) else None
      
      m.flatMap(x=>h(x._1,x._2)).foreach(x=>print(x+" "))
     
      println("\n-------Using filter to filter f(2._2)!=None---")
      m.filter(e=>f(e._2)!=None).foreach(x=>print(x +" "))
     
      
  }
  
}