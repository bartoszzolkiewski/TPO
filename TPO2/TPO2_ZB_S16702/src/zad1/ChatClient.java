/**
 *
 *  @author Żółkiewski Bartosz S16702
 *
 */

package zad1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChatClient extends Thread {
	public final String id;
	public final String host;
	public final int port;

	private SocketChannel sc;
	private Logger logger;
	
	public ChatClient(String host, int port, String id) {
		this.host = host;
		this.port = port;
		this.id = id;
		this.logger = new Logger();
	}

	public void run() {
		this.readChannel();
		
		try {
			this.sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readChannel() {
		while (!this.isInterrupted()) {
			try {
				Queue<String> messages = NioTools.readChannel(this.sc);
				
				if (messages == null)
					continue;
				
				while (!messages.isEmpty()) {
					String msg = messages.poll();
					
					this.logger.log(msg, false);
					if (msg.contains(this.id + " logged out")) {
						this.interrupt();
						break;
					}
				}
				
			} catch (IOException e) {
				this.logException(e);
				break;
			} catch (NullPointerException e) {
				continue;
			}
		}
	}
	
	private void logException(Exception exc) {
		this.logger.log("*** " + exc.toString(), false);
	}

	public void login() {
		try {
			this.sc = SocketChannel.open(new InetSocketAddress(this.host, this.port));
			this.sc.configureBlocking(false);
			
			this.start();
			this.push("Hello " + this.id);
		} catch (IOException exc) {
			this.logException(exc);
		}
	}
	
	public void send(String msg) {
		this.push("Push " + msg);
	}
	
	public void logout() {
		this.push("Bye xd");
	}
	
	public String getChatView() {
		StringBuilder sb = new StringBuilder();
		sb.append("=== " + this.id + " chat view\n");
		sb.append(this.logger.dump());
		return sb.toString();
	}
	
	public void push(String s) {
		try {
			NioTools.writeChannel(this.sc, s + "\n");
		} catch (Exception e) {
			this.logException(e);
		}
		
	}
}
