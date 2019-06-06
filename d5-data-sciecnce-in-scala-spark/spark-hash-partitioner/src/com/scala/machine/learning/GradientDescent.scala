package com.scala.machine.learning

/**
 * @author jzhang
 */
import scala.collection.mutable._
import scala.util.Random._
class GradientDescent[A](v:List[A]) {
  
   def this() {
     this (Nil)
   }
   val lref = new LinearAlgebra()
   val vv = lref.convert(v) 
 
   
   def sum_of_squares() : Double={ 
    //computes the sum of squared elements in v
    return vv.zip(vv).map(x=>x._1 * x._2).sum
   }
   def sum_of_squares[A](v:List[A]) : Double={ 
      //computes the sum of squared elements in v
      val vv1 = lref.convert(v)
      return vv1.zip(vv1).map(x=>x._1 * x._2).sum
   }
   def square[A](x:Double):Double={
       val xx = lref.convert(x)
       return xx*xx
   }
   // calculate derivative
   def difference_quotient[A](f:Double=>Double, x:A, h:Double) :Double = {
       val xx = lref.convert(x)      
       return (f(xx+h)-f(xx)) / h
   }
   
   // calculate partial derivative on i-th elements, zero
   def partial_difference_quotient[A](f:Double=>Double, v:List[A], i:Int, h:Double): Double={
      if (i>v.length) return 0.0
      val vv = lref.convert(v(i))      
      return (f(vv+h)-f(vv)) / h
   }
   // make all partial derivative of elements of v
   def estimate_gradient[A](f:Double=>Double, v:List[A], h:Double=0.00001) : List[Double]={
       var w = MutableList[Double]()  // create mutable list w
       for (i<-0 until v.length) {           
           w+=partial_difference_quotient(f,v,i,h)  // append data to end of list
       }
       return w.toList
   }
   // very important , gradient descent input function, calculate all 2nd level derivative of element of v
   def sum_of_squares_gradient[A](v:List[A]) : List[Double] ={
       val vv1 = lref.convert(v)
       return vv1.map(x=>x*2)
   } 
   // very important will be used in gradient descent, v , direction, step_size must be Double type vector
   def step(v:List[Double], direction:List[Double], step_size:Double):List[Double]={
        //move step_size in the direction from v
       return v.zip(direction).map(x=>x._1+x._2*step_size)
     
   }
 //  case class gd_args(x_i:Double,y_i:Double,theta:List[Double],x_m:List[Double]=Nil,theta_m:List[Double]=Nil)
   
 
   trait Params {
        def x_i():List[Double] 
        def y_i():Double
        def theta():List[Double]
       
   }
   class SimpleArgs(x:Double,y:Double,thetaValue:List[Double]) extends Params {      
        def x_i():List[Double]={return List(x)}
        def y_i():Double = {return y}
        def theta():List[Double]={return thetaValue}
   }
    
   class MultipleArgs(x:List[Double],y:Double,thetaValue:List[Double]) extends Params {       
        def x_i():List[Double]={return x}
        def y_i():Double = {return y}
        def theta():List[Double]={return thetaValue}
   } 
   
  def round(x: Double)(p: Int): Double = {
    var A = x.toString().split('.')
    (A(0) + "." + A(1).substring(0, if (p > A(1).length()) A(1).length() else p)).toDouble
  } 
   /*
    *   return minimize_stochastic(negate(target_fn),       # target_fn=squared_error(x_i, y_i, theta) in simple_linear_regression
                                   negate_all(gradient_fn), # gradient_fn= squared_error_gradient(x_i, y_i, theta) in simple_linear_regression
                                   x, y,                    # x = num_friends_good, y = daily_minutes_good in simple_linear_regression
                                   theta_0, alpha_0)        # theta = [random.random(), random.random()],  0.0001 in simple_linear_regression
    *  p1.isInstanceOf[SimpleArgs]
    */
    def minimize_stochastic_simple[A](
       target_fn:SimpleArgs=>Double, 
       gradient_fn:SimpleArgs=>List[Double], 
       xx:List[A], yy:List[A], 
       theta_0:List[Double],
       alpha_0:Double=0.01 
       ):List[Double]={       
     
       val x = lref.convert(xx)
       val y = lref.convert(yy)
       val data = x.zip(y)
       var theta = theta_0   // initial guess
       var alpha = alpha_0   // initial step_size
       var min_theta:List[Double]=List()  
       var min_value=Integer.MAX_VALUE.toDouble   // the minimum so far
       var iterations_with_no_improvement = 0
       // if we ever go 100 iteration with no improvement, stop
       while (iterations_with_no_improvement < 100) {
         
           var value:Double=0.0
         
           for (i<-0 until x.length) {
                  
                  val gd_params=new SimpleArgs(x(i),y(i),theta)   // apply new theta calculated in 136th line             
           
                  value+=target_fn(gd_params)                     // get new value by square sum of yi-predict yi based on new theta
           }
           
           if (value<min_value) { //  if we've found a new minimum, remember it and go back to the original step size
               min_theta = theta
               min_value = value
               iterations_with_no_improvement = 0
               alpha = alpha_0
           } else {  //otherwise we're not improving, so try shrinking the step size
               iterations_with_no_improvement += 1
               alpha *= 0.9
           }
           // and take a gradient step for each of the data points       
           //val data1 = scala.util.Random.shuffle(data)  // shuffle result in wrong result !
           
           for (d<-data) {               
                  val gd_params=new SimpleArgs(d._1,d._2,theta)
                  val gradient_i = gradient_fn(gd_params) 
                  theta = lref.vector_subtract(theta,lref.vector_multiply(alpha, gradient_i))
              
           }
       }
       return min_theta
   }
    
    
   def minimize_stochastic_multiple[A](
       target_fn:MultipleArgs=>Double, 
       gradient_fn:MultipleArgs=>List[Double], 
       xx:List[List[A]], yy:List[A], 
       theta_0:List[Double],
       alpha_0:Double=0.01
       ):List[Double]={      
     
       val x = lref.convertX(xx)
        
       val y = lref.convert(yy)
       val data = x.zip(y)
       var theta = theta_0   // initial guess
       var alpha = alpha_0   // initial step_size
       var min_theta:List[Double]=List()  
       var min_value=Integer.MAX_VALUE.toDouble   // the minimum so far
       var iterations_with_no_improvement = 0
       // if we ever go 100 iteration with no improvement, stop
       while (iterations_with_no_improvement < 100) {
           var value:Double=0.0
           for (i<-0 until x.length) {
               val gd_params =new MultipleArgs(x(i),y(i),theta)
               value+=target_fn(gd_params)              
               
           }
          // value = round(value)(5)  // round it to 5 after dot
          
           if (value<min_value) { //  if we've found a new minimum, remember it and go back to the original step size
               min_theta = theta
               min_value = value
               iterations_with_no_improvement = 0
               //println("min_value=",value,",min_theta=",theta)
               alpha = alpha_0
           } else {  //otherwise we're not improving, so try shrinking the step size
               iterations_with_no_improvement += 1
               alpha *= 0.9
           }
           // and take a gradient step for each of the data points       
           val data1 = scala.util.Random.shuffle(data)   // prevent same solution from repeated forever
           for (d<-data1) {              
              val gd_params =new MultipleArgs(d._1,d._2,theta)
              val gradient_i = gradient_fn(gd_params) 
              theta = lref.vector_subtract(theta,lref.vector_multiply(alpha, gradient_i))      
              //println(theta)
           }
       }
       return min_theta
   }
  

   def in_random_order(data:List[(Double,Double)]):List[(Double,Double)]= {
        
       val newData=scala.util.Random.shuffle(data)  // shuffle in pair of x corresponding y
     
       return newData
   }
}