package com.scala.spark.maven.practise
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object  SparkBroadcasterExample  {
  def main(args: Array[String]) {

     val conf = new SparkConf().setAppName("SparkAccumulatorBroadcastJoin").setMaster("local[*]")
     val sc = new SparkContext(conf)
     val purchaseInDelhi = sc.accumulator(0)

     val payRDD = sc.textFile("userInfo.csv")
     val payPair = payRDD.map(x => (x.split(",")(0),x))  // key is first item and rest is whole line
     val usrRDD = sc.textFile("payments.csv")
     
     // Add accumulation +1 for purchase in Delhi
     val usrPair = usrRDD.map(
       x => {
         if(x.split(",")(1)=="Delhi") {
           purchaseInDelhi += 1
         }
       (x.split(",")(0),x.split(",")(2))
       }
     )
     // usrPair.collectMap is scala map 
     val usrMap = usrPair.collectAsMap()
     
     // broadcast this map to all nodes so that each node has one local copy
     val broadcastUserMap = sc.broadcast(usrMap)
     // passing user broadcastMap as parameter to payPair map to implement join with this two maps
     val r = payPair.map(v => (v._1,(broadcastUserMap.value(v._1),v._2)))
     r.foreach(println)
    // r.saveAsTextFile("results2")
     println("No of Delhi purchase " + purchaseInDelhi.value)
   }
}
