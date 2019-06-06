package com.scala.machine.learning
import scala.collection.immutable.ListMap
/**
 * @author jzhang
 */
class statistics[A](v:List[A],w:List[A]) { 
  
   val lref = new  LinearAlgebra()
   var vv:List[Double] = lref.convert(v)
   var ww:List[Double] = lref.convert(w)
   val two = new LinearAlgebra(v,w)
   val one = new LinearAlgebra(v)
   
   def this(v:List[A]) {
      this(v,Nil)
      vv = lref.convert(v)
   }
   def this() {
     this(Nil,Nil)
   }
   
   //--------Applications-------------------------
   // range between max(v)-max(v) , only vv be initialized
   def data_range() :Double={
      var min:Double =Integer.MAX_VALUE
      var max:Double =Integer.MIN_VALUE
      for ( v<-vv ) {
        if (v<min) min=v
        if (v>max) max=v
      }
      return max-min
   }
   // find all difference between each elements vi and mean ,only vv be initialized
   def do_mean() :List[Double] = {
      val mv = one.vector_mean()
      return vv.map { x => x-mv }
   }
    // find all difference between each elements vi and mean  
   def do_mean[A](v:List[A]) :List[Double] = {
      val vv1 = lref.convert(v)     
      val mv = vv1.sum/v.length
      return vv1.map { x => x-mv }
   }
    
   // very famous variance, that is sum of square of all difference between vi and mean value, only vv be initialized
   def variance():Double={
     val len = vv.length;
     val deviations = do_mean()     
     return lref.sum_of_square(deviations) / (len-1);
   }
     // very famous variance, that is sum of square of all difference between vi and mean value,  outside calling
   def variance[A](v:List[A]):Double={
     val len = v.length;
     val vv1 = lref.convert(v)     
     val mv  = vv1.sum / vv1.length    
     val deviations= vv1.map { x => x-mv }     
     return lref.sum_of_square(deviations) / (len-1);
   }
    // very famous variance, that is sum of square of all difference between vi and mean value, only vv be initialized
   
   // very famous standard deviation
   def standard_deviation() :Double={ // only vv be initialized
      return Math.sqrt(variance())
   }
   
    // very famous standard deviation
   def standard_deviation[A](v:List[A]) :Double={  
     val len = v.length;
     val vv1 = lref.convert(v)     
     val mv  = vv1.sum / vv1.length    
     val deviations= vv1.map { x => x-mv }     
     val variance = lref.sum_of_square(deviations) / (len-1);      
     return Math.sqrt(variance)
   }
   
   
   def coveriance() : Double={  // vv and ww must be initialized
     val n = vv.length;
     return two.dot(do_mean(vv),do_mean(ww))/(n-1)
   }
   
   def coveriance[A](v:List[A],w:List[A]) : Double={  // vv and ww must be initialized
     val n = v.length;  
     val vv1 = lref.convert(v) 
     val ww1 = lref.convert(w) 
     return lref.dot(do_mean(vv1),do_mean(ww1))/(n-1)
   }
   
   // very famous correlation
   def correlation[A](v:List[A],w:List[A]) : Double={
        
     val stdev_v =standard_deviation(v)
     val stdev_w =standard_deviation(w)
     if (stdev_v>0.0 && stdev_w>0.0) {
         return coveriance(v,w) /stdev_v / stdev_w
     } else {
         return 0.0
     }
   }
    // very famous correlation
   def correlation() : Double={
        
     val stdev_v =standard_deviation(vv)
     val stdev_w =standard_deviation(ww)
     if (stdev_v>0.0 && stdev_w>0.0) {
         return coveriance(v,w) /stdev_v / stdev_w
     } else {
         return 0.0
     }
   }
    // return the pth-percentile value in v
   def quantile(p:Double) :Double={
     val vv1 = vv
     val len = vv1.length
     val p_index = (p*len).toInt
     val vs=vv1.sorted
     return vs(p_index)
      
   } 
   // return the pth-percentile value in v
   def quantile[A](v:List[A],p:Double) :Double={
     val vv1 = lref.convert(v)
     val len = vv1.length
     val p_index = (p*len).toInt
     val vs=vv1.sorted
     return vs(p_index)
      
   } 
   // find middle - most value of v
   def median[A](v:List[A]):Double={
     val vc = lref.convert(v)
     val len = vc.length
     val sorted_v = vc.sorted
     val midpoint = len / 2
     if (len % 2 == 1.0) { // if odd return the middle value
       return sorted_v(midpoint)
     } else { // if even , return the average of the middle values
       val lo = midpoint -1
       val hi = midpoint +1
       return (sorted_v(lo)+sorted_v(hi)) / 2
     }
      
   }
   // find middle - most value of v
   def median():Double={
     val vc = vv
     val len = vc.length
     val sorted_v = vc.sorted
     val midpoint = len / 2
     if (len % 2 == 1.0) { // if odd return the middle value
       return sorted_v(midpoint)
     } else { // if even , return the average of the middle values
       val lo = midpoint -1
       val hi = midpoint +1
       return (sorted_v(lo)+sorted_v(hi)) / 2
     }
      
   }
   // find word with max count 
   def mode() : List[(Double,Int)] = {
     // count elements in List      
     var ret3=vv.groupBy(x => x).mapValues(_.size)
     val maxv =ret3.values.max 
     return ret3.filter(p=>p._2==maxv).toList      
   } 
  // find word with max count 
   def mode[A](v:List[A]) : List[(Double,Int)] = {
     // count elements in List
     val vv1 = lref.convert(v)
     var ret3=vv1.groupBy(x => x).mapValues(_.size)
     val maxv =ret3.values.max 
     return ret3.filter(p=>p._2==maxv).toList      
      
   } 
   
   
  // find word with max count 
   def mode_str(v:List[String]) : List[(String,Int)] = {
     // count elements in List
   
     var ret3=v.groupBy(x => x).mapValues(_.size)
     val maxv =ret3.values.max 
     return ret3.filter(p=>p._2==maxv).toList      
      
   } 
     // find word with count  
   def Counter() : List[(Double,Int)] = {
     // count elements in List           
     var ret3=vv.groupBy(x => x).mapValues(_.size)    
     return ret3.toList
   } 
   
      // find word with count  
   def Counter(v:List[A]) : List[(Double,Int)] = {
     // count elements in List         
     val vv1 = lref.convert(v)
     var ret3=vv1.groupBy(x => x).mapValues(_.size)    
     return ret3.toList
   } 
    // find word with count and sorted they by count 
   def most_common() : List[(Double,Int)] = {
     // count elements in List      
      
     var ret3=vv.groupBy(x => x).mapValues(_.size)
     var ret4 = ListMap(ret3.toSeq.sortWith(_._2 < _._2):_*)
     
     return ret4.toList.sorted
   } 
   def most_common(v:List[A]) : List[(Double,Int)] = {
     // count elements in List      
     val vv1 = lref.convert(v)
     var ret3=vv1.groupBy(x => x).mapValues(_.size)
     var ret4 = ListMap(ret3.toSeq.sortWith(_._2 < _._2):_*)
     
     return ret4.toList.sorted
   } 
   def most_common_str(v:List[String]) : List[(String,Int)] = {
     // count elements in List      
      
     var ret3=v.groupBy(x => x).mapValues(_.size)
     var ret4 = ListMap(ret3.toSeq.sortWith(_._2 < _._2):_*)
     
     return ret4.toList.sorted
   } 
}