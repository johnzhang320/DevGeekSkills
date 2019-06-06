package com.core.java.utils;

public class Interview_SamSong {
/**
 *   We have classes, 
 *   
 *   one class has members List<String> , Map<String,Integer>
 *   
 *   another class has Rest API , response JSON created by first class
 *   
 *   (1) suppose we have URI already and we define endpoint in Restful class
 *   (2) path for class is '/'
 *   (3) IN Mavin project we already included the Jackson Library, which means
 *       we can use MediaType.APPLICATION_JSON in either Consumer and procduces
 *   (4) Suppose Jersey Client apply JSON and POJO , then we use first class as
 *       response class, we only 
 *   (5) in List<String> myList return Array JSON which has a name "myList": [{....},{.....}....]}
 *   (6) in Map<String,Integer> myMap "myMap" :{ "string1": num1, "String1":num2.....}
 *   (7) API for Request
 *       GET: /myhost/firstclass/
 *       Accept: Application/Json
 *          
 *   Jersey Server
 *   @path ("/")
 *   class RestService {
 *     @path {"/firstClass/") 
 *     @Produces(MediaType.Application_JSON)
 *     public firstClass getFirstClass(secondClass second) {
 *     		  firstClass firstClass = new firstClass(....);
 *      	  .....
 *            initialize firstClass list and map
 *            
 *            return firstClass;	
 *     
 *     }
 * 
 *   Jersey client
 *   ClientConfig clientConfig = ssl.getDefaultClientConfig(sslContext);
				 
				 clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
				 
				 client = Client.create(clientConfig);
				 
			 WebResource webResource = client.resource(URL);
			 
			 SecondClass requestPoJo = new SecondClass();
			 
			 ClientResponse response = webResource.accept("application/json").type("application/json")
			                                      .post(ClientResponse.class,requestPoJo);
		
 *   
 *    interface employee {
 *    	  public int getPayment();
 *        public int getTaxRate();
 *        
 *    }
 *    
 *    class yearlyWorker implements employee {
 *    		private String fullname;
 *    		private int Age;
 *          private int Salary;
 *          public getPayment(int months) {
 *             return monthsPayment * 12(1-getTexRate());
 *          }
 *          public getTaxRate() {
 *            return 0.15;
 *          }
 *          
 *     class hourlyWorker implements employee {
 *    		private String fullname;
 *    		private int Age;
 *          private int Salary;
 *          public getPayment(int days) {
 *             return monthsPayment * 8 * days *12 (1-getTexRate());
 *          }
 *          public getTaxRate() {
 *            return 0.23;
 *          }      
 *    }
 *    
 *    public employee empFactory (int type) {
 *    		employee retVal=null;
 *          if (type==YEARLY_WORKER) {
 *          	retVal = new yearlyWorker();
 *          } else if (type==HOURLY_WORKER) {
 *          	retVal = new hourlyWorker();
 *          }
 *          return retVal;
 *    } 
 */
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
