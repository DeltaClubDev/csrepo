package unl.cse;

/**
 * Creates an Address object by downloading from datarbase
 * @author Linus
 * @author ___________________________________________________________________________________________________
 * @author Alexis Kennedy
 * @author Jacob Charles
 * @version 0.5.0
 */
public class AddressData {
	public DBFactory dbConn = new DBFactory();
	/**
	 * @param addrID
	 * @param countryID
	 * @param stateID
	 * @param cityID
	 * @param zipID
	 * @param streetID
	 * @return
	 */
	public Address createAddress(int addrID, int countryID, int stateID, int cityID, int zipID, int streetID) {
		String queryC = "SELECT Name FROM Country WHERE CountryID = ?";
		String queryS = "SELECT Name FROM State WHERE StateID = ?";
		String queryCit = "SELECT NAME FROM City WHERE CityID = ?";
		String queryZ = "SELECT ZipCode FROM Zip WHERE ZipID = ?";
		String querySt = "SELECT Name FROM Street WHERE StreetID = ?";
		Address a = new Address();
		a.setAddrID(addrID);
		// Set Country
		dbConn.setQuery(queryC);
		dbConn.setIntParam(1, countryID);
		a.setCountry(dbConn.runQuery("Name"));
		a.setCountryID(countryID);
		// Set State
		dbConn.setQuery(queryS);
		dbConn.setIntParam(1, stateID);
		a.setState(dbConn.runQuery("Name"));
		a.setStateID(stateID);
		// Set City
		dbConn.setQuery(queryCit);
		dbConn.setIntParam(1, cityID);
		a.setCity(dbConn.runQuery("Name"));
		a.setCityID(cityID);
		// Set Zip
		dbConn.setQuery(queryZ);
		dbConn.setIntParam(1, zipID);
		a.setZip(dbConn.runQuery("ZipCode"));
		a.setZipID(zipID);
		// Set Street
		dbConn.setQuery(querySt);
		dbConn.setIntParam(1, streetID);
		a.setStreet(dbConn.runQuery("Name"));
		a.setStreetID(streetID);
		return a;
	}
}