package com.scala.spark.maven.practise
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
/*
 * Using Broadcast Variable- Broadcast variable enhances the efficiency of joins between small and large RDDs.
 * hese are read only variables, present in-memory cache on every machine. When working with Spark, usage of 
 * broadcast variables eliminates the necessity to ship copies of a variable for every task, so data can be 
 * processed faster. Broadcast variables help in storing a lookup table inside the memory which enhances the 
 * retrieval efficiency when compared to an RDD lookup (
 */

object  SparkAccumulatorBroadcastJoin  {

  def main(args: Array[String]) {

     val conf = new SparkConf().setAppName("SparkAccumulatorBroadcastJoin").setMaster("local[*]")
     val sc = new SparkContext(conf)

     val payRDD = sc.textFile("userInfo.csv")
     val payPair = payRDD.map(x => (x.split(",")(0),x))

     val usrRDD = sc.textFile("payments.csv")
     val usrPair = usrRDD.map(x =>(x.split(",")(0),x.split(",")(2)))

     val usrMap = usrPair.collectAsMap()
     val r = payPair.map(v => (v._1,(usrMap(v._1),v._2)))

     r.foreach(println)
     //r.saveAsTextFile("results_join")

   }
}
