package unl.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * <b>ProductsHub</b> will hold a instances of the 3 types of products.
 * See the constructor to view which objects those are.
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.5.0
 */
public class ProductsHub {
	
	private final List<Equipment> equipList;
	private final List<Consultation> consultationList;
	private final List<License> liceList;
	
	/**
	 * <b>ProductsHub</b> will construct 3 <b>ArrayList</b> which
	 * will store instances of <b>Equipment, Consultation, </b>and 
	 * <b>License</b>
	 */
	public ProductsHub(){
		this.equipList = new ArrayList<Equipment>();
		this.consultationList = new ArrayList<Consultation>();
		this.liceList = new ArrayList<License>();
	}
	
	/**
	 * 
	 * @param newEquipment - Adds an instance of <b>Equipment</b> to the hub.
	 */
	public void addEquipment(Equipment newEquipment) {
		this.equipList.add(newEquipment);
	}
	
	/**
	 * 
	 * @param newConsultation - Adds an instance of <b>Consultation</b> to the hub.
	 */
	public void addConsutation(Consultation newConsultation) {
		this.consultationList.add(newConsultation);
	}
	
	/**
	 * 
	 * @param newLicense - Adds an instance of <b>License</b> to the hub.
	 */
	public void addLicense(License newLicense) {
		this.liceList.add(newLicense);
	}
	
	/**
	 * 
	 * @return - Returns an <i>unmodifiableList</i> of all the <b>Equipment</b> instances
	 */
	public List<Equipment> getEquipList() {
		return Collections.unmodifiableList(this.equipList);
	}
	
	public boolean equipIsThere(String code) {
		for(Equipment e : this.equipList) {
			if (e.getCode().equals(code.trim())) {
				return true;
			}
		} return false;
	}
	
	/**
	 * 
	 * @param id - Used to match the correct equipment
	 * @return - Returns a List of Equipment
	 */
	public Equipment getEquipmentById(String id) {
		for (Equipment e : this.equipList) {
			if (id.equals(e.getCode())) {
				return e;
			}
		} return null;
	}
	
	/**
	 * 
	 * @return - Returns an <i>unmodifiableList</i> of all the <b>Consultation</b> instances
	 */
	public List<Consultation> getConsultList() {
		return Collections.unmodifiableList(this.consultationList);
	}
	
	public boolean consultIsThere(String code) {
		for(Consultation c : this.consultationList) {
			if (c.getCode().equals(code.trim())) {
				return true;
			}
		} return false;
	}
	
	/**
	 * 
	 * @param id - Used to match the correct Consultation
	 * @return - Returns a List of Consultation
	 */
	public Consultation getConsultById(String id) {
		for (Consultation c : this.consultationList) {
			if (id.equals(c.getCode())) {
				return c;
			}
		} return null;
	}
	
	/**
	 * 
	 * @return - Returns an <i>unmodifiableList</i> of all the <b>License</b> instances
	 */
	public List<License> getLiceList() {
		return Collections.unmodifiableList(this.liceList);
	}
	
	public boolean liceIsThere(String code) {
		for(License l : this.liceList) {
			if (l.getCode().equals(code.trim())) {
				return true;
			}
		} return false;
	}
	
	/**
	 * 
	 * @param id - Used to match the correct License
	 * @return - Returns a List of License
	 */
	public License getLicenseById(String id) {
		for (License l : this.liceList) {
			if (id.equals(l.getCode())) {
				return l;
			}
		} return null;
	}
	
	public double getSubTotal() {
		double subTot = 0.0;
		for (Equipment e : this.equipList) {
			subTot += e.getTotal();
		} for (License l : this.liceList) {
			subTot += l.getTotal();
		} for (Consultation c : this.consultationList) {
			subTot += c.getTotal();
		} return subTot;
	}
	
	public double getFee(boolean comply) {
		double fee = 0.0;
		if (comply == true) { fee += 125.0; }
		for (License l : this.liceList) {
			fee += l.getPrice();
		} for (Consultation c : this.consultationList) {
			fee += 150.0;
		} return fee;
	}
	
	public double getTaxed(boolean comply) {
		double taxed = 0.0;
		if (comply == true) { return taxed; }
		for (Equipment e : this.equipList) {
			taxed += e.getTaxed();
		} for (License l : this.liceList) {
			taxed += l.getTaxed();
		} for (Consultation c : this.consultationList) {
			taxed += c.getTaxed();
		} return taxed;
	}
	
	public double getCost(boolean comply) {
		double cost = getSubTotal() + getFee(comply) + getTaxed(comply);
		return cost;
	}
	
	public List<String> getCodes() {
		List<String> codes = new ArrayList<String>();
		for (Equipment e : this.equipList) {
			codes.add(e.getCode());
		} for (License l : this.liceList) {
			codes.add(l.getCode());
		} for (Consultation c : this.consultationList) {
			codes.add(c.getCode());
		} return codes;
	}
	
	public String getNameByCode(String code) {
		String name = "N/A";
		for (Equipment e : this.equipList) {
			if (code.equals(e.getCode())) { return e.getName(); }
		} for (License l : this.liceList) {
			if (code.equals(l.getCode())) { return l.getName(); }
		} for (Consultation c : this.consultationList) {
			if (code.equals(c.getCode())) { return c.getName(); }
		} return name;
	}
	
	public String getInfoByCode(String code) {
		String info = "N/A";
		for (Equipment e : this.equipList) {
			if (code.equals(e.getCode())) { return e.getInfo(); }
		} for (License l : this.liceList) {
			if (code.equals(l.getCode())) { return l.getInfo(); }
		} for (Consultation c : this.consultationList) {
			if (code.equals(c.getCode())) { return c.getInfo(); }
		} return info;
	}
	
	public double getFeeByCode(String code, boolean comply) {
		double fee = 0.0;
		if (comply == true) { fee += 125.0; }
		for (License l : this.liceList) {
			if (code.equals(l.getCode())) { return fee += l.getPrice(); }
		}for (Consultation c : this.consultationList) {
			fee += 150.0;
		} return fee;
	}
	
	public double getTaxedByCode(String code, boolean comply) {
		if (comply == true) { return 0.0; }
		double taxed = 0.0;
		for (Equipment e : this.equipList) {
			if (code.equals(e.getCode())) { return e.getTaxed(); }
		} for (License l : this.liceList) {
			if (code.equals(l.getCode())) { return l.getTaxed(); }
		} for (Consultation c : this.consultationList) {
			if (code.equals(c.getCode())) { return c.getTaxed(); }
		} return taxed;
	}
	
	public double getSubTotalByCode(String code) {
		double total = 0.0;
		for (Equipment e : this.equipList) {
			if (code.equals(e.getCode())) { return e.getTotal(); }
		} for (License l : this.liceList) {
			if (code.equals(l.getCode())) { return l.getTotal(); }
		} for (Consultation c : this.consultationList) {
			if (code.equals(c.getCode())) { return c.getTotal(); }
		} return total;
	}
}