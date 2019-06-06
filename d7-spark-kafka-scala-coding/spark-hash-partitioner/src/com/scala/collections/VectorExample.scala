package com.scala.collections

/**
 * @author jzhang
 */
object VectorExample {
   def main(args:Array[String]) {
       val v = List(5, 13, 6, 3, 1,"hello")
       val w = List(5.3, 1.1, 4.2, 3.5, 2)
       println("v="+v+",w="+w)
       println("vector substract ="+ new VectClass(v,w).vector_subtract())
       println("vector add ="+ new VectClass(v,w).vector_add())
       println("vector sum ="+ new VectClass(v,w).vector_sum())
       println("vector multiply ="+ new VectClass(v).vector_multiply(2.0))
       println("vector mean ="+ new VectClass(v).vector_mean())
       println("vector dot product ="+ new VectClass(v,w).dot())
       println("vector sum_of_square ="+ new VectClass(w).sum_of_square())
       println("magnitude ="+ new VectClass(v).magnitude())
       println("vector euclidian distance ="+ new VectClass(v,w).distance())
       println("vector cosine_similarity() ="+ new VectClass(v,w).cosine_similarity())

   }
}