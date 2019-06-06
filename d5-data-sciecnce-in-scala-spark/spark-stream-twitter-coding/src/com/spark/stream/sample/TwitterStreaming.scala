package com.spark.stream.sample


import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.Minutes

object TwitterStreaming {
  
  System.setProperty("twitter4j.oauth.consumerKey", "PjEDphhZ8Igv4IJO0nP6fkGrC")
  System.setProperty("twitter4j.oauth.consumerSecret", "sbfDg8doSQ9nyWhhhr3FJ8A0Jgnv1tKyvGh30Uy2PrqAqY7UOi")
  System.setProperty("twitter4j.oauth.accessToken", "187280067-nvFtJIKbShfcWnI8EcmS7OGTup4kHghlkYBReqIK")
  System.setProperty("twitter4j.oauth.accessTokenSecret", "3Snfc2TAgerzGZf7CMsFTAdOiJLwV7biT4z6q4Pa3qFVA")

  val filters = Array("narendramodi")

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  
  val checkpointDir="/Users/jianzhang/spark/checkpoint"

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("TwitterAnalysis").setMaster("local[2]")
    
    val ssc = new StreamingContext(conf,Seconds(5))
    
    //ssc.checkpoint(this.getClass.getClassLoader.getResource("")+"/spark/staging")

    ssc.checkpoint(checkpointDir)
    
    //val streams = TwitterUtils.createStream(ssc,None,filters)
    
    val streams = TwitterUtils.createStream(ssc,None)   // no filter
    
    val statuses = streams.map(status=>status.getText())
    
    val words = statuses.flatMap(status => status.split(" "))
    
   
   // words.foreach(x=>print(x+" "))
    
    //val hashTags = words.filter(word => word.startsWith("#"))

    val hashTags = streams.flatMap(status => status.getText.split(" ")).filter(_.startsWith("#"))

    hashTags.print()
    
    
    //window based taking the hashTags from DStreams
    //Minutes will be Windows length ,Seconds will be Sliding Interval
    //Count the hashtags over last 10 mins
    hashTags.window(Minutes(60),Seconds(10)).countByValue().print()

    //Top hashTags with in the Batch interval of  60 Seconds and counting the HashTags by ReduceByKey Operation
    val top60hashTags = hashTags.map(w => (w,1)).reduceByKeyAndWindow(_+_,Seconds(60))

    //Top hashTags with in the Batch interval of  10 Seconds and counting the HashTags by ReduceByKey Operation
    val top10hashTags = hashTags.map(w => (w,1)).reduceByKeyAndWindow(_+_,Seconds(10))


    //window based Counting values from the given Batch Interval..
    hashTags.countByValueAndWindow(Seconds(10),Seconds(5)).print()


    /*
     * topList.foreach(x=>println(x))
     *  (#USA,1)
        (#SriLanka,1)
        (#Church,1)
        (#India,1)
        (#Pakistan,1)
        (#Leftist,1)
        (#Bangladesh,1)
        (#China,1)
        (#GujaratRains,1)
        (#Modi,1)
                      (#संतरामपालजीनिर्दोषहै,1)
        (#GujaratFlood,1)
        (#Tibet,1)
        (#RSS,1)
        (#charity,1)
        (#NGO,1)
        (#usatoday,1)
        (#unitech,1)
        (#Nationalist,1)
        (#Nepal,1)
        (#Hindu,1)
        topList=() 
     */
    
    // function I did for remove first # sign if word contains it
    
    def bond(x:String) = if(x.startsWith("#")) x.substring(1) else x 
      
    top60hashTags.foreachRDD{ rdd => val topList = rdd.take(40).sortBy(x=>x._1)      
      //println("topList="+topList.foreach(x=>println(x)))
      println("\n Popular topics in last 60 Seconds (%s total) :".format(rdd.count()))
      //topList.foreach{case (count,topic) => println("%s (%s tweetes)".format(count,topic))}
      topList.foreach(x=>println(bond(x._1)+(" ("+x._2+" tweetes)")))
    }


    top10hashTags.foreachRDD{ rdd =>
      val topList = rdd.take(10).sortBy(x=>x._1)
      println("\n Popular topics in last 10 Seconds (%s total) :".format(rdd.count()))
      //topList.foreach{case (count,topic) => println("%s (%s tweetes)\n".format(count,topic))}
      topList.foreach(x=>println(bond(x._1)+(" ("+x._2+" tweetes)")))
    }

    //saving top hash Tags in batchInterval of Seconds 60
    // top60hashTags.saveAsTextFiles(checkpointDir+"/twittercheckpoint")   //"hdfs://localhost:8020/user/spark/twitter");
    
    ssc.start()
    ssc.awaitTermination()

  }
}