package com.scala.spark.maven.practise
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD

object SparkSQLSample1 {
   case class HService(
    uhid:String,
    locationid:String,
    doctorid:String,
    billdate: String,
    servicename:String,
    servicequantity:String,
    starttime: String,
    endtime: String,
    servicetype: String,
    servicecategory:String,
    deptname:String
  )

  def main(args: Array[String]) {

     val conf = new SparkConf().setAppName("HHService").setMaster("local[*]")
     val sc = new SparkContext(conf)
     val sqlContext = new org.apache.spark.sql.SQLContext(sc)
     import sqlContext.implicits._

     // Convert the Text File into RDD of Strings
     val hospitalDataText = sc.textFile("services.csv")

     // Remove first line as its header
     val header = hospitalDataText.first()  // fetch first line of RDD -- csv head line
     val hospitalData = hospitalDataText.filter(a => a != header)

     // Convert RDD of String (string is command seperated values) to RDD of Array of String
     // why we use map instead of flatMap because map is line base map 
     val hData = hospitalData.map(_.split(","))
     .map(p => HService(p(0),p(1),p(2),p(3),p(4),p(5),p(6),p(7),p(8),p(9),p(10)))
     // convert RDD t dataframe
     val hosSevice = hData.toDF()
     // 
     hosSevice.registerTempTable("HService")
     
     val results =sqlContext.sql("SELECT doctorid,  count(uhid) as visits FROM HService GROUP BY doctorid order by visits desc")
     results.collect().foreach(println)
   }
}