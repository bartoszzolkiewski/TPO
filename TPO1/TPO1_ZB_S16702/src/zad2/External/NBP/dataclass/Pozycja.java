package zad2.External.NBP.dataclass;

import javax.xml.bind.annotation.XmlElement;

public class Pozycja {
		
	@XmlElement(name="nazwa_waluty", required=true)
	private String nazwaWaluty;
	
	@XmlElement(name="przelicznik", required=true)
	private String przelicznik;
	
	@XmlElement(name="kod_waluty", required=true)
	private String kodWaluty;
	
	@XmlElement(name="kurs_sredni", required=true)
	private String kursSredni;
	  
	public String getNazwaWaluty() {
		return this.nazwaWaluty;
	}
  
	public String getPrzelicznik() {
		return this.przelicznik;
	}
	
	public String getKodWaluty() {
		return this.kodWaluty;
	}
	
	public Double getKursSredni() {
		return Double.parseDouble(this.kursSredni.replace(',', '.'));
	}
}
