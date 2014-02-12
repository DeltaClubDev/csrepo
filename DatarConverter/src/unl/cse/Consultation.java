package unl.cse;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder={"code", "name", "hourPrice", "consultList"})
public class Consultation {
	
	private String code;
	private String name;
	public Double hourPrice;
	
	@XmlElement(name="consultant")
	private final ArrayList<Persons> consultList;
	
	public Consultation() {
		this.consultList = new ArrayList<Persons>();	
	}
	
	@XmlElement(name="code")
	public void setCode(String code) {
		this.code = code.trim();
	}
	
	@XmlElement(name="name")
	public void setName(String name) {
		this.name = name.trim();
	}
	
	@XmlElement(name="hourPrice")
	public void setHourPrice(String hourPrice) {
		Double temp;
		try {
			temp = Double.parseDouble(hourPrice.trim());
		} catch (Exception e) {
			e.printStackTrace();
			temp = 0.0;
		}
		this.hourPrice = temp;
	}
	
	public void addConsultList(List<Persons> consult) {
		this.consultList.addAll(consult);
	}
	
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public Double getHourPrice() {
		return hourPrice;
	}
	
	public List<Persons> getConsultList() {
		return Collections.unmodifiableList(this.consultList);
	}
}