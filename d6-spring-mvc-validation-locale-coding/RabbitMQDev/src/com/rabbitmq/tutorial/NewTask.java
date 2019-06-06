package com.rabbitmq.tutorial;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.apache.log4j.*;
public class NewTask {
  private static final Logger LOG = Logger.getLogger(NewTask.class);
  private static final String TASK_QUEUE_NAME = "task_queue";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    /**
     * Message durability

		We have learned how to make sure that even if the consumer dies, the task isn't lost. 
		But our tasks will still be lost if RabbitMQ server stops.
		
		When RabbitMQ quits or crashes it will forget the queues and messages unless you tell 
		it not to. Two things are required to make sure that messages aren't lost: we need to 
		mark both the queue and messages as durable.
		
		First, we need to make sure that RabbitMQ will never lose our queue. In order to do so, 
		we need to declare it as durable:
		
		boolean durable = true;
     */
    boolean durable = true;
    channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);
    
    String  message = getMessage(argv);

    for (int i=0; i<7;i++) {
    	
        message = "Hello World "+String.valueOf(i+1)+"....";
    
	    channel.basicPublish("", TASK_QUEUE_NAME,
        MessageProperties.PERSISTENT_TEXT_PLAIN,
        message.getBytes("UTF-8"));
    	LOG.info("NewTask: [x] Sent '" + message + "'");
    }
    channel.close();
    connection.close();
  }

  private static String getMessage(String[] strings) {
    if (strings.length < 1)
      return "Hello World!";
    return joinStrings(strings, " ");
  }

  private static String joinStrings(String[] strings, String delimiter) {
    int length = strings.length;
    if (length == 0) return "";
    StringBuilder words = new StringBuilder(strings[0]);
    for (int i = 1; i < length; i++) {
      words.append(delimiter).append(strings[i]);
    }
    return words.toString();
  }
}