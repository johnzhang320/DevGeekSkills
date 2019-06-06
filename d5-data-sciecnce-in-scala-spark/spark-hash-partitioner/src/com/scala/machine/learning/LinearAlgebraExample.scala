package com.scala.machine.learning

/**
 * @author jzhang
 */
object LinearAlgebraExample {
   def main(args:Array[String]) {
      // val v = List(5.0, 13.0, 6.0, 3.0, 1.0,4.0,5.0,7.0)
      // val w = List(5.3, 1.1, 4.2, 3.5, 2.0,9.0,10.0,21.5)
       val v= List(3, 2, 4)
       val w= List(1, 2, 5)
       println("v="+v+",w="+w)
       println("vector substract ="+ new LinearAlgebra(v,w).vector_subtract())
       println("vector add ="+ new LinearAlgebra(v,w).vector_add())
       println("vector sum ="+ new LinearAlgebra(v,w).vector_sum())
       println("vector multiply ="+ new LinearAlgebra(v).vector_multiply(2.0))
       println("vector mean ="+ new LinearAlgebra(v).vector_mean())
       println("vector dot product ="+ new LinearAlgebra(v,w).dot())
       println("vector sum_of_square ="+ new LinearAlgebra(w).sum_of_square())
       println("magnitude ="+ new LinearAlgebra(v).magnitude())
       println("vector euclidian distance ="+ new LinearAlgebra(v,w).distance())
       println("vector cosine_similarity() ="+ new LinearAlgebra(v,w).cosine_similarity())

   }
}