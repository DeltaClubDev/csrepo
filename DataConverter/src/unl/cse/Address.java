package unl.cse;

/*
 * Child elements for the addressCollection ArrayList
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "unl.cse")
@XmlType(propOrder = { "street", "city", "state", "zip", "country"})
public class Address {
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
	
	public Address() { }

	@XmlElement(name = "street")
	public void setStreet(String street) {
		this.street = street;
	}

	@XmlElement(name = "city")
	public void setCity(String city) {
		this.city = city;
	}

	@XmlElement(name = "state")
	public void setState(String state) {
		this.state = state;
	}

	@XmlElement(name = "zip")
	public void setZip(String zip) {
		this.zip = zip;
	}

	@XmlElement(name = "country")
	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public String getCountry() {
		return country;
	}
}
