package com.kafka.topic;
//import util.properties packages
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;


//import simple producer packages
import org.apache.kafka.clients.producer.Producer;

//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;

//Create java class named “SimpleProducer”
public class SimpleProducer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	  // Check arguments length value
	   /*   if(args.length == 0){
	         System.out.println("Enter topic name");
	         return;
	      }
	      */
	      //Assign topicName to string variable
	      String topicName =Constants.TOPIC_NAME;   // args[0].toString();
	      
	      // create instance for properties to access producer configs   
	      Properties props = new Properties();
	      
	      //Assign localhost id
	      props.put("bootstrap.servers", "localhost:9092");
	      
	      //Set acknowledgements for producer requests.      
	      props.put("acks", "all");
	      
	      //If the request fails, the producer can automatically retry,
	      props.put("retries", 0);
	      
	      //Specify buffer size in config
	      props.put("batch.size", 16384);
	      
	      //Reduce the no of requests less than 0   
	      props.put("linger.ms", 1);
	      
	      //The buffer.memory controls the total amount of memory available to the producer for buffering.   
	      props.put("buffer.memory", 33554432);
	      
	      props.put("key.serializer", 
	         "org.apache.kafka.common.serialization.StringSerializer");
	         
	      props.put("value.serializer", 
	         "org.apache.kafka.common.serialization.StringSerializer");
	      
	      Producer<String, String> producer = new KafkaProducer
	         <String, String>(props);
	            
	      for(int i = 0; i < 30; i++) {
	    	    // ProducerRecord can specify partition number , then all messages goes to partition
	    	    // if not specify , available partition 
	         producer.send(new ProducerRecord<String, String>(topicName, 
	            Integer.toString(i), Integer.toString(i)));
	      // Check arguments length value
	         try {
	    		  Thread.sleep(100);
	    	  } catch (InterruptedException e) {}
	        // System.out.println(i);
	      }
	      
	      
	      String str[] ={"This is Kafka API Test", "How are you?", "do you like Kafka?","movieId,title,genres,1,Toy Story (1995),Adventure|Animation|Children|Comedy|Fantasy"
 

	      		 };
	      for (String s:str) {
	    	  producer.send(new ProducerRecord<String, String>(topicName, s, s));
	    	  try {
	    		  Thread.sleep(1000);
	    	  } catch (InterruptedException e) {}
	    	 // System.out.println(s);
	      }
	      FileInputStream fis = null;
	      DataInputStream dis = null;
	      BufferedReader br = null;
	      String line=null;
	      StringBuffer buf = new StringBuffer();
	      String filename ="movies.csv";
	      try {
	    	  int count=0;
	          fis = new FileInputStream(filename);
	          dis = new DataInputStream(fis);
	          br = new BufferedReader(new InputStreamReader(dis));
	          while((line = br.readLine()) != null){
	        	    if (count<1000) {
	        	    	buf.append(line);
	        	    }
	        	    count++;
	          		//System.out.println(line);
	          	}  
	           
	      } catch (FileNotFoundException e) {
	          e.printStackTrace();
	      } catch (IOException e) {
	          e.printStackTrace();
	      } finally{
	          try{if(br != null) br.close();}catch(Exception ex){}
	      }
	      System.out.println("current len="+buf.length());
	      String sub = buf.toString().substring(1,1800);
	      try {
    		  Thread.sleep(2000);
    	  } catch (InterruptedException e) {}
	      producer.send(new ProducerRecord<String, String>(topicName, "movies", sub));
	      try {
    		  Thread.sleep(2000);
    	  } catch (InterruptedException e) {}
	      System.out.println("set current len="+buf.length());
	   }

}
