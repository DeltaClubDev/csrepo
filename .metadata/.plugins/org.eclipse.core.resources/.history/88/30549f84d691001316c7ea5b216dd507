package unl.cse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="unl.cse")
@XmlType(propOrder={"code", "name", "price", "annualPrice"})

public class License {
	private String code;
	private String name;
	private Double price;
	private Double annualPrice;
	
	public License() { }
	
	public String getCode() {
		return code;
	}
	
	@XmlElement(name="code")
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	@XmlElement(name="name")
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getPrice() {
		return price;
	}
	
	@XmlElement(name="price")
	public void setPrice(String price) {
		Double temp;
		try {
			temp = Double.parseDouble(price);
		} catch (Exception e) {
			e.printStackTrace();
			temp = 0.0;
		}
		this.price = temp;
	}
	
	public Double getAnnualPrice() {
		return annualPrice;
	}
	
	@XmlElement(name="annualPrice")
	public void setAnnualPrice(String annualPrice) {
		Double temp;
		try {
			temp = Double.parseDouble(annualPrice);
		} catch (Exception e) {
			e.printStackTrace();
			temp = 0.0;
		}
		this.annualPrice = temp;
	}
}