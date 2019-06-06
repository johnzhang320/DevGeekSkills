package com.scala.collections

/**
 * @author jzhang
 */
object demoList {
  def main(args: Array[String]) {
     println("--------------List Basic---------------")
     basic()
     println("--------------List Methods and Advanced---------------")
     listMethod()
  }
  def basic() ={
      /**
      *  Lists

        Lists preserve order, can contain duplicates, and are immutable.
        
        scala> val numbers = List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5) 
      */
      println("-----ListTest:immutable/mutable--------")
     
       val immutableList = List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5) 
       var lists=scala.collection.mutable.MutableList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
       
       lists(0)=20
       lists +=10 
       println("immutableList(3)="+immutableList(3) )
       
       for (i<-lists) {
         print(i+" ")
       }
       println("")
       
       println("----List:String List and Match--------")
       
       val names = List("joe","admin","smith","Sample") 
       
       for (name<-names) {
          name match {
            case "joe" => println("This is joe")
            case "admin" => println("This is admin")
            case _ => println("none of the list")
          }
      }
      println("----List:Two Dimisions--------")
      val twodim: List[List[Int]] =
         List(
            List(1, 0, 0),
            List(0, 1, 0),
            List(0, 0, 1)
         )
      for (first<-twodim) {
        for (second<-first) {
          print(second)
        }
        println("")
      }   
      println(twodim(1)(1))
      /**
       * We have created two lists country_1 and country_2. We are concatenating two lists country_1 and 
       * country_2 into a single country list using ::: operator. In the second case we are using .::: operator to 
       * concat country_1 and country_2 into “cont” list and the second list country_2 data will be first inserted 
       * and then country_1 list data is displayed.

          Finally we use concat method to concatenate country_1 and country_2 into the list “con”.
       */
      
      println("----List:Cancatenating lists using :::--------")
      
      val country_1 =  List("India","SriLanka","Algeria")
      val country_2 = List("Austria","Belgium","Canada")
   
      val country = country_1 ::: country_2
      println( "country_1 ::: country_2 : " + country )
   
      val cont = country_1.:::(country_2)
              println( "country_1.:::(country_2) : " + cont )
      val con = List.concat(country_1, country_2)
              println( "List.concat(country_1, country_2) : " + con  )
      
      
      /**
       * reverse method to reverse the elements of the country list.        
       */
      println("----List:Reverse List Order :::--------")     
      
      val country2 = List("Denmark","Sweden","France")
      println("Country List before reversal :" + country2)
      println("Country List after reversal :" + country2.reverse)
      
      println("----List:Creating Uniform Lists:::--------")     
      
      val name = List.fill(6)("Rehan") 
      println( "Name : " + name  )
 
      val id = List.fill(6)(12)         
      println( "Id : " + id  )
        
  }
  def listMethod() = {
     println("\n----List Distinct:::--------")     
      
     val dupList = List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5) 
     val nums = List(5, 1, 4, 3, 2)
     println("\n----List filter (_>2) for List(5, 1, 4, 3, 2)--------")     
     nums.filter(_ > 2).foreach(x=>print(x+" "))
     
     println("\n----List filter _%2==0 for List(5, 1, 4, 3, 2)--------")    
     
     nums.filter(_%2==0).foreach(x=>print(x+" "))
     
     val nums1 = List(5, 1, 4, 3, 2,"hello")
     
     val nums2 = List(5.3, 1.1, 4, 3, 2)
     println(" ")
      
    
     val l1 = List(1.0, 2.0, 3.0)
     val l2 = List(1, 2, 3)
      
     val l = scala.collection.mutable.MutableList(2,3,5,3,3) 
     l(0)=1
      println(f(l1))
    //  println(f(l2))
      
      //addvw(f(nums1),f(nums2))
      println(convert(nums1))
      println(convert(nums2))
      
     println(addvw(convert(nums1),convert(nums2)))
     
      val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")
      
    //  println("show(capitals.get( \"Japan\")) : " + show(capitals.get( "Japan")) )
      
      
   //   println("show(capitals.get( \"India\")) : " + show(capitals.get( "India")) )
      
     val vv = List(100,49,41,40,25,21,21,19,19,18,18,16,15,15,15,15,14,14,13,13,13,13,12,12,11,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,8,8,8,8,8,8,8,8,8,8,8,8,8,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)
    
     var ret3=vv.groupBy(x => x).mapValues(_.size)
 
     
     println(ret3)
     val maxv =ret3.values.max 
     val mapr =ret3.filter(p=>p._2==maxv)
     println (mapr.toList)
      
  }
   
  
  def addvw(v:List[Double],w:List[Double]):List[Double] = {
      return v.zip(w).map(x=>x._1+x._2)
  } 
  def convert[A](v:List[A]) ={          
     var result = new scala.collection.mutable.ListBuffer[Double]()
     for (n<-v) {      
        if (t(n)=="Int" || t(n)=="Double") {
           result+=n.toString().toDouble
        } 
     }
     result.toList
  }
  
  def t[T](v: T) = v match {
       
      case _: Int    => "Int"
      case _: String => "String"
      case _: Double => "Double"
      case _        =>"Unknown"  
  }
  def show(x: Option[String]) = x match {
      case Some(s) => s
      case None => "?"
   }
  def f(l: List[Double]) : List[Double]= {
     return l;
  }
  def f2(l: List[Int]) : List[Double]= {
     val l2 = l.map(x=>x.toDouble)
     return l2;
  }
}