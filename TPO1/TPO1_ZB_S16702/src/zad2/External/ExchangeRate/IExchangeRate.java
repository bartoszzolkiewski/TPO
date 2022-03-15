package zad2.External.ExchangeRate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zad2.External.ExchangeRate.dataclass.ExchangeRateData;

public interface IExchangeRate {
	@GET("/latest")
	Call<ExchangeRateData> latest(@Query("base") String baseCurrency, @Query("symbols") String symbols);
}
