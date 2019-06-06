package com.scala.machine.learning
import scala.collection.mutable.MutableList
/**
 * @author jzhang
 */
object LogisticsRegression {
    val lref = new LinearAlgebra()
    val sref = new statistics()   
    val gref = new GradientDescent()
    val slinear = new SimpleLinearRegression()  
    val mlinear = new MultipleRegression()  
    val work = new WorkWithData()
    val ml = new Mach‌ineLearning();
    val data = List(List(0.7,48000,1),List(1.9,48000,0),List(2.5,60000,1),List(4.2,63000,0),List(6,76000,0),List(6.5,69000,0),List(7.5,76000,0),List(8.1,88000,0),List(8.7,83000,1),List(10,83000,1),List(0.8,43000,0),List(1.8,60000,0),List(10,79000,1),List(6.1,76000,0),List(1.4,50000,0),List(9.1,92000,0),List(5.8,75000,0),List(5.2,69000,0),List(1,56000,0),List(6,67000,0),List(4.9,74000,0),List(6.4,63000,1),List(6.2,82000,0),List(3.3,58000,0),List(9.3,90000,1),List(5.5,57000,1),List(9.1,102000,0),List(2.4,54000,0),List(8.2,65000,1),List(5.3,82000,0),List(9.8,107000,0),List(1.8,64000,0),List(0.6,46000,1),List(0.8,48000,0),List(8.6,84000,1),List(0.6,45000,0),List(0.5,30000,1),List(7.3,89000,0),List(2.5,48000,1),List(5.6,76000,0),List(7.4,77000,0),List(2.7,56000,0),List(0.7,48000,0),List(1.2,42000,0),List(0.2,32000,1),List(4.7,56000,1),List(2.8,44000,1),List(7.6,78000,0),List(1.1,63000,0),List(8,79000,1),List(2.7,56000,0),List(6,52000,1),List(4.6,56000,0),List(2.5,51000,0),List(5.7,71000,0),List(2.9,65000,0),List(1.1,33000,1),List(3,62000,0),List(4,71000,0),List(2.4,61000,0),List(7.5,75000,0),List(9.7,81000,1),List(3.2,62000,0),List(7.9,88000,0),List(4.7,44000,1),List(2.5,55000,0),List(1.6,41000,0),List(6.7,64000,1),List(6.9,66000,1),List(7.9,78000,1),List(8.1,102000,0),List(5.3,48000,1),List(8.5,66000,1),List(0.2,56000,0),List(6,69000,0),List(7.5,77000,0),List(8,86000,0),List(4.4,68000,0),List(4.9,75000,0),List(1.5,60000,0),List(2.2,50000,0),List(3.4,49000,1),List(4.2,70000,0),List(7.7,98000,0),List(8.2,85000,0),List(5.4,88000,0),List(0.1,46000,0),List(1.5,37000,0),List(6.3,86000,0),List(3.7,57000,0),List(8.4,85000,0),List(2,42000,0),List(5.8,69000,1),List(2.7,64000,0),List(3.1,63000,0),List(1.9,48000,0),List(10,72000,1),List(0.2,45000,0),List(8.6,95000,0),List(1.5,64000,0),List(9.8,95000,0),List(5.3,65000,0),List(7.5,80000,0),List(9.9,91000,0),List(9.7,50000,1),List(2.8,68000,0),List(3.6,58000,0),List(3.9,74000,0),List(4.4,76000,0),List(2.5,49000,0),List(7.2,81000,0),List(5.2,60000,1),List(2.4,62000,0),List(8.9,94000,0),List(2.4,63000,0),List(6.8,69000,1),List(6.5,77000,0),List(7,86000,0),List(9.4,94000,0),List(7.8,72000,1),List(0.2,53000,0),List(10,97000,0),List(5.5,65000,0),List(7.7,71000,1),List(8.1,66000,1),List(9.8,91000,0),List(8,84000,0),List(2.7,55000,0),List(2.8,62000,0),List(9.4,79000,0),List(2.5,57000,0),List(7.4,70000,1),List(2.1,47000,0),List(5.3,62000,1),List(6.3,79000,0),List(6.8,58000,1),List(5.7,80000,0),List(2.2,61000,0),List(4.8,62000,0),List(3.7,64000,0),List(4.1,85000,0),List(2.3,51000,0),List(3.5,58000,0),List(0.9,43000,0),List(0.9,54000,0),List(4.5,74000,0),List(6.5,55000,1),List(4.1,41000,1),List(7.1,73000,0),List(1.1,66000,0),List(9.1,81000,1),List(8,69000,1),List(7.3,72000,1),List(3.3,50000,0),List(3.9,58000,0),List(2.6,49000,0),List(1.6,78000,0),List(0.7,56000,0),List(2.1,36000,1),List(7.5,90000,0),List(4.8,59000,1),List(8.9,95000,0),List(6.2,72000,0),List(6.3,63000,0),List(9.1,100000,0),List(7.3,61000,1),List(5.6,74000,0),List(0.5,66000,0),List(1.1,59000,0),List(5.1,61000,0),List(6.2,70000,0),List(6.6,56000,1),List(6.3,76000,0),List(6.5,78000,0),List(5.1,59000,0),List(9.5,74000,1),List(4.5,64000,0),List(2,54000,0),List(1,52000,0),List(4,69000,0),List(6.5,76000,0),List(3,60000,0),List(4.5,63000,0),List(7.8,70000,0),List(3.9,60000,1),List(0.8,51000,0),List(4.2,78000,0),List(1.1,54000,0),List(6.2,60000,0),List(2.9,59000,0),List(2.1,52000,0),List(8.2,87000,0),List(4.8,73000,0),List(2.2,42000,1),List(9.1,98000,0),List(6.5,84000,0),List(6.9,73000,0),List(5.1,72000,0),List(9.1,69000,1),List(9.8,79000,1))
    
    // logistic is value and not vector calculation
    def logistic(x:Double) :Double={
      return 1.0 / (1+ math.exp(-x))
    }
    // logistic_prime is using value as well
    def logistic_prime(x:Double) :Double={
       val log = logistic(x)
       return log*(1.0-log)
    }
    
    def logistic_log_likelihood_i(x_i:List[Double], y_i:Double, beta:List[Double]):Double={
        if (y_i >= 1.0) {
            return math.log(logistic(lref.dot(x_i, beta)))
        }  else {
            return math.log(1 - logistic(lref.dot(x_i, beta)))
        }
    }
    def logistic_log_likelihood(x:List[List[Double]], y:List[Double], beta:List[Double]) :Double = {
  
        var sum=0.0
        for (i<-0 until x.length) {
            sum +=logistic_log_likelihood_i(x(i), y(i), beta)
        } 
        return sum
    }          
    
    // here i is the index of the data point, j the index of the derivative 
    def logistic_log_partial_ij(x_i:List[Double], y_i:Double, beta:List[Double], j:Int):Double={   

        return (y_i - logistic(lref.dot(x_i, beta))) * x_i(j)
    }
    
    // the gradient of the log likelihood corresponding to the i-th data point
    def logistic_log_gradient_i(x_i:List[Double], y_i:Double, beta:List[Double]):List[Double] = {
         val result = MutableList[Double]()
         for (j<-0 until beta.length) {
             result+= logistic_log_partial_ij(x_i, y_i, beta, j)
         }
         return result.toList
    }
    
   
    def logistic_log_gradient(x:List[List[Double]], y:List[Double], beta:List[Double]) :Double={
         var sum=0.0
         for (i<-0 until x.length) {
             sum += (logistic_log_gradient_i(x(i), y(i), beta)).sum
         }
         return sum
    }
  
    
    def main(args:Array[String]) {  
        val original =  List(List(1, 20, 2), List(1, 30, 3), List(1, 40, 4))
        val scaled = work.scale(original) 
        val rescaled = work.rescale(original)
        println("scaled="+scaled)
        println("rescaled="+rescaled)
        println ("---------------Linear regression------------------")
         
        
        val x = data.map(x=>List(1,x(0),x(1))) // each element is [1, experience, salary
        val y = data.map(x=>x(2))             // each element is paid_account
        println("\n\nx="+x)
        println("\n\ny="+y)
       
        val rescaled_x = work.rescale(x) 
        println("\n\nrescale(x)="+rescaled_x)
        val yy = lref.convert(y)
        val beta = mlinear.estimate_beta(rescaled_x, yy)
        println ("beta="+beta)
       
        println("---------------Logistic Regression-----------------")
        println("\n\ntotaldata Size="+rescaled_x.length)
        val x_split = ml.train_test_split_x(rescaled_x,  0.33)
        val y_split = ml.train_test_split_y(yy, 0.33)
        val x_test = x_split(0)
        val x_train = x_split(1)
        val y_test = y_split(0)
        val y_train = y_split(1)
        
        println("\nx_test="+x_test)
        println("\nx_train="+x_train)
        println("\n\ny_test="+y_test)
        println("\ny_test="+y_train)
    }  
}