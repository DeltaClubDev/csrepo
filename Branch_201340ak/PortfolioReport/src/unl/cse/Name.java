package unl.cse;

/*
 * Child elements for the namesCollection ArrayList
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "unl.cse")
@XmlType(propOrder = { "firstname", "lastname" })
public class Name {
	private String firstname;
	private String lastname;
	
	public Name() { }

	@XmlElement(name = "first")
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@XmlElement(name = "last")
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}
}