package com.concurrency.app.web.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class NonBlockIO {
	public void fileChannel() {
		try {
		    FileInputStream fis = new FileInputStream("D:\\testin.txt"); // Path of Input text file  
		    ReadableByteChannel rbc = fis.getChannel();  
		} catch (Exception e) {}
	}
	public void socketChannel() {
		try {
			SocketChannel ch = SocketChannel.open();  
			ch.connect(new InetSocketAddress("somehost", 80));
		} catch (Exception e) {}	
	}
	public void serverSocketChannel() {
		try {
		    ServerSocketChannel ch = ServerSocketChannel.open();  
		    ch.socket().bind (new InetSocketAddress (80));  
		    
		   
		} catch (Exception e) {}	
	}
	private static void copyData(ReadableByteChannel src, WritableByteChannel dest) throws IOException   
    {  
        ByteBuffer buffer = ByteBuffer.allocateDirect(200 * 1024);   // allocate big buffer
        while (src.read(buffer) != -1)   
        {  
            // The buffer is used to drained  
            buffer.flip();  
            // keep sure that buffer was fully drained  
            while (buffer.hasRemaining())   
            {  
                dest.write(buffer);  
            }  
            buffer.clear(); // Now the buffer is empty, ready for the filling  
        }  
    }  
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Running very fast
		Long start = System.currentTimeMillis();
		File file = new File("c:\\temp\\hadoop-2.7.1.tar.gz");
	    FileInputStream input = new FileInputStream (file); // Path of Input text file  
        ReadableByteChannel source = input.getChannel();  
	    FileOutputStream output = new FileOutputStream ("c:\\temp\\testout.tar.gz"); // Path of Output text file  
        WritableByteChannel destination = output.getChannel();  
        copyData(source, destination);  
	    source.close();  
        destination.close();  
        System.out.println("Returned");
        System.out.println("Copy file size() "+file.length()/1024+" kb" + ", took "+(System.currentTimeMillis()-start)+" ms");
	}

}
