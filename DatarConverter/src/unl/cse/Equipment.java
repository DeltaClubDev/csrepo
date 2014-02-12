package unl.cse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="unl.cse")
@XmlType(propOrder={"code", "name", "unitPrice"})
public class Equipment {
	private String code;
	private String name;
	public Double unitPrice;
	
	public Equipment() { }
	
	@XmlElement(name="code")
	public void setCode(String code) {
		this.code = code.trim();
	}
	
	@XmlElement(name="name")
	public void setName(String name) {
		this.name = name.trim();
	}
	
	@XmlElement(name="unitPrice")
	public void setUnitPrice(String unitPrice) {
		Double temp;
		try {
			temp = Double.parseDouble(unitPrice.trim());
		} catch (Exception e) {
			e.printStackTrace();
			temp = 0.0;
		}
		this.unitPrice = temp;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public Double getUnitPrice() {
		return unitPrice;
	}
}