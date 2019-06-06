package com.spark.movie.recommend 
import java.io.File

import scala.io.Source

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd._
import org.apache.spark.mllib.recommendation.{ALS, Rating, MatrixFactorizationModel}
import org.apache.spark.sql.SQLContext
 
import scala.reflect.runtime.universe
import org.apache.spark.rdd.RDD


object BuildALSModel {

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
    
     val movieLensHomeDir = "/home/jzhang/Intellipaat/spark/SparkWorkSpace/MovieLensAls/data/medium"

    // load personal ratings   

    val myRatings = loadRatings(movieLensHomeDir+"/personalRatings.txt")
 

    val myRatingsRDD = sc.parallelize(myRatings, 1)

    // load ratings and movie titles  
    // cache them
    
  
    
    val ratingsText = sc.textFile(new File(movieLensHomeDir, "ratings.dat").toString)
    
    val moviesText = sc.textFile(new File(movieLensHomeDir, "movies.dat").toString)
    
     
 

    val ratings=ratingsText.map { line =>
      val fields = line.split("::")
      // format: (timestamp % 10, Rating(userId, movieId, rating))
      (fields(3).toLong % 10, Rating(fields(0).toInt, fields(1).toInt, fields(2).toDouble))
    }

    val movies = moviesText.map { line =>
      val fields = line.split("::")
      // format: (movieId, movieName)
      (fields(0).toInt, fields(1))
    }.collect().toMap
   
    
    
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
    
    val recommendations = bestModel.get
                                   .predict(candidates.map((0, _)))
                                   .collect
                                   .sortBy(-_.rating)
                                   .take(50)

    var i = 1
    println("\nMovies recommended for you:")
    recommendations.foreach { r =>
      println("%2d".format(i) + ": " + movies(r.product)+", total rating: "+r.rating)
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


/*
 * 7/07/26 11:41:47 WARN util.Utils: Your hostname, jzhang-Inspiron-15-7579 resolves to a loopback address: 127.0.1.1; using 192.168.0.25 instead (on interface wls8)
17/07/26 11:41:47 WARN util.Utils: Set SPARK_LOCAL_IP if you need to bind to another address
17/07/26 11:41:47 INFO slf4j.Slf4jLogger: Slf4jLogger started
17/07/26 11:41:47 INFO Remoting: Starting remoting
17/07/26 11:41:47 INFO Remoting: Remoting started; listening on addresses :[akka.tcp://sparkDriver@192.168.0.25:33931]
17/07/26 11:41:47 INFO Remoting: Remoting now listens on addresses: [akka.tcp://sparkDriver@192.168.0.25:33931]
17/07/26 11:41:48 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
17/07/26 11:41:48 WARN snappy.LoadSnappy: Snappy native library not loaded
17/07/26 11:41:48 INFO mapred.FileInputFormat: Total input paths to process : 1
17/07/26 11:41:48 INFO mapred.FileInputFormat: Total input paths to process : 1
Got 1000209 ratings from 6040 users on 3706 movies.
distinct count rating, user and movie , took 1950 millis seconds!
my own ratings:
Rating(0,1,4.0)
Rating(0,780,3.0)
Rating(0,590,5.0)
Rating(0,1210,3.0)
Rating(0,648,5.0)
Rating(0,344,4.0)
Rating(0,165,5.0)
Rating(0,153,3.0)
Rating(0,597,3.0)
Rating(0,1580,4.0)
Rating(0,231,3.0)
Training: 602252, validation: 198919, test: 199049
Training data set
Rating(1,783,4.0)
Rating(2,1687,3.0)
Rating(2,1188,4.0)
Rating(2,1801,3.0)
Rating(2,1957,5.0)
Rating(2,1247,5.0)
Rating(3,2081,4.0)
Rating(5,2268,2.0)
Rating(5,3079,2.0)
Rating(5,162,4.0)
Rating(5,2952,2.0)
Rating(5,913,5.0)
Rating(5,2323,4.0)
Rating(5,714,4.0)
Rating(6,3600,3.0)
Rating(7,861,4.0)
Rating(8,3425,3.0)
Rating(8,151,4.0)
Rating(8,1639,5.0)
Rating(8,3186,4.0)
Rating(8,2324,3.0)
Rating(9,150,3.0)
Rating(9,529,5.0)
Rating(9,3408,4.0)
Rating(10,1256,3.0)
Rating(10,2067,3.0)
Rating(10,1278,5.0)
Rating(10,3037,5.0)
Rating(10,1374,4.0)
Rating(10,275,4.0)
Rating(10,2405,4.0)
Rating(10,3086,4.0)
Rating(10,3095,4.0)
Rating(10,104,3.0)
Rating(10,3189,4.0)
Rating(10,1091,3.0)
Rating(11,1923,5.0)
Rating(11,1461,1.0)
Rating(11,531,3.0)
Rating(13,589,5.0)
Rating(13,1009,2.0)
Rating(13,50,4.0)
Rating(13,2470,3.0)
Rating(15,3421,4.0)
Rating(15,1777,3.0)
Rating(15,1370,4.0)
Rating(15,2770,1.0)
Rating(15,2501,4.0)
Rating(15,2882,3.0)
Rating(15,2115,4.0)
Rating(16,2355,5.0)
Rating(17,2140,4.0)
Rating(17,1376,5.0)
Rating(17,1610,4.0)
Rating(17,3175,5.0)
Rating(17,2117,4.0)
Rating(18,1907,5.0)
Rating(18,2140,4.0)
Rating(18,2807,1.0)
Rating(18,1876,3.0)
Rating(18,3704,4.0)
Rating(18,291,1.0)
Rating(18,1702,4.0)
Rating(18,2375,4.0)
Rating(18,552,2.0)
Rating(18,2115,5.0)
Rating(19,588,4.0)
Rating(19,593,5.0)
Rating(19,2662,3.0)
Rating(19,1387,5.0)
Rating(19,47,4.0)
Rating(19,2000,4.0)
Rating(19,2888,2.0)
Rating(19,3928,2.0)
Rating(21,3053,1.0)
Rating(22,1405,3.0)
Rating(22,1921,4.0)
Rating(22,2163,3.0)
Rating(22,344,2.0)
Rating(22,2195,4.0)
Rating(22,442,2.0)
Rating(22,532,4.0)
Rating(22,3258,3.0)
Rating(22,1747,4.0)
Rating(23,2,2.0)
Rating(23,2722,3.0)
Rating(23,3479,3.0)
Rating(23,3701,3.0)
Rating(23,2912,3.0)
Rating(23,2288,3.0)
Rating(23,2511,3.0)
Rating(23,3763,2.0)
Rating(23,2105,2.0)
Rating(24,1265,4.0)
Rating(24,3039,4.0)
Rating(24,296,5.0)
Rating(24,3685,4.0)
Rating(25,1831,2.0)
Rating(25,1375,3.0)
Rating(25,110,3.0)
RMSE (validation) = 0.8697302811832069 for the model trained with rank = 8, lambda = 0.1, and numIter = 10.
ALS.train 1 times
RMSE (validation) = 0.8691969701571133 for the model trained with rank = 8, lambda = 0.1, and numIter = 20.
ALS.train 2 times
RMSE (validation) = 3.7558695311242833 for the model trained with rank = 8, lambda = 10.0, and numIter = 10.
ALS.train 3 times
RMSE (validation) = 3.7558695311242833 for the model trained with rank = 8, lambda = 10.0, and numIter = 20.
ALS.train 4 times
RMSE (validation) = 0.8666294683458914 for the model trained with rank = 12, lambda = 0.1, and numIter = 10.
ALS.train 5 times
RMSE (validation) = 0.8673545797390215 for the model trained with rank = 12, lambda = 0.1, and numIter = 20.
ALS.train 6 times
RMSE (validation) = 3.7558695311242833 for the model trained with rank = 12, lambda = 10.0, and numIter = 10.
ALS.train 7 times
RMSE (validation) = 3.7558695311242833 for the model trained with rank = 12, lambda = 10.0, and numIter = 20.
ALS.train 8 times
ALS.Training took 8 to train a bestModel with minimum mean square error , total took 54835 millis seconds!
The best model was trained with rank = 12 and lambda = 0.1, and numIter = 10, and its RMSE on the test set is 0.8650283780638319.
Movies recommended for you:
 1: Bewegte Mann, Der (1994)
 2: Chushingura (1962)
 3: For All Mankind (1989)
 4: Lamerica (1994)
 5: Window to Paris (1994)
 6: Shawshank Redemption, The (1994)
 7: Sanjuro (1962)
 8: Smashing Time (1967)
 9: Usual Suspects, The (1995)
10: Firelight (1997)
11: Sixth Sense, The (1999)
12: Ayn Rand: A Sense of Life (1997)
13: Godfather, The (1972)
14: Schindler's List (1993)
15: Big Trees, The (1952)
16: Seven Samurai (The Magnificent Seven) (Shichinin no samurai) (1954)
17: Man of the Century (1999)
18: Raiders of the Lost Ark (1981)
19: Casablanca (1942)
20: To Kill a Mockingbird (1962)
21: Great Escape, The (1963)
22: One Flew Over the Cuckoo's Nest (1975)
23: Carmen (1984)
24: Shall We Dance? (1937)
25: Rear Window (1954)
26: Boat, The (Das Boot) (1981)
27: Star Wars: Episode IV - A New Hope (1977)
28: King of Masks, The (Bian Lian) (1996)
29: Killer, The (Die xue shuang xiong) (1989)
30: Last Days, The (1998)
31: Close Shave, A (1995)
32: Sting, The (1973)
33: Godfather: Part II, The (1974)
34: October Sky (1999)
35: 42 Up (1998)
36: Matrix, The (1999)
37: Young Frankenstein (1974)
38: Life Is Beautiful (La Vita � bella) (1997)
39: Blade Runner (1982)
40: Silence of the Lambs, The (1991)
41: 12 Angry Men (1957)
42: North by Northwest (1959)
43: First Love, Last Rites (1997)
44: Central Station (Central do Brasil) (1998)
45: GoodFellas (1990)
46: Inherit the Wind (1960)
47: Man for All Seasons, A (1966)
48: Before the Rain (Pred dozhdot) (1994)
49: Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb (1963)
50: Maltese Falcon, The (1941)
17/07/26 11:42:51 INFO remote.RemoteActorRefProvider$RemotingTerminator: Shutting down remote daemon.
 * 
 */
 