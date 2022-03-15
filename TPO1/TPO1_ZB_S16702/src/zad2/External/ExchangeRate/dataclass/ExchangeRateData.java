
package zad2.External.ExchangeRate.dataclass;

import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ExchangeRateData {

    @SerializedName("motd")
    @Expose
    private Motd motd;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("rates")
    @Expose
    private Map<String,Double> rates;

    public Motd getMotd() {
        return motd;
    }

    public void setMotd(Motd motd) {
        this.motd = motd;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String,Double> getRates() {
        return this.rates;
    }

    public void setRates(Map<String,Double> rates) {
        this.rates = rates;
    }
    
    @Override
    public String toString() {
    	return this.toString("");
    }
    
    public String toString(String additionalMessage) {
    	Map<String,Double> rates = this.getRates();
    	StringBuilder sb = new StringBuilder();
    	sb.append("<html>");
    	sb.append("Rates: <br />"); 
    	
    	for (Entry<String,Double> e : rates.entrySet()) {
    		sb.append("-  "); sb.append(this.getBase()); sb.append(" -> "); sb.append(e.getKey()); sb.append(": "); sb.append(e.getValue()); sb.append("<br />");
    	}
    	sb.append(additionalMessage);
    	sb.append("</html>");
    	return sb.toString();
    }

}
