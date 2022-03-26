package zad2;

import java.util.Currency;
import java.util.Locale;

public class Country {
	public final String name;

	public final Locale locale;
	public final Currency currency;
		
	public final String country_code;
	public final String currency_code;


	public Country(String name) {
		this.name = name;
		
		this.locale = Country.getLocaleForCountryName(this.name);
		this.currency = Currency.getInstance(this.locale);
		
		this.country_code = this.locale.getCountry();
		this.currency_code = this.currency.getCurrencyCode();
	}
	
	public static boolean isValidCountryName(String countryName) {
		Locale locale = Country.getLocaleForCountryName(countryName);
		return locale != null;
	}
	
	private static Locale getLocaleForCountryName(String name) {

		for (Locale locale : Locale.getAvailableLocales()) {
			for (Locale locale2 : Locale.getAvailableLocales()) {
		        if (locale.getDisplayCountry(locale2).toLowerCase().equals(name.toLowerCase())) {
		            return locale;
		           
		        }
			}
	    }
		return null;
	}
}
