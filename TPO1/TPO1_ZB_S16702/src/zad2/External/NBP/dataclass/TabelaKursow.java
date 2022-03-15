package zad2.External.NBP.dataclass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="tabela_kursow")
public class TabelaKursow {

  @XmlElement(name="numer_tabeli", required=true)
  private String numerTabeli;
  
  @XmlElement(name="data_publikacji", required=true)
  private String dataPublikacji;

  @XmlElement(name = "pozycja")
  private List<Pozycja> pozycje;
  
  
  public String getNumerTabeli() {
	  return this.numerTabeli;
  }
  
  public String getDataPublikacji() {
	  return this.dataPublikacji;
  }
  
  public List<Pozycja> getPozycje() {
	  return this.pozycje;
  }
  
  public Map<String,Pozycja> getPozycjeMap() {
	  Map<String,Pozycja> map = new HashMap<>();
	  
	  for (Pozycja p : this.pozycje) {
		  map.put(p.getKodWaluty(), p);
	  }
	  return map;
  }
}
