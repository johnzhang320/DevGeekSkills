package com.spark.partition

import breeze.numerics.{sqrt, pow}
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}
import java.io.File;
object HashPartition {
  
  def main(args: Array[String]) {

    val sc = new SparkContext(new SparkConf()
      .setAppName("WordCount")
      .setMaster("local"))
   // before run , rm -rf /Users/jianzhang/result

    val counts = sc.textFile("John_Zhang_Resume.txt")
      .flatMap(x=>x.split(" "))
      .filter(x=>(x!="and"))
         .filter(x=>(x!=","))
         .filter(x=>(x!="*"))
         .filter(x=>(x!="for"))
         .filter(x=>(x!="to"))
         .filter(x=>(x!="the"))
         .filter(x=>(x!="from"))
         .filter(x=>(x!="of"))
         .filter(x=>(x!="in"))
         .filter(x=>(x!="on"))
         .filter(x=>(x!="with"))
         .filter(x=>(x!="by"))
         .map(word=>(word.replace(",", "").replace(" ","")
             .replace("	","").replace("					","").replace("						",""),1))
         .cache()
    // cal item -> (user,rating) and item -> sqrt(ratings)
    val partitionCounts = counts.partitionBy(new HashPartitioner(10))
     
    val wordCounts = partitionCounts.reduceByKey(_+_).sortBy(x=>x, true)
    val savefile= wordCounts.saveAsTextFile("/Users/jianzhang/result")
    
    wordCounts.foreach(println)
    
  
  }
}