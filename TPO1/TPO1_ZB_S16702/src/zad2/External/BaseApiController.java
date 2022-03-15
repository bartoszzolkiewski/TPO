package zad2.External;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public abstract class BaseApiController {
	protected <T> Response<T> makeCall(Call<T> call) {
		Response<T> response = null;
		try {
			response = call.execute();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return response;
	}
}
