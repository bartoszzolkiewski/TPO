/**
 *
 *  @author Żółkiewski Bartosz S16702
 *
 */

package zad1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ChatServer implements Runnable {
	private Thread thread;
	private Selector selector;
	
	private String host;
	private int port;
	private ServerSocketChannel ssc;
	
	private Logger logger;
	private final Map<ConnectedClient, SocketChannel> clients = new HashMap<>();

	public ChatServer(String host, int port) {
		this.host = host;
		this.port = port;
		this.logger = new Logger();
	}

	public void startServer() {
		this.setupConnections();
		
		this.thread = new Thread(this);
		this.thread.start();
		
		System.out.println("Server started\n");
	}

	public void stopServer() {
		this.thread.interrupt();			
		System.out.println("Server stopped");

	}

	public String getServerLog() {
		return this.logger.dump();
	}

	@Override
	public void run() {	
		this.serviceConnections();
		
		try {
			this.selector.close();
			this.ssc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setupConnections() {
		try {
			this.ssc = ServerSocketChannel.open();
			this.ssc.configureBlocking(false);
			this.ssc.socket().bind(new InetSocketAddress(this.host, this.port));
			this.selector = Selector.open();
			this.ssc.register(this.selector, SelectionKey.OP_ACCEPT);
		} catch (Exception exc) {}
	}
	
	private void serviceConnections() {
		while (!this.thread.isInterrupted()) {
			try {
				this.selector.select();
				
				if (this.thread.isInterrupted())
					break;
				
				Set<SelectionKey> keys = this.selector.selectedKeys();
				Iterator<SelectionKey> iter = keys.iterator();

				while (iter.hasNext()) {
					SelectionKey key = (SelectionKey) iter.next();
					iter.remove();
					
					if (key.isAcceptable()) {
						this.register();
					}
					
					if (key.isReadable()) {
						this.handle(key);
					}
				}
			} catch (IOException exc) {
				continue;
			}	
		}
	}
	
	private void register() throws IOException {
		SocketChannel cc = this.ssc.accept();
		cc.configureBlocking(false);
		cc.register(this.selector, SelectionKey.OP_READ);
	}

	private void handle(SelectionKey key) throws IOException {
		SocketChannel cc = (SocketChannel) key.channel();

		Queue<String> messages = NioTools.readChannel(cc);
		
		while (!messages.isEmpty()) {
			String msg = messages.poll();
			String [] arr = msg.split(" ", 2);
			String method = arr[0];
			String payload = arr[1];
			
			switch (method) {
				case "Hello":
					this.handleHello(cc, key, payload);
					break;
				case "Push":
					this.handlePush(cc, key, payload);
					break;
				case "Bye":
					this.handleBye(cc, key);
					break;
				default:
					break;
			}
			
		}
	}
	
	private void handleBye(SocketChannel cc, SelectionKey key) throws IOException {
		ConnectedClient client = (ConnectedClient) key.attachment();
		this.logger.log(client.id + " logged out");
		this.broadcast(client.id + " logged out");
	}

	private void handleHello(SocketChannel channel, SelectionKey key, String payload) {
		ConnectedClient client = new ConnectedClient(payload);
		key.attach(client);

		this.clients.put(client, channel);
		this.logger.log(payload + " logged in");
		this.broadcast(payload + " logged in");
		
	}
	
	private void broadcast(String message) {
		for (SocketChannel channel : this.clients.values()) {
			try {
				NioTools.writeChannel(channel, message + '\n');
			} catch (IOException e) {
				continue;
			} 
		}
	}
	
	private void handlePush(SocketChannel channel, SelectionKey key, String payload) {
		ConnectedClient client = (ConnectedClient) key.attachment();
		this.logger.log(client.id + ": " + payload);
		this.broadcast(client.id + ": " + payload);
	}
}
