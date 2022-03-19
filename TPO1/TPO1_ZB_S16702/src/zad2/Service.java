/**
 *
 *  @author Żółkiewski Bartosz S16702
 *
 */

package zad2;

import java.util.Map;

import zad2.External.ExchangeRate.ExchangeRateController;
import zad2.External.ExchangeRate.dataclass.ExchangeRateData;
import zad2.External.NBP.NBPController;
import zad2.External.OpenWeather.OpenWeatherController;
import zad2.External.OpenWeather.dataclass.GeoCoding;
import zad2.External.OpenWeather.dataclass.WeatherData;

public class Service {
	public final Country country;
	private OpenWeatherController openWeatherController;
	private ExchangeRateController exchangeRateController;
	private NBPController nbpController;

	public Service(String kraj) {
		this.country = new Country(kraj);
		this.openWeatherController = new OpenWeatherController();
		this.exchangeRateController = new ExchangeRateController();
		this.nbpController = new NBPController();
	}
	
	public GeoCoding getGeoCoding(String miasto) {
		GeoCoding cityGeoCoding = this.openWeatherController.geoDirect(miasto, this.country.country_code);
		return cityGeoCoding;
	}
	
	public String getWeather(String miasto) {
		GeoCoding cityGeoCoding = this.openWeatherController.geoDirect(miasto, this.country.country_code);

		return this.openWeatherController.weatherRaw(
			cityGeoCoding.getLat(), 
			cityGeoCoding.getLon()
		);
	}
	
	public WeatherData getWeatherObj(String miasto) {
		GeoCoding cityGeoCoding = this.getGeoCoding(miasto);

		return this.openWeatherController.weather(
			cityGeoCoding.getLat(), 
			cityGeoCoding.getLon()
		);
	}
	
	public ExchangeRateData getExchangeRateData(String currencyFrom, String currencyTo) {
		ExchangeRateData exchangeRateData = this.exchangeRateController.latest(currencyFrom, currencyTo);
		return exchangeRateData;
	}
	
	public Double getRateFor(String kod_waluty) {
		ExchangeRateData exchangeRateData = this.getExchangeRateData(this.country.currency_code, kod_waluty.toUpperCase());
		Map<String,Double> rates = exchangeRateData.getRates();		
		return rates.get(kod_waluty.toUpperCase());
	}
	
	public Double getNBPRate() {
		return this.nbpController.getNBPRate(this.country.currency_code.toUpperCase());
	}
}  
