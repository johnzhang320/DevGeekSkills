package com.scala.machine.learning

/**
 * @author jzhang
 */
class SimpleLinearRegression[A](x:List[A],y:List[A]) {
    val lref = new LinearAlgebra()
    val sref = new statistics()   
    val gref = new GradientDescent()
    val xx = lref.convert(x)
    val yy = lref.convert(y)
    def this() {
      this(Nil,Nil)
    } 
    // after calculate alpha and beta, give x and find y=f(x)=ax+b
    def predict(alpha:Double, beta:Double,x_i:Double): Double={
        return beta*x_i+alpha
    }
    
    def error(alpha:Double, beta:Double,x_i:Double,y_i:Double): Double={
        return y_i - predict(alpha, beta,x_i)
    }
    def sum_of_squared_errors[A](alpha:Double, beta:Double,x:List[A],y:List[A]): Double={
       val xx = lref.convert(x)
       val yy = lref.convert(y)
       return (xx.zip(yy).map(x=> math.pow(error(alpha,beta,x._1,x._2),2))).sum        
    }
    // given training values for x and y, find the least-squared values of alpha and beta e
    def least_sqaures_fit[A](x:List[A],y:List[A]) :List[Double]= {        
        val beta = sref.correlation(x, y) * sref.standard_deviation(y) / sref.standard_deviation(x)
        val alpha= lref.vector_mean(y)-beta*lref.vector_mean(x)
        return List(alpha,beta)
    }
    // total squared variation y and their mean
    def total_sum_of_squares[A](y:List[A]):Double={
        val diffToMean = sref.do_mean(y) // List[Double]
        return lref.sum_of_square(diffToMean)
    }
    
    // the fraction of variation in y captured by the model, which equals
    // 1 - the fraction of variation in y not captured by the model
    def r_squared[A] (alpha:Double, beta:Double,x:List[A],y:List[A]):Double={
      return 1.0 - (sum_of_squared_errors(alpha, beta,x,y) / total_sum_of_squares(y))
    }
    
      
   
    //-----------------------------------------------------------------
    
     def sum_of_squared_errors(alpha:Double, beta:Double): Double={
        
       return (xx.zip(yy).map(x=> math.pow(error(alpha,beta,x._1,x._2),2))).sum        
    }
    // given training values for x and y, find the least-squared values of alpha and beta e
    def least_sqaures_fit() :List[Double]= {        
        val beta = sref.correlation(x, y) * sref.standard_deviation(y) / sref.standard_deviation(x)
        val alpha= lref.vector_mean(y)-beta*lref.vector_mean(x)
        return List(alpha,beta)
    }
    // total squared variation y and their mean
    def total_sum_of_squares():Double={
        val diffToMean = sref.do_mean(yy) // List[Double]
        return lref.sum_of_square(diffToMean)
    }
    
    // the fraction of variation in y captured by the model, which equals
    // 1 - the fraction of variation in y not captured by the model
    def r_squared(alpha:Double, beta:Double):Double={
      return 1.0 - (sum_of_squared_errors(alpha, beta,x,y) / total_sum_of_squares(y))
    }
    // Input function of gradient_descent 
    def squared_error(data:gref.SimpleArgs):Double= {
       val theta = data.theta     // theta(0) -- alpha , theta(1)--beta
       return error(theta(0),theta(1),data.x_i()(0),data.y_i())
    }
   

    def squared_error_gradient(data:gref.SimpleArgs):List[Double]={
        val theta = data.theta     // theta(0) -- alpha , theta(1)--beta
        val alpha_partial_derivative = -2 * error(theta(0),theta(1),data.x_i()(0),data.y_i())
        val beta_partial_derivative =  -2 * error(theta(0),theta(1),data.x_i()(0),data.y_i()) * data.x_i()(0)
        var ret=scala.collection.mutable.MutableList[Double]()
        ret+=alpha_partial_derivative
        ret+=beta_partial_derivative
        return ret.toList
    }
    
    
    def gradient_descent_test() {
        val num_friends_good = List(49,41,40,25,21,21,19,19,18,18,16,15,15,15,15,14,14,13,13,13,13,12,12,11,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,8,8,8,8,8,8,8,8,8,8,8,8,8,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)
        val daily_minutes_good = List(68.77,51.25,52.08,38.36,44.54,57.13,51.4,41.42,31.22,34.76,54.01,38.79,47.59,49.1,27.66,41.03,36.73,48.65,28.12,46.62,35.57,32.98,35,26.07,23.77,39.73,40.57,31.65,31.21,36.32,20.45,21.93,26.02,27.34,23.49,46.94,30.5,33.8,24.23,21.4,27.94,32.24,40.57,25.07,19.42,22.39,18.42,46.96,23.72,26.41,26.97,36.76,40.32,35.02,29.47,30.2,31,38.11,38.18,36.31,21.03,30.86,36.07,28.66,29.08,37.28,15.28,24.17,22.31,30.17,25.53,19.85,35.37,44.6,17.23,13.47,26.33,35.02,32.09,24.81,19.33,28.77,24.26,31.98,25.73,24.86,16.28,34.51,15.23,39.72,40.8,26.06,35.76,34.76,16.13,44.04,18.03,19.65,32.62,35.59,39.43,14.18,35.24,40.13,41.82,35.45,36.07,43.67,24.61,20.9,21.9,18.79,27.61,27.21,26.61,29.77,20.59,27.53,13.82,33.2,25,33.1,36.65,18.63,14.87,22.2,36.81,25.53,24.62,26.25,18.21,28.08,19.42,29.79,32.8,35.99,28.32,27.79,35.88,29.06,36.28,14.1,36.63,37.49,26.9,18.58,38.48,24.48,18.95,33.55,14.24,29.04,32.51,25.63,22.22,19,32.73,15.16,13.9,27.2,32.01,29.27,33,13.74,20.42,27.32,18.23,35.35,28.48,9.08,24.62,20.12,35.26,19.92,31.02,16.49,12.16,30.7,31.22,34.65,13.13,27.51,33.2,31.57,14.1,33.42,17.44,10.12,24.42,9.82,23.39,30.93,15.03,21.67,31.09,33.29,22.61,26.89,23.48,8.38,27.81,32.35,23.84)
    
        
        val r=scala.util.Random
        val theta = List(r.nextDouble, r.nextDouble) // generate two doubles which are less than 1
        val result = gref.minimize_stochastic_simple(
                                              squared_error, 
                                              squared_error_gradient,
                                              num_friends_good,
                                              daily_minutes_good, 
                                              theta,
                                              0.0001                                             
                                              )
        println("Using Gradient Descent!!!!!")
        val alpha=result(0)
        val beta=result(1)
        println ("alpha="+result(0))
        println ("beta="+ result(1))
        
        println ("r-squared="+r_squared(alpha, beta))    }
    
}