/**
 *
 *  @author Żółkiewski Bartosz S16702
 *
 */

package zad2;

import java.awt.EventQueue;

import zad2.GUI.Window.Window;

public class Main {
  public static void main(String[] args) {
    Service s = new Service("Poland");
    String weatherJson = s.getWeather("Warsaw");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();
    
    
    EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Window window = new Window();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
    
  }
}
