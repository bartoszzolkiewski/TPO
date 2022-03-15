package zad2.GUI.Window.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import zad2.GUI.Window.Controllers.WindowController;

public class ChangeDataButtonClickActionListener implements ActionListener {
	private WindowController windowController;

	public ChangeDataButtonClickActionListener(WindowController windowController) {
		this.windowController = windowController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.windowController.handleChangeDataButtonClick();
	}

}
