package unl.cse;

/*
 * Main Person Object, host 3 child parent elements
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "person")
@XmlType(propOrder = {"id", "namesCollection", "broker", "addressCollection", "emailsCollection"} )
public class Persons {

	private String id;
	private String[] broker;
	
	@XmlElementWrapper(name = "emails")
	@XmlElement(name = "email")
	private ArrayList<String> emailsCollection = new ArrayList<String>();
	
	@XmlElement(name = "names")
	private ArrayList<Name> namesCollection = new ArrayList<Name>();
	
	@XmlElement(name = "address")
	private ArrayList<Address> addressCollection = new ArrayList<Address>();
	
	public Persons() { }
	
	@XmlElement(name = "id")
	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name = "broker")
	public void setBroker(String[] broker) {
		this.broker = broker;
	}

	public void setName(Name name) {
		this.namesCollection.add(name);
	}

	public void setAddress(Address address) {
		this.addressCollection.add(address);
	}

	public void setEmails(String[] emails) {
		for (String s : emails) {
			if (s != null) {
				this.emailsCollection.add(s);
			} else {
				this.emailsCollection.add("N/A");
			}
		}
	}
	
	public List<Address> getAddress() {
    	return Collections.unmodifiableList(this.addressCollection);
	}
	
	public List<Name> getNames() {
    	return Collections.unmodifiableList(this.namesCollection);
	}
	
	public List<String> getEmails() {
		return Collections.unmodifiableList(this.emailsCollection);
	}

	public String getId() {
		return id;
	}

	public String[] getBroker() {
		return broker;
	}
}