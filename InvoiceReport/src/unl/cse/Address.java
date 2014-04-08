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
	
	public int getAddrID() {
		return addrID;
	}

	public void setAddrID(int addrID) {
		this.addrID = addrID;
	}

	/**
	 * 
	 * @param street - Accepts a String
	 */
	public void setStreet(String street) {
		
		if ((street != null)  && (!street.trim().isEmpty())) {
			this.street = street.trim();
		} else {
			this.street = "N/A";
		}
	}

	/**
	 * 
	 * @param city - Accepts a String
	 */
	public void setCity(String city) {
		
		if ((city != null) && (!city.trim().isEmpty())) {
			this.city = city.trim();
		} else {
			this.city = "N/A";
		}
	}

	/**
	 * 
	 * @param state - Accepts a String
	 */
	public void setState(String state) {
		
		if ((state != null) && (!state.trim().isEmpty())) {
			this.state = state.trim();
		} else {
			this.state = "N/A";
		}
	}

	/**
	 * 
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

	/**
	 * 
	 * @param country - Accepts a String
	 */
	public void setCountry(String country) {

		if ((country != null) && (!country.trim().isEmpty())) {
			this.country = country.trim();
		} else {
			this.country = "N/A";
		}
	}

	/**
	 * 
	 * @return - Returns a String
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * 
	 * @return - Returns a String
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @return - Returns a String
	 */
	public String getState() {
		return state;
	}

	/**
	 * 
	 * @return - Returns a String
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * 
	 * @return - Returns a String
	 */
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