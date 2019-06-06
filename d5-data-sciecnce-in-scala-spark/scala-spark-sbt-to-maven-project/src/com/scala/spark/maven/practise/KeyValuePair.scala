package com.scala.spark.maven.practise
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import java.io.File
object KeyValuePair {
   def main(args: Array[String]) {

     val conf = new SparkConf().setAppName("HHService").setMaster("local[*]")
     val sc = new SparkContext(conf)
     val sqlContext = new org.apache.spark.sql.SQLContext(sc)
     import sqlContext.implicits._

     val hservice = sc.textFile("services.csv")
     val header = hservice.first()
     val hsRDD = hservice.filter(a => a != header)
     val hss = hsRDD.map(x => (x.split(",")(0), x))

     val hregistration = sc.textFile("registration.csv")
     val header2 = hregistration.first()
     val hrRDD = hregistration.filter(a => a!= header2)
     val hrSS = hrRDD.map(x => (x.split(",")(0),x.split(",")(3)))

     val r = hss.join(hrSS)
     r.take(500).foreach(println)
     
   
   //  r.saveAsTextFile("results")

   }
}