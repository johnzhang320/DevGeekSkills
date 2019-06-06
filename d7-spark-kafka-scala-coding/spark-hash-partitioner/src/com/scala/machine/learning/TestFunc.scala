package com.scala.machine.learning

/**
 * @author jzhang
 */
object TestFunc {
   def main(args:Array[String]) {
      val x1 = 1.0
      val x2 = 2.0
      val tolerance = 0.00005

      // define the f(x) function here
      val fx = (x: Double) => x*x*x + x*x - 3*x -3
    
      // pass the f(x) function as a parameter to the new halveTheInterval function
      val answer = halveTheInterval(fx, x1, x2, tolerance)

      // print the answer
      println(answer)
      
     print("sumSquares(2, 3)="+ sumSquares(2, 3))
      
   }
    def sum(f: Int => Int, a: Int, b: Int): Int = if (a > b) 0 else f(a+b) 

    // these three functions use the sum() function
    def sumInts(a: Int, b: Int): Int = sum(id, a, b)
    def sumSquares(a: Int, b: Int): Int = sum(square, a, b)
    def sumPowersOfTwo(a: Int, b: Int): Int = sum(powerOfTwo, a, b)
    
    // three functions that are passed into the sum() function
    def id(x: Int): Int = x
    def square(x: Int): Int = x * x
    def powerOfTwo(x: Int): Int = if (x == 0) 1 else 2 * powerOfTwo(x - 1)

// this simply prints the number 10
//println("sum ints 1 to 4 = " + sumInts(1,4))
   /**
   * the first argument to this function is a function literal.
   */
  def halveTheInterval(fx: Double => Double, x1:Double, x2:Double, tolerance:Double): Double = {
      var x1wkg = x1
      var x2wkg = x2
      while (Math.abs(x1wkg-x2wkg) > tolerance) {
          var x3 = (x1wkg + x2wkg)/2.0
          if (signsAreOpposite(fx(x3), fx(x1wkg))) x2wkg = x3 else x1wkg = x3
      }
      return x1wkg
  }
  def signsAreOpposite(x: Double, y: Double):Boolean = {
      if (x < 0 && y > 0) return true
      else if (x > 0 && y < 0) return true
      else return false
  }
}