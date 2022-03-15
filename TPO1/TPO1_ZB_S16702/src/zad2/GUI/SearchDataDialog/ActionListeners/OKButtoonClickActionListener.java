package zad2.GUI.SearchDataDialog.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import zad2.GUI.SearchDataDialog.Controllers.SearchDataDialogController;

public class OKButtoonClickActionListener implements ActionListener {
	private SearchDataDialogController searchDataDialogController;

	public OKButtoonClickActionListener(SearchDataDialogController searchDataDialogController) {
		this.searchDataDialogController = searchDataDialogController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.searchDataDialogController.handleOKButtonClick();
	}

}
