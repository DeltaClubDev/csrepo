package unl.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * <b>PubComp</b> is a type of company where the instances live in
 * the <b>CustomersHub</b> class. This class will make use of the
 * <b>Persons</b> and <b>Address</b> class is it has a primary 
 * human representation and also owns an address.
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.1.0
 */

public class PubComp {
	
	private String code;
	private String name;
	
	@XmlElement(name="humanRep")
	private final ArrayList<Persons> humanRepList;
	@XmlElement(name="address")
	private final ArrayList<Address> adrList;
	
	public PubComp() { 
		this.humanRepList = new ArrayList<Persons>();
		this.adrList = new ArrayList<Address>();
	}
	
	@XmlElement(name="code")
	public void setCode(String code) {
		this.code = code.trim();
	}
	
	@XmlElement(name="name")
	public void setName(String name) {
		this.name = name.trim();
	}
	
	public void addHumanRep(List<Persons> human) {
		this.humanRepList.addAll(human);
	}
	
	public void addAddress(Address adr) {
		this.adrList.add(adr);
	}
	
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Persons> getHumanRep() {
		return Collections.unmodifiableList(this.humanRepList);
	}
	
	public List<Address> getAddress() {
    	return Collections.unmodifiableList(this.adrList);
	}
}