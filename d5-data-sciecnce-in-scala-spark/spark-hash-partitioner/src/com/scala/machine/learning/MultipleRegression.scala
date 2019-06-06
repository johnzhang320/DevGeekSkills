package com.scala.machine.learning

/**
 * @author jzhang
 */
class MultipleRegression[A](x:List[A],y:List[A]) {
    val lref = new LinearAlgebra()
    val sref = new statistics()   
    val gref = new GradientDescent()
    val slinear = new SimpleLinearRegression()
    val xx = lref.convert(x)
    val yy = lref.convert(y)
    def this() {
      this(Nil,Nil)
    } 
    // after calculate alpha and beta, give x and find y=f(x)=ax+b
    def predict(x_i:List[Double],beta:List[Double]): Double={
        return lref.dot(x_i,beta)
    }
    
    def error(x_i:List[Double],y_i:Double,beta:List[Double]): Double={
        return y_i - predict(x_i, beta)
    }
   
    
    def multiple_r_squared(xx:List[List[Double]], yy:List[Double], beta:List[Double]): Double={
        
       val x = lref.convertX(xx)
        
       val y = lref.convert(yy)
       var sum_of_squared_errors=0.0 
       for (i<-0 until y.length) {
           val er = error(x(i),y(i),beta)
           val er2 = er*er
           sum_of_squared_errors+=er2
       }
       return 1.0 - sum_of_squared_errors / slinear.total_sum_of_squares(y)
      
    }
       

    
    
    def squared_error(data:gref.MultipleArgs):Double= {       
       val er= error(data.x_i(),data.y_i(),data.theta())
       return er * er
    }
    
    // """the gradient corresponding to the ith squared error term""" , do partial derivative on difference between yi - predict_yi

    def squared_error_gradient(data:gref.MultipleArgs):List[Double]={
        var ret=scala.collection.mutable.MutableList[Double]()
        for (xij <- data.x_i()) {
            ret+= (-2 * xij * error(data.x_i(),data.y_i(),data.theta()))       
        }
            
        return ret.toList
    }
    
     //-----------------------------------------------------------------
    
    
     def estimate_beta[A](x:List[(List[A])], y:List[Double]) :List[Double]={
       
        val r=scala.util.Random
        var beta_initial=List(r.nextDouble(),r.nextDouble(),r.nextDouble,r.nextDouble)
        
       
        val result = gref.minimize_stochastic_multiple(
                               squared_error, 
                               squared_error_gradient, 
                               x, y, 
                               beta_initial, 
                               0.001)   
                                              
        println("Using Gradient Descent!!!!!")
        println ("theta="+result )
        
        val xx = lref.convertX(x)
        
        println( "multiple_r_squared="+multiple_r_squared(xx, y, result) )
        
        return result
         
    } 
    
}