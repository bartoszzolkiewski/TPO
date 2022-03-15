package zad2.GUI.SearchDataDialog.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import zad2.GUI.SearchDataDialog.Controllers.SearchDataDialogController;

public class CancelButtonClickActionListener implements ActionListener {
	private SearchDataDialogController getSearchDataDialogController;
	

	public CancelButtonClickActionListener(SearchDataDialogController getSearchDataDialogController) {
		this.getSearchDataDialogController = getSearchDataDialogController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.getSearchDataDialogController.handleCancelButtonClick();

	}

}
