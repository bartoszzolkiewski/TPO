package zad2.GUI.Window.Controllers;

import javax.swing.JDialog;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import zad2.Service;
import zad2.External.ExchangeRate.dataclass.ExchangeRateData;
import zad2.External.OpenWeather.dataclass.GeoCoding;
import zad2.External.OpenWeather.dataclass.WeatherData;
import zad2.GUI.SearchDataDialog.SearchDataDialog;
import zad2.GUI.Window.Window;

public class WindowController {
	private Window window;
	private JFXPanel jfxPanel;
	
	public WindowController(Window window) {
		this.window = window;
		this.jfxPanel = new JFXPanel();
	}
	
	public void handleChangeDataButtonClick() {
		try {
			SearchDataDialog dialog = new SearchDataDialog(this);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleSearchDataDialogSubmit(String countryName, String cityName, String currencyCode) {
		Service service = new Service(countryName);
		GeoCoding geoCoding = service.getGeoCoding(cityName);
		this.window.getGeoCodingButton().setText(geoCoding.toString());
		
		WeatherData weather = service.getWeatherObj(cityName);
		this.window.getWeatherDataButton().setText(weather.toString());
		
		ExchangeRateData exchangeRateData = service.getExchangeRateData(service.country.currency_code, currencyCode);
		Double nbpRate = service.getNBPRate();
		this.window.getExchangeDataButton().setText(exchangeRateData.toString("NBP Rate: " + String.format("%.2f", nbpRate)));
		
		this.window.getWebViewPanel().remove(jfxPanel);
		this.jfxPanel = new JFXPanel();
		this.window.getWebViewPanel().add(jfxPanel);

		// Creation of scene and future interactions with JFXPanel
		// should take place on the JavaFX Application Thread
		Platform.setImplicitExit(false);
		Platform.runLater(() -> {
			WebView webView = new WebView();
		    jfxPanel.setScene(new Scene(webView));
		    webView.getEngine().load("https://en.wikipedia.org/w/index.php?search=" + cityName);
		});
	}

}
