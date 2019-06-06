package com.scala.machine.learning

/**
 * @author jzhang

 */
import scala.collection.mutable.MutableList
class WorkWithData {
    
    val lref = new LinearAlgebra()
    val sref = new statistics()
    // scale: calculate two dimensions vector , first vector is mean , second vector is standard derivative  
    // ex input  val data_matrix =List(List(1, 20, 2), List(1, 30, 3), List(1, 40, 4))
    // output  List((1.0, 30.0, 3.0), (0.0, 10.0, 1.0))
 
    def scale[A](data_matrix :List[List[A]]) :List[List[Double]]= {  
       
       val num_rows = data_matrix.length
       val num_cols = data_matrix(0).length
       val matrix = lref.convertX(data_matrix)
      
       val means=MutableList[Double]()
       val stdevs=MutableList[Double]()      
       
       for (j <-0 until num_cols) {       
           val l1=MutableList[Double]()
           for (i<-0 until num_rows) {            
               l1 += matrix(i)(j)      //vector each row per one column           
           }
          
           means+=lref.vector_mean(l1.toList)
           stdevs+=sref.standard_deviation(l1.toList)     
       }
       return List(means.toList,stdevs.toList) 
       
    }
    
    // rescale: the input data so that each column has mean 0 and standard deviation 1 ignores 
    //          columns with no deviation

    // input scale 2 X n matrix and data_matric
    // output rescaled:  [[1, -1.0, -1.0], [1, 0.0, 0.0], [1, 1.0, 1.0]]

    
    def rescale[A](matrix_old:List[List[A]]):List[List[Double]]={
         
         val data_matrix = lref.convertX(matrix_old)
         val scale_data = scale(data_matrix)
         val means = scale_data(0)
         val stdevs = scale_data(1)             
         val num_rows = data_matrix.length
         val num_cols = data_matrix(0).length
         val marray = Array.ofDim[Double](num_rows,num_cols)
         for (i <-0 until num_rows) {        
             for (j<-0 until num_cols) {            
                 if (stdevs(j)>0) {
                    marray(i)(j) = (data_matrix(i)(j) - means(j)) / stdevs(j)
                 } else {
                    marray(i)(j) = data_matrix(i)(j)   
                 }           
             }
         }
         return lref.convertM(marray)
    }
     
    
}