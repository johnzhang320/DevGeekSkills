package com.scala.machine.learning
import scala.collection.mutable.MutableList
/**
 * @author jzhang 
 */
class Mach‌ineLearning {
    def train_test_split_x(x:List[List[Double]],test_pct:Double): List[List[List[Double]]] = {
        val int_pct = test_pct * 100
        var testSize1 = (x.length * int_pct)/100;
        var testSize = testSize1
        var trainSize = x.length - testSize
        println("\nx.length="+x.length+",testSize="+testSize+", trainSize="+trainSize);
        val x_train = MutableList[List[Double]]();
        val x_test =  MutableList[List[Double]]();
       
       
        
        for ( i <- 0 until x.length) {
            if (i<testSize) {
                x_test+= x(i)
                
            } else {        
                x_train+= x(i)
                
            }
        }
        val result = List(x_train.toList,x_test.toList)
        return result
    }
    
     def train_test_split_y(y:List[Double], test_pct:Double): List[List[Double]] = {
        val int_pct = test_pct * 100
        var testSize1 = (y.length * int_pct)/100;
        var testSize = testSize1
        var trainSize = y.length - testSize
        val y_train = MutableList[Double]();
        val y_test =  MutableList[Double]();
         
        
        for ( i <- 0 until y.length) {
            if (i<testSize) {
             
                y_test+= y(i)
            } else {        
               
                y_train+= y(i)
            }
        }
        val result = List(y_train.toList,y_test.toList)
        return result
    }
}