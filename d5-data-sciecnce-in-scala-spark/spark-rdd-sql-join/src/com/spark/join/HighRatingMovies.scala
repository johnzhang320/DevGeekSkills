package com.spark.join
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext

/**
 * @author jzhang
 */
object HighRatingMovies {
 
  case class ccMovies (
    movieId:String,
    title:String,
    genres:String
  )
  case class ccRatings(
     userId:String,
     movieId:String,
     rating:String,
     timestamp:String
  )
   def main(args:Array[String]) {
     val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("MovieJoinService")
      .set("spark.executor.memory", "3g")      
    
     val sc = new SparkContext(conf)
     val sqlContext =new SQLContext(sc)    
   
     import sqlContext.implicits._
     
     val path = ""
      
     val movieText = sc.textFile(path+"movies.csv")
     val header = movieText.first();
     val movieData = movieText.filter(a=>a != header)
     val mData = movieData.map(_.split(",")).map(p => ccMovies(p(0),p(1),p(2)))
     
     val movieDF = mData.toDF();
     movieDF.registerTempTable("MovieTable")
                 
     movieDF.take(10).foreach { println }
     
     val ratingText = sc.textFile(path+"ratings.csv")
     
     val header2 = ratingText.first()
     
     val ratingData = ratingText.filter(a=>a!=header)
     
     val rData = ratingData.map(line=>line.split(",")).map(p => ccRatings(p(0),p(1), p(2),p(3)))
     
     val ratingDF = rData.toDF()
     ratingDF.registerTempTable("RatingTable")
     
     ratingDF.take(100).foreach { println }
     val start = System.currentTimeMillis()
     // List movies which large number of users ranked and rating>3.5, desc sort by number of users
     val results = sqlContext.sql("select a.title, AVG(b.rating) as average,count(b.userId) userNumber from MovieTable a,  RatingTable b    where  a.movieId = b.movieId group by a.title having average>3.5 order by userNumber desc")
 
     results.take(500).foreach { println }
     
     println("500 MB table rating, join took "+(System.currentTimeMillis()-start) +" millis seconds!")
  }
}