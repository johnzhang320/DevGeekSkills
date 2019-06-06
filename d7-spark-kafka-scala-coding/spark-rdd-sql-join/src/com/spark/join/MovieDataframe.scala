package com.spark.join
import org.apache.spark.{SparkConf, SparkContext}
/**
 * @author jzhang
 */
class MovieDataframe {
  
  
  
  case class ccMovies (
    movieId:Int,
    title:String,
    genres:String
  )
  case class ccRatings(
     userId:Int,
     movieId:Int,
     rating:Double,
     timestamp:Int
  )
   
  def getSC(appName:String) : SparkContext ={
      val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName(appName)
      .set("spark.executor.memory", "2g")      
    
       return new SparkContext(conf)
  }
   
}