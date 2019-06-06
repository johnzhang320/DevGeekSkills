package com.kafka.spark.stream
/**
 * @author jzhang
 * build.sbt
 * name := "Spark Kafka Project"
    version := "1.0"
    scalaVersion := "2.10.5"
    
    libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.0"
    libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.6.0"
    libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka" % "1.6.0"
 */
/**
 *  Command running reference
 *  /usr/local/spark/bin/spark-submit --packages org.apache.spark:spark-streaming
  -kafka_2.10:1.6.0 --class "KafkaWordCount" --master local[4] target/scala-2.10/spark
  -kafka-project_2.10-1.0.jar localhost:2181 <group name> <topic name> <number of threads>
 *  
 */
import java.util.HashMap

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._

object KafkaWordCount {
   def main(args: Array[String]) {
    /*  if (args.length < 4) {
         System.err.println("Usage: KafkaWordCount <zkQuorum><group> <topics> <numThreads>")
         System.exit(1)
      }*/
      val zkQuorum = "localhost:2181"
      val group = "test"
      val topics="topic_brokers"
      val numThreads=1

     // val Array(zkQuorum, group, topics, numThreads) = args
      
      val sparkConf = new SparkConf().setAppName("KafkaWordCount").setMaster("local[*]")
      val ssc = new StreamingContext(sparkConf, Seconds(2))
      ssc.checkpoint("checkpoint")

      val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
      val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
     
      val words = lines.flatMap(_.split(" "))
      val wordCounts = words.map(x => (x, 1L))
         .reduceByKeyAndWindow(_ + _, _ - _, Minutes(10), Seconds(2), 2)
      wordCounts.print

      ssc.start()
      ssc.awaitTermination()
   }
}