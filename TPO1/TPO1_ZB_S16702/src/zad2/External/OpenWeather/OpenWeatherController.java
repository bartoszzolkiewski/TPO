package zad2.External.OpenWeather;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zad2.External.BaseApiController;
import zad2.External.OpenWeather.dataclass.GeoCoding;
import zad2.External.OpenWeather.dataclass.WeatherData;


public class OpenWeatherController extends BaseApiController {
	private static final String API_BASE_URL = "http://api.openweathermap.org/";
	private static final String API_KEY = "86ab9c4ee55a61d6e53b2fba7fd655a6";
	
	private final IOpenWeather client;

	public OpenWeatherController() {
		Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.client = retrofit.create(IOpenWeather.class);
	}
	
	/**
	 * Returns a GeoCoding object that represents location data for
	 * country specified.
	 * @param query A string can contain {city name},{state code},{country code}
	 * @return GeoCoding
	 */
	public GeoCoding geoDirect(String city, String countryCode) {
		Call<List<GeoCoding>> call = this.client.geoDirect(city + "," + countryCode, API_KEY, 1);
		Response<List<GeoCoding>> response = this.makeCall(call);
		
		GeoCoding result = null;
		if (response.isSuccessful()) {
			result = response.body().get(0);
		}
		
		return result;
	}
	
	/**
	 * Returns a WeatherData object that represents current weather conditions
	 * in a location specified.
	 * @param lat
	 * @param lon
	 * @return WeatherData
	 */
	public WeatherData weather(Double lat, Double lon) {
		Call<WeatherData> call = this.client.weather(lat, lon, API_KEY);
		Response<WeatherData> response = this.makeCall(call);
		
		WeatherData result = null;
		if (response.isSuccessful()) {
			result = response.body();
		}
		return result;
	}
	
	/**
	 * Returns raw String object representing JSON response to the request.
	 * @param lat
	 * @param lon
	 * @return String
	 */
	public String weatherRaw(Double lat, Double lon) {
		Call<ResponseBody> call = this.client.weatherRaw(lat, lon, API_KEY);
		Response<ResponseBody> response = this.makeCall(call);
		
		String result = null;
		if (response.isSuccessful()) {
			try {
				result = response.body().string();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
