package zad2.External.NBP;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import zad2.External.NBP.dataclass.TabelaKursow;


public interface INBP {
	@GET("/kursy/kursya.html")
	Call<ResponseBody> getKursyA();
	
	@GET("/kursy/kursyb.html")
	Call<ResponseBody> getKursyB();
	
	@GET("/kursy/xml/{filename}")
	Call<TabelaKursow> getXMLFile(@Path("filename") String filename);
	
}
