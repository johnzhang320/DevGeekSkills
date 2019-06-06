package com.spark.join
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD


object  HHServiceMultipleInput  {

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

  case class HRegistration(
    uhid1:String,
    locationid:String,
    registration_date:String,
    gender:String,
    dob:String,
    mobile:String,
    martialstatus:String,
    birthplace:String,
    religion:String,
    ethinicgroup:String,
    disability:String,
    education:String,
    mothertoungue:String,
    bloodgroup:String,
    address:String,
    city:String,
    state:String,
    pincode:String,
    country:String
  )

  def main(args: Array[String]) {

     val conf = new SparkConf().setAppName("HHService").setMaster("local[*]")
     val sc = new SparkContext(conf)
     val sqlContext = new org.apache.spark.sql.SQLContext(sc)
     import sqlContext.implicits._

     val hospitalDataText = sc.textFile("services.csv")
     val header = hospitalDataText.first()
     val hospitalData = hospitalDataText.filter(a => a != header)
     val hData = hospitalData.map(_.split(","))
     .map(p => HService(p(0),p(1),p(2),p(3),p(4),p(5),p(6),p(7),p(8),p(9),p(10)))
     val hosSevice = hData.toDF()
     hosSevice.registerTempTable("SERVICES")

     val hregistration = sc.textFile("registration.csv")
     val header2 = hregistration.first()
     val hrRDD = hregistration.filter(a => a!= header2)
     val hrData = hrRDD.map(_.split(",")).map(p => HRegistration(p(0),p(1),p(2),p(3),p(4),p(5),p(6),p(7),p(8),p(9),p(10),p(11),p(12),p(13),p(14),p(15),p(16),p(17),p(18)))
     val registrations = hrData.toDF()
     registrations.registerTempTable("REGISTRATIONS")

     val start = System.currentTimeMillis()
     val results = sqlContext.sql("select SERVICES.doctorid, count(SERVICES.uhid) as maleCount from SERVICES  ,  REGISTRATIONS    where  SERVICES.uhid = REGISTRATIONS.uhid1 and REGISTRATIONS.gender='\"Male\"' group by SERVICES.doctorid order by maleCount desc")
     results.collect().foreach(println)
     
     println("100 MB table service , join took "+(System.currentTimeMillis()-start) +" millis seconds!")

   }
}
