package com.spark.movie.recommend 
import java.io.File

import scala.io.Source

import org.apache.log4j.Logger
import org.apache.log4j.Level

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd._
import org.apache.spark.mllib.recommendation.{ALS, Rating, MatrixFactorizationModel}
import org.apache.spark.sql.SQLContext
import scala.reflect.runtime.universe
import org.apache.spark.rdd.RDD
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions
import util.control.Breaks._

object MovieLensALS {


case class Movie(movieId: Int, title: String, category:String)
 

 
def main(args: Array[String]) {

    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)  
  
    // set up environment

    val conf = new SparkConf()
      .setAppName("MovieLensALS")
      .setMaster("local[*]")
      .set("spark.executor.memory", "6g")
      
    val sc = new SparkContext(conf)
   
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._
    
     val movieLensHomeDir = "/Users/jianzhang/SparkWorkSpace/MovieLensAls/data/medium"

    // load personal ratings   

    val myRatings = loadRatings(movieLensHomeDir+"/personalRatings.txt")
 

    val myRatingsRDD = sc.parallelize(myRatings, 1)
    
    val myRatingsDF = myRatingsRDD.toDF();

    // load ratings and movie titles  
    // cache them
    
  
    
    val ratingsText = sc.textFile(new File(movieLensHomeDir, "ratings.dat").toString)
    
    val moviesText = sc.textFile(movieLensHomeDir+ "/movies.dat")
    
     
    def moviesParser(str:String) : Movie ={
        val fields = str.split("::")
        Movie(fields(0).toInt,fields(1),fields(2))
    }

    val ratingsRDD=ratingsText.map { line =>
      val fields = line.split("::")
      // format: (timestamp % 10, Rating(userId, movieId, rating))
      (fields(3).toLong % 10, Rating(fields(0).toInt, fields(1).toInt, fields(2).toDouble))
    }
    
    val ratings= ratingsRDD.cache();

    val mData = moviesText.map(_.split("::")).map(p => Movie(p(0).toInt,p(1),p(2)))
    
    val moviesDF = mData.toDF().cache(); 
   
   // val moviesDF = moviesText.map(moviesParser).toDF().cache()  // movies dataframe cached
    val movies = moviesText.map { line =>
      val fields = line.split("::")
      // format: (movieId, movieName)
      (fields(0).toInt, fields(1))
    }.collect().toMap
   
    
     // get movie titles to show with recommendations
    val movieTitles=moviesDF.map(array => (array(0), array(1)+", "+array(2)+", total rating:")).collectAsMap()
 
    // your code here
    // step 1 Now, let’s make our first edit to add code to get a summary of the ratings.
    
    val start1 = System.currentTimeMillis()
    
    val numRatings = ratings.count
    val numUsers = ratings.map(_._2.user).distinct.count
    val numMovies = ratings.map(_._2.product).distinct.count
    println("Got " + numRatings + " ratings from "
      + numUsers + " users on " + numMovies + " movies.")
    val end1 = System.currentTimeMillis()
    
    println("distinct count rating, user and movie , took "+(end1-start1)+" millis seconds!")
    
    println("my own ratings:")
    
    myRatings.foreach { println }
    
   
  
    //Splitting training data

    // We will use MLlib’s ALS to train a MatrixFactorizationModel, which takes a RDD[Rating] object as input. 
    // ALS has training parameters such as rank for matrix factors and regularization constants. To determine 
    // a good combination of the training parameters, we split the data into three non-overlapping subsets, 
    // named training, test, and validation, based on the last digit of the timestamp, and cache them. We will 
    // train multiple models based on the training set, select the best model on the validation set based on 
    // RMSE (Root Mean Squared Error), and finally evaluate the best model on the test set. We also add your 
    // ratings to the training set to make recommendations for you. We hold the training, validation, and test 
    // sets in memory by calling persist because we need to visit them multiple times.
    
    val numPartitions = 20
    val training = ratings.filter(x => x._1 < 6)
                          .values
                          .union(myRatingsRDD)
                          .repartition(numPartitions)
                          .persist
                          
    val validation = ratings.filter(x => x._1 >= 6 && x._1 < 8)
                            .values
                            .repartition(numPartitions)
                            .persist
                            
    val test = ratings.filter(x => x._1 >= 8).values.persist

    val numTraining = training.count
    val numValidation = validation.count
    val numTest = test.count

    println("Training: " + numTraining + ", validation: " + numValidation + ", test: " + numTest)   
    
    println("Training data set")
    
    training.take(100).foreach { println }
                                   
    //Training using ALS

    // In this section, we will use ALS.train to train a bunch of models, and select and evaluate the best. 
    // Among the training paramters of ALS, the most important ones are rank, lambda (regularization constant),
    // and number of iterations. The train method of ALS we are going to use is defined as the following:                               
    // object ALS {

    // def train(ratings: RDD[Rating], rank: Int, iterations: Int, lambda: Double)
    // : MatrixFactorizationModel = {
    // //...
    //   }
    // }
    // ideally, we want to try a large number of combinations of them in order to find the best one. Due to time 
    // constraint, we will test only 8 combinations resulting from the cross product of 2 different ranks (8 and 12), 
    // 2 different lambdas (1.0 and 10.0), and two different numbers of iterations (10 and 20). We use the provided 
    // method computeRmse to compute the RMSE on the validation set for each model. The model with the smallest RMSE 
    // on the validation set becomes the one selected and its RMSE on the test set is used as the final metric.
    //
    //
    
    val start2 = System.currentTimeMillis()
    val ranks = List(8, 12)            // 
    val lambdas = List(0.1, 10.0)      // regularization constant
    val numIters = List(10, 20)        // number of iteration using 10 and 20 means , take two time  
    var bestModel: Option[MatrixFactorizationModel] = None
    var bestValidationRmse = Double.MaxValue
    var bestRank = 0
    var bestLambda = -1.0
    var bestNumIter = -1
    var count = 0;
    var running=true
    for (rank <- ranks; lambda <- lambdas; numIter <- numIters) {
        
            // def train(ratings: RDD[Rating], rank: Int, iterations: Int, lambda: Double)
            // : MatrixFactorizationModel = {
            // //...
            //   }
            val model = ALS.train(training, rank, numIter, lambda)
            
            val validationRmse = computeRmse(model, validation, numValidation)
            println("\nRMSE (validation) = " + validationRmse + " for the model trained with rank = "
              + rank + ", lambda = " + lambda + ", and numIter = " + numIter + ".")
            if (validationRmse < bestValidationRmse) {
              bestModel = Some(model)
              bestValidationRmse = validationRmse
              bestRank = rank
              bestLambda = lambda
              bestNumIter = numIter
          }
          count+=1
          println("ALS.train "+count+" times")
        
    }
    
    val end2 = System.currentTimeMillis()
    
    println("\nALS.Training took "+count+" to train a bestModel with minimum mean square error , total took "+(end2-start2)+" millis seconds!")
 
    
    val testRmse = computeRmse(bestModel.get, test, numTest)

    println("\nThe best model was trained with rank = " + bestRank + " and lambda = " + bestLambda
      + ", and numIter = " + bestNumIter + ", and its RMSE on the test set is " + testRmse + ".")        
    
    // Recommending movies for you

    // As the last part of our tutorial, let’s take a look at what movies our model recommends for you. This is done by generating 
    // (0, movieId) pairs for all movies you haven’t rated and calling the model’s predict method to get predictions. Recall that 0 
    // is the special user id assigned to you.  
      
    val myRatedMovieIds = myRatings.map(_.product).toSet
     
    
    
    val candidates = sc.parallelize(movies.keys.filter(!myRatedMovieIds.contains(_)).toSeq)
    
    
  
    
  //  println("candidates:")
   // candidates.take(100).foreach { x => println(x) }
    
    val recommendations = bestModel.get
                                   .predict(candidates.map((0, _)))
                                   .collect
                                   .sortBy(-_.rating)
                                   .take(50)

    var i = 1
    println("\nMovies recommended for you:")
    
    
    
    
     
    recommendations.foreach { r =>
      println("%2d".format(i) + ": " + movieTitles(r.product)+", total rating: "+r.rating)
      i += 1
    }  
      
     
    // ---------------------------------------------------------------------------------------------------                                 
    // clean up
    sc.stop()
  }

  /** Compute RMSE (Root Mean Squared Error). */
  def computeRmse(model: MatrixFactorizationModel, data: RDD[Rating], n: Long): Double = {
    val predictions: RDD[Rating] = model.predict(data.map(x => (x.user, x.product)))
    val predictionsAndRatings = predictions.map(x => ((x.user, x.product), x.rating))
      .join(data.map(x => ((x.user, x.product), x.rating)))
      .values
    math.sqrt(predictionsAndRatings.map(x => (x._1 - x._2) * (x._1 - x._2)).reduce(_ + _) / n)
  }

  /** Load ratings from file. */
  def loadRatings(path: String): Seq[Rating] = {
    val lines = Source.fromFile(path).getLines()
    val ratings = lines.map { line =>
      val fields = line.split("::")
      Rating(fields(0).toInt, fields(1).toInt, fields(2).toDouble)
    }.filter(_.rating > 0.0)
    if (ratings.isEmpty) {
      sys.error("No ratings provided.")
    } else {
      ratings.toSeq
    }
  }
}


 
 