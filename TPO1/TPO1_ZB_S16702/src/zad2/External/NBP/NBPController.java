package zad2.External.NBP;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;
import zad2.External.BaseApiController;
import zad2.External.NBP.dataclass.Pozycja;
import zad2.External.NBP.dataclass.TabelaKursow;

public class NBPController extends BaseApiController {
	private static final String API_BASE_URL = "https://www.nbp.pl/";
	
	private final INBP client;

	public NBPController() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(JaxbConverterFactory.create())
                .build();

        this.client = retrofit.create(INBP.class);
	}
	
	private String getXMLNameForA() {
		return this.getXMLName(this.client.getKursyA());
	}
	
	private String getXMLNameForB() {
		return this.getXMLName(this.client.getKursyB());
	}
	
	private String getXMLName(Call<ResponseBody> call) {
		Response<ResponseBody> response = this.makeCall(call);
		
		if (response.isSuccessful()) {
			try {
				Document doc = Jsoup.parse(response.body().string());
				Elements linkElements = doc.select("a[href]");
				for (Element link : linkElements) {
					String href = link.attr("href");
					
					if (href.contains(".xml")) {
						return href.substring(href.lastIndexOf('/') + 1);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return null;
	}
	
	private TabelaKursow getTabelaKursow(String xmlFileName) {
		Call<TabelaKursow> call = this.client.getXMLFile(xmlFileName);
		Response<TabelaKursow> response = this.makeCall(call);
		
		if (response.isSuccessful()) {
			return response.body();
		}
		return null;
	}
	
	public Double getNBPRate(String currencyCode) {
		List<String> xmlFileNames = Arrays.asList(new String[] {
			this.getXMLNameForA(),
			this.getXMLNameForB()
		});
		
		if (currencyCode.equals("PLN"))
			return 1d;
		
		for (String fileName : xmlFileNames) {
			TabelaKursow tabela = this.getTabelaKursow(fileName);
			Pozycja p = tabela.getPozycjeMap().get(currencyCode);
			if (p != null) {
				return p.getKursSredni();
			}
		}
		return null;
	}
	
}
