package unl.cse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="unl.cse")
@XmlType(propOrder={"code", "name", "price", "annualPrice"})

public class License {
	private String code;
	private String name;
	public Double price;
	public Double annualPrice;
	
	public License() { }
	
	@XmlElement(name="code")
	public void setCode(String code) {
		this.code = code.trim();
	}
	
	@XmlElement(name="name")
	public void setName(String name) {
		this.name = name.trim();
	}
	
	@XmlElement(name="price")
	public void setPrice(String price) {
		Double temp;
		try {
			temp = Double.parseDouble(price.trim());
		} catch (Exception e) {
			e.printStackTrace();
			temp = 0.0;
		}
		this.price = temp;
	}
	
	@XmlElement(name="annualPrice")
	public void setAnnualPrice(String annualPrice) {
		Double temp;
		try {
			temp = Double.parseDouble(annualPrice.trim());
		} catch (Exception e) {
			e.printStackTrace();
			temp = 0.0;
		}
		this.annualPrice = temp;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public Double getAnnualPrice() {
		return annualPrice;
	}
}