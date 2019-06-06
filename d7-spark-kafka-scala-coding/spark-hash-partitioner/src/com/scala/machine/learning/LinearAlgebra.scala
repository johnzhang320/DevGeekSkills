package com.scala.machine.learning
import scala.collection.mutable.MutableList

/**
 * @author jzhang
 */
class LinearAlgebra[A](v:List[A],w:List[A]) {
  
  var vv:List[Double] = convert(v)
  var ww:List[Double] = convert(w)
  
  def this(v: List[A]) {
     this(v,Nil)
     vv=convert(v)    
  }  
  def this() {
     this(Nil,Nil)
      
  } 
  // only vv must be initialized , usage: val sum = mew LinearAlgebra(v).vector_sum()    
  def vector_add(): List[Double]= {
     return vv.zip(ww).map(x=>x._1+x._2) 
  }
   def vector_add[A](v:List[A],w:List[A]): List[Double]= {
     var vv1=convert(v)
     var ww1=convert(w)
     return vv1.zip(ww1).map(x=>x._1+x._2) 
  }
 
  def vector_subtract(): List[Double]= {
     return vv.zip(ww).map(x=>x._1-x._2) 
  }
  def vector_subtract[A](v:List[A],w:List[A]): List[Double]= {
     var vv1=convert(v)
     var ww1=convert(w)
     return vv1.zip(ww1).map(x=>x._1-x._2) 
  }
 
  def vector_sum(): Double = {
      return vv.sum 
  }
  def vector_multiply(c:Double) :List[Double] ={
      return vv.map(x=>x*c)
  }
  def vector_multiply[A](c:Double,v:List[A]) :List[Double] ={
      val vv1 = convert(v)
      return vv1.map(x=>x*c)
  }
  def vector_mean() :Double= {
      // compute the vector whose i-th element is the mean of the i-th elements of the input vectors
      val n = vv.length
      return vector_sum()/n
  }
  def vector_mean[A](v :List[A]) :Double= {
      // compute the vector whose i-th element is the mean of the i-th elements of the input vectors
      val vv1 = convert(v)
      val n = vv1.length
      return vv1.sum/n
  }
  def dot() :Double = {  // vv and ww must be initialized , usage: val dot = new LinearAlgebra(v,w).dot()     
     // vv_1*ww_1+vv_2*Ww_2......vv_n*ww_n
     return vv.zip(ww).map(x=>x._1*x._2).sum
  }
  
  def dot(v:List[Double],w:List[Double]):Double= {
      return v.zip(w).map(x=>x._1*x._2).sum
  }

  def sum_of_square() :Double = { // only vv must be initialized , usage: val dot = new LinearAlgebra(v).sum_of_square() 
     return vv.zip(vv).map(x=>x._1*x._2).sum
  }
  
  def sum_of_square(v:List[Double]) :Double = { 
     
     return v.zip(v).map(x=>x._1*x._2).sum
  }
  def magnitude() :Double= { // only vv must be initialized
     return Math.sqrt(sum_of_square(vv))
  }
  
  def squared_distance(): Double={ // vv and ww must be initialized     
    return sum_of_square(vector_subtract(vv,ww))
  }
  def squared_distance(v:List[A],w:List[A]): Double={ // vv and ww must be initialized 
    val vv1 =convert(v)
    val ww1 =convert(w)
    return sum_of_square(vector_subtract(vv1,ww1))
  }
  // Famous Eculidian Distance 
  def distance() :Double={ // vv and ww must be initialized     
     return Math.sqrt(squared_distance())
  }
   // Famous Eculidian Distance 
  def distance[A](v:List[A],w:List[A]) :Double={ // vv and ww must be initialized  
     val vv1 =convert(v)
     val ww1 =convert(w)
     return Math.sqrt(sum_of_square(vector_subtract(vv1,ww1)))
  }
  // vv and ww must be initialized
  def cosine_similarity() :Double={
      return dot(vv,ww) / math.sqrt(dot(vv,vv) * dot(ww,ww))
  } 
 
 /* def make_matrix(num_rows:Int, num_cols:Int, entry_fn:List[Int]=>Double): List[List[Double]]={
      val marray = Array.ofDim[Double](num_rows,num_cols)
      val mlist = MutableList[List[Double]]()
      for (i<-0 until num_rows) {
          for (j<-0 until num_cols) {             
              marray(i)(j)=convert(entry_fn(List(i,j)))
          }
           
      }
       
      return convertM(marray)
  }
    
  */
  
  //-------------------------Utilities--------------------------
  def convert[A](v:List[A]): List[Double]={          
     var result = new scala.collection.mutable.ListBuffer[Double]()
     for (n<-v) {      
        if (t(n)=="Int" || t(n)=="Double") {
           result+=n.toString().toDouble
        } 
     }
     return result.toList
  }
  def convertX[A](v:List[List[A]]): List[List[Double]]={          
     var result = new MutableList[List[Double]]()
     for (i<-0 until v.length) { 
        val l=new MutableList[Double]()
        for (n<-v(i)) {
            if (t(n)=="Int" || t(n)=="Double") {              
               l+=n.toString().toDouble
            } 
        }
        result+=l.toList
     }
     return result.toList
  }
   def convertM[A](v:Array[Array[A]]): List[List[Double]]={          
     var result = new MutableList[List[Double]]()
     for (i<-0 until v.length) { 
        val l=new MutableList[Double]()
        for (n<-v(i)) {
            if (t(n)=="Int" || t(n)=="Double") {              
               l+=n.toString().toDouble
            } 
        }
        result+=l.toList
     }
     return result.toList
  }
  def convert[A] (v:A): Double={
      if (t(v)=="Int" || t(v)=="Double") {
           return v.toString().toDouble
      }
      return 0.0
  }
  def t[T](v: T) = v match {
       
      case _: Int    => "Int"
      case _: String => "String"
      case _: Double => "Double"
      case _: Float => "Double"        
      case _        =>"Unknown"  
  }
}