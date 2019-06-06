package com.spark.partition
 
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}
import org.apache.spark.Partitioner

class CustomPartitioner(numParts: Int) extends Partitioner {
    override def numPartitions:Int = numParts;
    
    override def getPartition(key:Any):Int = {
       if (key.toString().equals("system")) {   // only key word is "system" be put to part-00000
           0
       } else {
           1
       }
    }
 
}
