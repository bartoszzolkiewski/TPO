package zad2.GUI.SearchDataDialog.Controllers;

import zad2.Country;
import zad2.GUI.SearchDataDialog.SearchDataDialog;

public class SearchDataDialogController {
	private SearchDataDialog searchDataDialog;

	public SearchDataDialogController(SearchDataDialog searchDataDialog) {
		this.searchDataDialog = searchDataDialog;
	}

	public void handleCancelButtonClick() {
		this.searchDataDialog.dispose();
	}

	public void handleOKButtonClick() {
		String countryName, cityName, currencyCode;
		cityName = this.searchDataDialog.getCityInputField().getText();
		countryName = this.searchDataDialog.getCountryInputField().getText();
		currencyCode = this.searchDataDialog.getCurrencyInputField().getText();
		
		if (Country.isValidCountryName(countryName)) {
			this.searchDataDialog.windowController.handleSearchDataDialogSubmit(countryName, cityName, currencyCode);
			this.searchDataDialog.dispose();
		} else {
			this.searchDataDialog.getCountryInputField().setText("Enter a valid country name");
		}
	}
}
