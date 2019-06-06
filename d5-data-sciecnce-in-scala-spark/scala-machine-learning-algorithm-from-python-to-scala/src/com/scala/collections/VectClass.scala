package com.scala.collections

/**
 * @author jzhang
 */
class VectClass[A](v:List[A],w:List[A]) {
  var vv:List[Double] = convert(v)
  var ww:List[Double] = convert(w)
  def this(v: List[A]) {
     this(v,Nil)
     vv=convert(v)    
  }  
  // only vv must be initialized , usage: val sum = mew VectClass(v).vector_sum()    
  def vector_add(): List[Double]= {
     return vv.zip(ww).map(x=>x._1+x._2) 
  }
  def vector_subtract(): List[Double]= {
     return vv.zip(ww).map(x=>x._1-x._2) 
  }
  private def vector_subtract(v:List[Double],w:List[Double]): List[Double]= {
     return v.zip(w).map(x=>x._1-x._2) 
  }
  def vector_sum(): Double = {
      return vv.sum 
  }
  def vector_multiply(c:Double) :List[Double] ={
      return vv.map(x=>x*c)
  }
  def vector_mean() :Double= {
      // compute the vector whose i-th element is the mean of the i-th elements of the input vectors
      val n = vv.length
      return vector_sum()/n
  }
  def dot() :Double = {  // vv and ww must be initialized , usage: val dot = new VectClass(v,w).dot()     
     // vv_1*ww_1+vv_2*Ww_2......vv_n*ww_n
     return vv.zip(ww).map(x=>x._1*x._2).sum
  }
  
  private def dot(v:List[Double],w:List[Double]):Double= {
      return v.zip(w).map(x=>x._1*x._2).sum
  }
  def sum_of_square() :Double = { // only vv must be initialized , usage: val dot = new VectClass(v).sum_of_square() 
     return vv.zip(vv).map(x=>x._1*x._2).sum
  }
  
  private def sum_of_square(v:List[Double]) :Double = { 
     
     return v.zip(v).map(x=>x._1*x._2).sum
  }
  def magnitude() :Double= { // only vv must be initialized
     return Math.sqrt(sum_of_square(vv))
  }
  
  def squared_distance(): Double={ // vv and ww must be initialized     
    return sum_of_square(vector_subtract(vv,ww))
  }
  // Famous Eculidian Distance 
  def distance() :Double={ // vv and ww must be initialized     
     return Math.sqrt(squared_distance())
  }
  // vv and ww must be initialized
  def cosine_similarity() :Double={
      return dot(vv,ww) / math.sqrt(dot(vv,vv) * dot(ww,ww))
  } 
  
  //-------------------------Utilities--------------------------
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
}