package com.rabbitmq.tutorial;
//import com.rabbitmq.client.*;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.util.Random;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.*;
public class Worker {
	
  private static final Logger LOG = Logger.getLogger(Worker.class);
  private static final String TASK_QUEUE_NAME = "task_queue";
  private static int processID=0;
  
  public static void main(String[] argv) throws Exception {
    Random rand = new Random();
    processID = rand.nextInt(50) + 1;  
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    final Connection connection = factory.newConnection();
    final Channel channel = connection.createChannel();

    channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
    LOG.info("Worker: [*] Waiting for messages. To exit press CTRL+C");

    channel.basicQos(1);

    final Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body, "UTF-8");

        LOG.info("Worker:"+processID+" [x] Received '" + message + "'");
        try {
          doWork(message);
        } finally {
         // LOG.info(" [x] Done");
          /**
           * Manual message acknowledgments are turned on by default. In previous examples we 
           * explicitly turned them off via the autoAck=true flag. It's time to set this flag 
           * to false and send a proper acknowledgment from the worker, once we're done with a 
           * task.
           */
          channel.basicAck(envelope.getDeliveryTag(), false);   
        }
      }
    };
    channel.basicConsume(TASK_QUEUE_NAME, false, consumer);
  }

  private static void doWork(String task) {
   // for (char ch : task.toCharArray()) {
   //   if (ch == '.') {
        try {
         
         LOG.info("Worker:"+processID+" wrote task:"+task);
          Thread.sleep(4000);
        } catch (Exception _ignored) {
          Thread.currentThread().interrupt();
        }
        
   //   }
   // }
  }
}