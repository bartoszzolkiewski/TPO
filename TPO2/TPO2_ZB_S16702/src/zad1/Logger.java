package zad1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Logger {
	private List<String> entries;
	private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
	private static Lock lock = new ReentrantLock();
	
	public Logger() {
		this.entries = Collections.synchronizedList(new ArrayList<String>());
	}
	
	public synchronized void log(String message) {
		this.log(message, true);
	}
	
	public synchronized void log(String message, boolean addTimestamp) {
		try {
			lock.lock();
			
			Date date = new Date(System.currentTimeMillis());
			if (addTimestamp)
				this.entries.add(formatter.format(date) + " " + message);
			else
				this.entries.add(message);
		} finally {
			lock.unlock();
		}
	}
	
	public String dump() {
		StringBuilder sb = new StringBuilder();
		try {
			lock.lock();
			
			for (String row : this.entries) {
				sb.append(row + "\n");
			}
		} finally {
			lock.unlock();
		}
		
		return sb.toString();
	}
	
}
