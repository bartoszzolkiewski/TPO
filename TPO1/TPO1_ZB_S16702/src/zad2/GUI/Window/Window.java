package zad2.GUI.Window;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import zad2.GUI.Window.ActionListeners.ChangeDataButtonClickActionListener;
import zad2.GUI.Window.Controllers.WindowController;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.UIManager;


public class Window {
	public JFrame frame;
	private WindowController windowController;
	private JButton geoCodingButton;
	private JButton weatherDataButton;
	private JButton exchangeDataButton;
	private JPanel webViewPanel;

	/**
	 * Create the application.
	 */
	public Window() {
		this.windowController = new WindowController(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(1100, 550));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		frame.getContentPane().add(splitPane);
		
		JPanel cityInfoPanel = new JPanel();
		cityInfoPanel.setBackground(UIManager.getColor("ComboBox.background"));
		splitPane.setLeftComponent(cityInfoPanel);
		cityInfoPanel.setLayout(new GridLayout(4, 0, 0, 0));
		
		geoCodingButton = new JButton("n/a");
		geoCodingButton.setEnabled(false);
		cityInfoPanel.add(geoCodingButton);
		
		weatherDataButton = new JButton("n/a");
		weatherDataButton.setEnabled(false);
		cityInfoPanel.add(weatherDataButton);
		
		exchangeDataButton = new JButton("n/a");
		exchangeDataButton.setEnabled(false);
		cityInfoPanel.add(exchangeDataButton);
		
		JButton changeDataButton = new JButton("Change data");
		changeDataButton.addActionListener(new ChangeDataButtonClickActionListener(this.windowController));
		cityInfoPanel.add(changeDataButton);
		
		webViewPanel = new JPanel();
		splitPane.setRightComponent(webViewPanel);
		splitPane.setDividerLocation(250);
	}

	public JButton getGeoCodingButton() {
		return geoCodingButton;
	}
	public JButton getWeatherDataButton() {
		return weatherDataButton;
	}
	public JButton getExchangeDataButton() {
		return exchangeDataButton;
	}
	public JPanel getWebViewPanel() {
		return webViewPanel;
	}
}
