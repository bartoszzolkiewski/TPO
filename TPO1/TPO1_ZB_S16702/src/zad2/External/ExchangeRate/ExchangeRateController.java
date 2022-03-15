package zad2.External.ExchangeRate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zad2.External.BaseApiController;
import zad2.External.ExchangeRate.dataclass.ExchangeRateData;


public class ExchangeRateController extends BaseApiController {
	private static final String API_BASE_URL = "https://api.exchangerate.host/";
	
	private final IExchangeRate client;

	public ExchangeRateController() {
		Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
        		
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.client = retrofit.create(IExchangeRate.class);
	}
	
	
	
	/**
	 * Returns a GeoCoding object that represents location data for
	 * country specified.
	 * @param query A string can contain {city name},{state code},{country code}
	 * @return GeoCoding
	 */
	public ExchangeRateData latest(String baseCurrency, String toCurrency) {
		Call<ExchangeRateData> call = this.client.latest(baseCurrency, toCurrency);
		Response<ExchangeRateData> response = this.makeCall(call);
		
		ExchangeRateData result = null;
		if (response.isSuccessful()) {
			result = response.body();
		}
		
		return result;
	}
}
