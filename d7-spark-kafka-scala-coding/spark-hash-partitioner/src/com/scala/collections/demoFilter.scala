package com.scala.collections

/**
 * @author jzhang
 */
object demoFilter {
  def main(args:Array[String]) {
      def f(x:Int) = if (x >2) x else None
      
      
      
      val m = Map(1->2,2->4,3->6,4->0)
      println("\n-------Using filter to filter f(2._2)!=None---")
      m.filter(e=>f(e._2)!=None).foreach(x=>print(x +" "))
      println("\n-------Using filter to filter filter(_.length > 5 and filter(_.startsWith(\"a\")------")
      val fruits = List("orange", "peach", "apple", "banana")

      fruits.filter(_.length > 2).foreach { x => print(x+" ") }
      println("\n----------------")
      fruits.filter(_.startsWith("a")).foreach { x => print(x+" ") }
  }
}