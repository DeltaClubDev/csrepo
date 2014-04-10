package unl.cse;

import java.util.ArrayList; 
import java.util.Collections;
import java.util.List;

/**
 * <b>Persons</b> is used to construct a model for every individual.
 * References to this class are used very often in all the data models.
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.5.0
 */
public class Persons {

	private int primaryKey;
	private int addressID;
	private String id;
	private String firstName;
	private String lastName;
	private ArrayList<String> emailsList = new ArrayList<String>();
	private Address address;
	
	public Persons() { }
	
	/**
	 * <i>setId</i> sets the primary reference to an instance of a <b>Persons</b>
	 * @param id - Must be a String
	 */
	public void setId(String id) {
		this.id = id.trim();
	}
	
	public void setPrimaryKey(int key) {
		this.primaryKey = key;
	}
	
	public void setAddressID(int id) {
		this.addressID = id;
	}

	public void setFirstName(String name) {
		this.firstName = name.trim();
	}

	public void setLastName(String name) {
		this.lastName = name.trim();
	}

	/**
	 * <i>setAddress</i> will reference an instance of an <b>Address</b> class
	 * @param address - Accepts the <b>Address</b> object
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * <i>setEmails</i> will take an array of Strings and add them into an <b>ArrayList</b>.
	 * A person can have any number of emails or none at all, if their are none "N/A" is indicated.
	 * @param emails - Will take an array of emails
	 */
	public void setEmails(String[] emails) {
		for (String s : emails) {
			if (s != null) {
				this.emailsList.add(s);
			} else {
				this.emailsList.add("N/A");
			}
		}
	}

	public String getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @return - Returns an instance of a <b>Persons Address</b>
	 */
	public Address getAddress() {
    	return this.address;
	}
	
	/**
	 * 
	 * @return - Returns an <b>ArrayList</b> of emails
	 */
	public List<String> getEmails() {
		return Collections.unmodifiableList(this.emailsList);
	}
	
	public int getPrimaryKey() {
		return this.primaryKey;
	}
	
	public int getAddressID() {
		return this.addressID;
	}
}