package unl.cse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="unl.cse")
@XmlType(propOrder={"code", "name", "unitPrice"})
public class Equipment {
	String code;
	String name;
	Double unitPrice;
	
	public String getCode() {
		return code;
	}
	@XmlElement(name="code")
	public void setcode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	@XmlElement(name="name")
	public void setName(String name) {
		this.name = name;
	}
	
	public Double unitPrice() {
		return unitPrice;
	}
	@XmlElement(name="unitPrice")
	public void setPricePerUnit(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
	
	
	

}