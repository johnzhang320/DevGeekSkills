package com.scala.spark.maven.practise
import org.apache.spark.{SparkConf, SparkContext}

object SparkWordCount1 {
   def main(args:Array[String]) : Unit={
     println("Hello World......,WordCountService coming!")
     val conf = new SparkConf().setAppName("S").setMaster("local[*]")
     val sc = new SparkContext(conf)
     val textFile = sc.textFile("John_Zhang_Resume.txt")
     // display each word frequency and sorting count decend 
     val counts = textFile
         .flatMap(line => line.split(" "))
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
         .map(word=>(word,1))
         .reduceByKey((a,b)=>a+b)
         .sortBy(x=>x._2,false)
     counts.foreach(println) 
   }
}