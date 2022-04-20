package zad1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NioTools {
	private static Lock lock = new ReentrantLock();
	private static Map<SocketChannel, ByteBuffer> buffers = new HashMap<>();
	private static Map<SocketChannel, Queue<String>> messageQueues = new HashMap<>();
	
	public static Queue<String> readChannel(SocketChannel channel) throws IOException {
		if (!buffers.containsKey(channel))
			buffers.put(channel,  ByteBuffer.allocateDirect(1024));
		ByteBuffer readByteBuffer = buffers.get(channel);
		
		if (!messageQueues.containsKey(channel))
			messageQueues.put(channel, new ConcurrentLinkedQueue<>());
		Queue<String> messages = messageQueues.get(channel);
		
		try {
			lock.lock();
			StringBuilder sb = new StringBuilder();
		

			int bytesRead;
			do {
				// read
				bytesRead = channel.read(readByteBuffer);
				readByteBuffer.flip();
				
				// write
				CharBuffer cbuf = Charset.forName("UTF-8").decode(readByteBuffer);
				while (cbuf.hasRemaining()) {
					char c = cbuf.get();
					if (c == '\n') {
						messages.add(sb.toString());
						sb = new StringBuilder();
					} else {
						sb.append(c);
					}
				}
				
				// cleanup
				readByteBuffer.compact();
			} while (bytesRead > 0);
		} finally {
			lock.unlock();
		}
		return messages;
	}
	
	public static void writeChannel(SocketChannel channel, String payload) throws IOException {
		ByteBuffer sendBuffer = ByteBuffer.wrap(payload.getBytes("UTF-8"));
		
		try {
			lock.lock();
			while (sendBuffer.hasRemaining()) 
				channel.write(sendBuffer);
		} finally {
			lock.unlock();
		}
	}
}
