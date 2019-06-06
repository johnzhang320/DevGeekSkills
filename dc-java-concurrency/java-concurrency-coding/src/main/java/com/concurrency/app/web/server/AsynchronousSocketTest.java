package com.concurrency.app.web.server;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
/**
 * 
 * 

	An asynchronous channel for stream-oriented listening sockets. 
	
	An asynchronous server-socket channel is created by invoking the open method of this class. 
	A newly-created asynchronous server-socket channel is open but not yet bound. It can be bound 
	to a local address and configured to listen for connections by invoking the bind method. 
	Once bound, the accept method is used to initiate the accepting of connections to the 
	channel's socket. An attempt to invoke the accept method on an unbound channel will cause a 
	NotYetBoundException to be thrown. 
	
	Channels of this type are safe for use by multiple concurrent threads though at most one accept 
	operation can be outstanding at any time. If a thread initiates an accept operation before a 
	previous accept operation has completed then an AcceptPendingException will be thrown. 
	
	Socket options are configured using the setOption method. Channels of this type support the 
	following options: 
	
	
	
	Option Name
	
	Description
	
	SO_RCVBUF  The size of the socket receive buffer  
	SO_REUSEADDR  Re-use address  
	Additional (implementation specific) options may also be supported. 
	Usage Example: 
	  final AsynchronousServerSocketChannel listener =
	      AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(5000));
	
	  listener.accept(null, new CompletionHandler<AsynchronousSocketChannel,Void>() {
	      public void completed(AsynchronousSocketChannel ch, Void att) {
	          // accept the next connection
	          listener.accept(null, this);
	
	          // handle this connection
	          handle(ch);
	      }
	      public void failed(Throwable exc, Void att) {
	          ...
	      }
	  });


 *
 */
/**
 * 
 * 

	An asynchronous channel for stream-oriented connecting sockets. 
	
	Asynchronous socket channels are created in one of two ways. A newly-created AsynchronousSocketChannel 
	is created by invoking one of the open methods defined by this class. A newly-created channel is open 
	but not yet connected. A connected AsynchronousSocketChannel is created when a connection is made to 
	the socket of an AsynchronousServerSocketChannel. It is not possible to create an asynchronous socket 
	channel for an arbitrary, pre-existing socket. 
	
	A newly-created channel is connected by invoking its connect method; once connected, a channel remains 
	connected until it is closed. Whether or not a socket channel is connected may be determined by invoking 
	its getRemoteAddress method. An attempt to invoke an I/O operation upon an unconnected channel will 
	cause a NotYetConnectedException to be thrown. 
	
	Channels of this type are safe for use by multiple concurrent threads. They support concurrent reading 
	and writing, though at most one read operation and one write operation can be outstanding at any time. 
	If a thread initiates a read operation before a previous read operation has completed then a 
	ReadPendingException will be thrown. Similarly, an attempt to initiate a write operation before a 
	previous write has completed will throw a WritePendingException. 
	
	Socket options are configured using the setOption method. Asynchronous socket channels support the 
	following options: 
	
	
	
	Option Name
	
	Description
	
	SO_SNDBUF  The size of the socket send buffer  
	SO_RCVBUF  The size of the socket receive buffer  
	SO_KEEPALIVE  Keep connection alive  
	SO_REUSEADDR  Re-use address  
	TCP_NODELAY  Disable the Nagle algorithm  
	Additional (implementation specific) options may also be supported. 
	Timeouts
	
	The read and write methods defined by this class allow a timeout to be specified when 
	initiating a read or write operation. If the timeout elapses before an operation completes 
	then the operation completes with the exception InterruptedByTimeoutException. A timeout may 
	leave the channel, or the underlying connection, in an inconsistent state. Where the 
	implementation cannot guarantee that bytes have not been read from the channel then it puts 
	the channel into an implementation specific error state. A subsequent attempt to initiate a 
	read operation causes an unspecified runtime exception to be thrown. Similarly if a write 
	operation times out and the implementation cannot guarantee bytes have not been written to the 
	channel then further attempts to write to the channel cause an unspecified runtime exception to 
	be thrown. When a timeout elapses then the state of the ByteBuffer, or the sequence of buffers, 
	for the I/O operation is not defined. Buffers should be discarded or at least care must be taken 
	to ensure that the buffers are not accessed while the channel remains open. All methods that accept 
	timeout parameters treat values less than or equal to zero to mean that the I/O operation does not 
	timeout.

 *
 */
public class AsynchronousSocketTest {
	class Attachment {
		  AsynchronousServerSocketChannel server;
		  AsynchronousSocketChannel client;
		  ByteBuffer buffer;
		  SocketAddress clientAddr;
		  boolean isRead;
		}
		/* 
		 * A handler for consuming the result of an asynchronous I/O operation. 
 
			The asynchronous channels defined in this package allow a completion handler to 
			be specified to consume the result of an asynchronous operation. The completed 
			method is invoked when the I/O operation completes successfully. The failed 
			method is invoked if the I/O operations fails. The implementations of these 
			methods should complete in a timely manner so as to avoid keeping the invoking 
			thread from dispatching to other completion handlers.
			Type Parameters:<V> The result type of the I/O operation<A> The type of the 
			object attached to the I/O operationSince:1.7
		 *
		 */
		class ConnectionHandler implements
		    CompletionHandler<AsynchronousSocketChannel, Attachment> {
		  @Override
		  public void completed(AsynchronousSocketChannel client, Attachment attach) {
		    try {
		      SocketAddress clientAddr = client.getRemoteAddress();
		      System.out.format("Accepted a  connection from  %s%n", clientAddr);
		      attach.server.accept(attach, this);
		      ReadWriteHandler rwHandler = new ReadWriteHandler();
		      Attachment newAttach = new Attachment();
		      newAttach.server = attach.server;
		      newAttach.client = client;
		      newAttach.buffer = ByteBuffer.allocate(2048);
		      newAttach.isRead = true;
		      newAttach.clientAddr = clientAddr;
		      client.read(newAttach.buffer, newAttach, rwHandler);
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  }

		  @Override
		  public void failed(Throwable e, Attachment attach) {
		    System.out.println("Failed to accept a  connection.");
		    e.printStackTrace();
		  }
		}

		class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {
		  @Override
		  public void completed(Integer result, Attachment attach) {
		    if (result == -1) {
		      try {
		        attach.client.close();
		        System.out.format("Stopped   listening to the   client %s%n",
		            attach.clientAddr);
		      } catch (IOException ex) {
		        ex.printStackTrace();
		      }
		      return;
		    }

		    if (attach.isRead) {
		      attach.buffer.flip();
		      int limits = attach.buffer.limit();
		      byte bytes[] = new byte[limits];
		      attach.buffer.get(bytes, 0, limits);
		      Charset cs = Charset.forName("UTF-8");
		      String msg = new String(bytes, cs);
		      System.out.format("Client at  %s  says: %s%n", attach.clientAddr,
		          msg);
		      attach.isRead = false; // It is a write
		      attach.buffer.rewind();

		    } else {
		      // Write to the client
		      attach.client.write(attach.buffer, attach, this);
		      attach.isRead = true;
		      attach.buffer.clear();
		      attach.client.read(attach.buffer, attach, this);
		    }
		  }

		  @Override
		  public void failed(Throwable e, Attachment attach) {
		    e.printStackTrace();
		  }
		}

}
