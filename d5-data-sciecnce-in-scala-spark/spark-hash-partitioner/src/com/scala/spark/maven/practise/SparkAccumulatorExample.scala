package com.scala.spark.maven.practise
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

/*
 * Using Accumulators – Accumulators help update the values of variables in parallel while executing
 */
object  SparkAccumulatorExample  {

  def main(args: Array[String]) {

     val conf = new SparkConf().setAppName("SparkAccumulatorBroadcastJoin").setMaster("local[*]")
     val sc = new SparkContext(conf)
     val purchaseInDelhi = sc.accumulator(0)

     val payRDD = sc.textFile("userInfo.csv")
     val payPair = payRDD.map(x => (x.split(",")(0),x))

     val usrRDD = sc.textFile("payments.csv")
     val usrPair = usrRDD.map(
       x => {
         if(x.split(",")(1)=="Delhi") {
           purchaseInDelhi += 1
         }
       (x.split(",")(0),x.split(",")(2))
       }
     )

     val usrMap = usrPair.collectAsMap()
     val r = payPair.map(v => (v._1,(usrMap(v._1),v._2)))

     r.foreach(println)
   //  r.saveAsTextFile("results_example")
     println("No of Delhi purchase " + purchaseInDelhi.value)
   }
}
