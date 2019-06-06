package com.spark.stream.sample
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.log4j.{Level, Logger}
import org.apache.spark.Logging
/**
 * nc -lk 9999 
 * in linux
 */
/**
 * @author jzhang
 */
object SparkStreamBasic  { 
  def main(args: Array[String]) {
      if (args.length < 2) {
        System.err.println("Usage: NetworkWordCount <hostname> <port>")
        System.exit(1)
      }
      InternalLogging.setStreamingLogLevels()

      // Create the context with a 1 second batch size
      val sparkConf = new SparkConf().setAppName("NetworkWordCount").setMaster("local[*]")
      val ssc = new StreamingContext(sparkConf, Seconds(4))

      val lines = ssc.socketTextStream(args(0), args(1).toInt, StorageLevel.MEMORY_AND_DISK_SER)
      val words = lines.flatMap(_.split(" "))
      val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
      wordCounts.print()
      ssc.start()
      ssc.awaitTermination()
    }
}

object InternalLogging extends Logging {
  def setStreamingLogLevels() {
    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if (!log4jInitialized) {
      logInfo("Setting log level to [WARN] .")
      Logger.getRootLogger.setLevel(Level.WARN)
    }
  }
  }