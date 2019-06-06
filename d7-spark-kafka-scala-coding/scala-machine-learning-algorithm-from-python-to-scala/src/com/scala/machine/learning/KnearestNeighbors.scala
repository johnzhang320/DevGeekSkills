package com.scala.machine.learning

/**
 * @author jzhang
 */
class KnearestNeighbors() {
   /**
    * Solution for favorite languages (Java, Python and R) nearest cities, predicting my votes based on my neighbors votes
    * (1) Pick one of the Winners at random
    * (2) weight the votes by distance and pick the weighted winner
    * (3) Reduce K until we find a unique winner
    */
   val lref = new  LinearAlgebra()
   val sref = new  statistics()   
   // suppose labels are ordered from nearest to farthest
   def raw_majority_vote(labels:List[Double]):Double={
        val winers = sref.mode(labels)       
        return winers(0)._1
   }
   
  //assumes that labels are ordered from nearest to farthest
   def majority_vote(labels:List[String]):String= {
     
        val winners = sref.mode_str(labels)   
        val winner = winners(0)._1
        val winner_count = winners(0)._2
        
        val num_winners = winners.length    
                           
    
        if (num_winners == 1) {
            return winner                     // unique winner, so return it
        } else {
            return majority_vote(labels.dropRight(1)) // try again without the farthest
        }
   }
   
   // find nearest labeled_point to new_point, return 0-kth labels from labeled_pointsrin
   def knn_classify(k:Int,        // k values ex (1,3,5,7) 
       labeled_points:List[(List[Double],     // longitude   ,latitude
                            String      // vote content ex programming language can be Java or Python or R   
                           )],   
       new_point:List[Double]          // longitude , latitude                        
     ) :String ={
        // each labeled point should be a pair (point, label)     
        // order the labeled points from nearest to farthest 
        // check distance from labeled_point to new_point, order from nearest to farthest 
        
        val by_distance = labeled_points.sortBy(x=>(lref.distance(x._1,new_point)))  // find eculidient distance between labeled_point to new_point
         
        val size = by_distance.size
        
         
       // println("\nnew_point="+new_point+",by_distance="+by_distance.dropRight(size-k)+"\n")
   
        // find the labels for the k closest
        val k_nearest_labels =by_distance.dropRight(size-k).map(x=>(x._2)).toList //[label for _, label in by_distance[:k]]
       // println("k="+k+", k_nearest="+k_nearest_labels)
        //# and let them vote
        return majority_vote(k_nearest_labels)
   }
}