package com.scala.machine.learning

/**
 * @author jzhang
 */
object GradientDescentExample {
   def main(args:Array[String]) {
      val ref = new GradientDescent()
      val v = List(2,3,4,5,7,8.2,1201.0)
      val y = List(1,2,3,4,5,6,7)
      
      println( "v="+v)
      println( "partial_difference_quotient(f, v, 3, h)="+ref.partial_difference_quotient(ref.square, v, 3, 0.001))
      println( "estimate_gradient(f, v, h=0.00001)="+ref.estimate_gradient(ref.square, v, h=0.00001))     
      val gradient = ref.sum_of_squares_gradient(v) 
      println( "gradient = sum_of_squares_gradient(v) ="+ gradient) 
      println( "step(v, direction, step_size)="+ref.step(v, gradient, -0.01) )
      println( "sum_of_squares_gradient(v)="+ref.sum_of_squares_gradient(v)) 
     /*val d = v.zip(y)
      val data = ref.in_random_order(d)
      println( "in_random_order(x,y)="+data)
      val xx = data.map(x=>x._1)
      val yy = data.map(y=>y._2)      
      println( "in_random_order x="+xx+",y="+yy)*/
      
      
   }
}