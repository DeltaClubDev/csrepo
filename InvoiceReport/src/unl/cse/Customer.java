package unl.cse;

/**
 * <b>Customer</b> 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.5.0
 */

public class Customer {
	private int primaryKey;
	private int personsID;
	private int addressID;
	private String code;
	private String name;
	private char type;
	private String personsCode;
	private Persons humanRep;
	private Address address;
	
	/**
	 * @param code - Accepts a String for the primary indicator for
	 * the company
	 */
	public void setCode(String code) {
		this.code = code.trim();
	}
	
	/**
	 * @param name - Accepts a String
	 */
	public void setName(String name) {
		this.name = name.trim();
	}
	
	public void setType(char type) {
		this.type = type;
	}
	
	/**
	 * @param human - Accepts the <b>Persons</b> instance of the
	 * corresponding id to indicate which person if any owns this
	 * company.
	 */
	public void addHumanRep(Persons human) {
		this.humanRep = human;
	}
	
	/**
	 * @param adr - Accepts the instance of the companies 
	 * <b>Address</b>
	 */
	public void addAddress(Address adr) {
		this.address = adr;
	}
	
	/**
	 * @return - Returns a String that is the primary 
	 * indicator for the company
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @return - Returns a String
	 */
	public String getName() {
		return name;
	}
	
	public char getType() {
		return this.type;
	}
	
	/**
	 * @return - Returns the <b>Persons</b> reference to
	 * the company.
	 */
	public Persons getHumanRep() {
		return this.humanRep;
	}
	
	/**
	 * @return - Returns the <b>Address</b> object for the company
	 */
	public Address getAddress() {
    	return this.address;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public int getPersonsID() {
		return personsID;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public void setPersonsID(int personsID) {
		this.personsID = personsID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getPersonsCode() {
		return personsCode;
	}

	public void setPersonsCode(String personsCode) {
		this.personsCode = personsCode;
	}
}