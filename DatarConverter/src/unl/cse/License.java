package unl.cse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="unl.cse")
@XmlType(propOrder={"code", "name", "price", "annualPrice"})

public class License {
	String code;
	String name;
	Double price;
	Double annualPrice;
	
	
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
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAnnualPrice() {
		return annualPrice;
	}
	@XmlElement(name="annualPrice")
	public void setAnnualPrice(Double annualPrice) {
		this.annualPrice = annualPrice;
	}

}
