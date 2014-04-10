package unl.cse;

/**
 * The <b>Address</b> class was designed to be used for multiple types
 * of objects. This is because Companies, and People will have addresses.
 * All values in this Object run risks of being null!
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.5.0
 */
public class Address {
	private int addrID;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
	private int streetID;
	private int cityID;
	private int stateID;
	private int zipID;
	private int countryID;
	
	public Address() { }
	
	/**
	 * returns the primary key of an Address from the database
	 * @return - Primary Key (int)
	 */
	public int getAddrID() {
		return addrID;
	}

	/**
	 * Sets the primary key
	 * @param addrID - (int)
	 */
	public void setAddrID(int addrID) {
		this.addrID = addrID;
	}

	public void setStreet(String street) {
		if ((street != null)  && (!street.trim().isEmpty())) {
			this.street = street.trim();
		} else {
			this.street = "N/A";
		}
	}

	public void setCity(String city) {
		if ((city != null) && (!city.trim().isEmpty())) {
			this.city = city.trim();
		} else {
			this.city = "N/A";
		}
	}

	public void setState(String state) {
		if ((state != null) && (!state.trim().isEmpty())) {
			this.state = state.trim();
		} else {
			this.state = "N/A";
		}
	}

	/**
	 * Sets the zip code
	 * @param zip - Accepts a String, please leave it as a String. Zip codes
	 * can also contain "-" 
	 */
	public void setZip(String zip) {
		if ((zip != null) && (!zip.trim().isEmpty())) {
			this.zip = zip.trim();
		} else {
			this.zip = "N/A";
		}
	}

	public void setCountry(String country) {

		if ((country != null) && (!country.trim().isEmpty())) {
			this.country = country.trim();
		} else {
			this.country = "N/A";
		}
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

	public int getStreetID() {
		return streetID;
	}

	public int getCityID() {
		return cityID;
	}

	public int getStateID() {
		return stateID;
	}

	public int getZipID() {
		return zipID;
	}

	public int getCountryID() {
		return countryID;
	}

	public void setStreetID(int streetID) {
		this.streetID = streetID;
	}

	public void setCityID(int cityID) {
		this.cityID = cityID;
	}

	public void setStateID(int stateID) {
		this.stateID = stateID;
	}

	public void setZipID(int zipID) {
		this.zipID = zipID;
	}

	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}
}