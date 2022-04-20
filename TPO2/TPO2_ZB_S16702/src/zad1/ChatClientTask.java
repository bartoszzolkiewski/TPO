/**
 *
 *  @author Żółkiewski Bartosz S16702
 *
 */

package zad1;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ChatClientTask extends FutureTask<ChatClient> {
	public ChatClient client;
	
	public ChatClientTask(Callable<ChatClient> callable) {
		super(callable);
	}

	public static ChatClientTask create(ChatClient client, List<String> msgs, int wait) {
		Callable<ChatClient> func = new Callable<ChatClient>() {
			@Override
			public ChatClient call() throws Exception {
				client.sendLogin();
				TimeUnit.MILLISECONDS.sleep(wait);
				
				for (String msg : msgs) {
					TimeUnit.MILLISECONDS.sleep(wait);
					client.sendMessage(msg);
				}
				
				TimeUnit.MILLISECONDS.sleep(wait);
				client.sendLogout();
				
				while (!client.isInterrupted()) {}
				return client;
			}
		};
		
		ChatClientTask task = new ChatClientTask(func);
		task.client = client;
		return task;
	}


	public ChatClient getClient() {
		return this.client;
	}
}
