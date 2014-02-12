package unl.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="products", namespace="unl.cse")
@XmlType(propOrder = {"equipList", "consultList", "liceList"})
public class ProductsHub {
	
	@XmlElement(name="equipment")
	private final List<Equipment> equipList;
	@XmlElement(name="consultation")
	private final List<Consultation> consultList;
	@XmlElement(name="license")
	private final List<License> liceList;
	
	public ProductsHub(){
		this.equipList = new ArrayList<Equipment>();
		this.consultList = new ArrayList<Consultation>();
		this.liceList = new ArrayList<License>();
	}
	
	public void addEquipment(Equipment newEquipment) {
		this.equipList.add(newEquipment);
	}
	
	public void addConsutation(Consultation newConsultation) {
		this.consultList.add(newConsultation);
	}
	
	public void addLicense(License newLicense) {
		this.liceList.add(newLicense);
	}
	
	public List<Equipment> getEquipList() {
		return Collections.unmodifiableList(this.equipList);
	}
	
	public List<Consultation> getConsultList() {
		return Collections.unmodifiableList(this.consultList);
	}
	
	public List<License> getLiceList() {
		return Collections.unmodifiableList(this.liceList);
	}
}