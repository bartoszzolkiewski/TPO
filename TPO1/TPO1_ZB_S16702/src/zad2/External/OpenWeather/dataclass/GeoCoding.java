package zad2.External.OpenWeather.dataclass;

import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeoCoding {

	@SerializedName("name")
	@Expose
	private String name;
	
	@SerializedName("local_names")
	@Expose
	private Map<String,String> localNames;
	
	@SerializedName("lat")
	@Expose
	private Double lat;
	
	@SerializedName("lon")
	@Expose
	private Double lon;
	
	@SerializedName("country")
	@Expose
	private String country;
	
	@SerializedName("state")
	@Expose
	private String state;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Map<String,String> getLocalNames() {
		return localNames;
	}
	
	public void setLocalNames(Map<String,String> localNames) {
		this.localNames = localNames;
	}
	
	public Double getLat() {
		return lat;
	}
	
	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	public Double getLon() {
		return lon;
	}
	
	public void setLon(Double lon) {
		this.lon = lon;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("<html>");
    	sb.append("Miasto: "); sb.append(this.getName()); sb.append("<br />");
    	sb.append("Kraj: "); sb.append(this.getCountry()); sb.append("<br />");
    	sb.append("Stan: "); sb.append(this.getState()); sb.append("<br />");
    	sb.append("Szerokość geo: "); sb.append(this.getLat()); sb.append("<br />");
    	sb.append("Długość geo: "); sb.append(this.getLon()); sb.append("<br />");
    	sb.append("</html>");
    	return sb.toString();
	}

}