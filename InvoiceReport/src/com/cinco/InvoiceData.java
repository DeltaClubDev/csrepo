package com.cinco;

import unl.cse.DBFactory;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @author Dr. Bourke
 * @version 0.5.0
 */
public class InvoiceData {
	public static DBFactory dbConn = new DBFactory();
	public static void closeDB() { dbConn.closeConn(); }

	/**
	 * Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		String queryE = "DELETE FROM Emails";
		String queryC = "UPDATE Company SET PersonsID = ? WHERE PersonsID > ?";
		String queryMI = "UPDATE MainInvoice SET SalesDudeID = ? WHERE SalesDudeID > ?";
		String queryPR = "UPDATE Products SET PersonsID = ? WHERE PersonsID > ?";
		String queryP = "DELETE FROM Persons";
		// Should we delete the address?
		
		//Delete Emails
		dbConn.setQuery(queryE);
		dbConn.runUpdate();
		//Update companies
		dbConn.setQuery(queryC);
		dbConn.setNullParam(1);
		dbConn.setIntParam(2, 0);
		dbConn.runUpdate();
		//Update MainInvoice
		dbConn.setQuery(queryMI);
		dbConn.setNullParam(1);
		dbConn.setIntParam(2, 0);
		dbConn.runUpdate();
		//Update Products
		dbConn.setQuery(queryPR);
		dbConn.setNullParam(1);
		dbConn.setIntParam(2, 0);
		dbConn.runUpdate();
		//Delete Dudes
		dbConn.setQuery(queryP);
		dbConn.runUpdate();
	}

	/**
	 * Removes the person record from the database corresponding to the
	 * provided <code>personCode</code>
	 * @param personCode
	 */
	public static void removePerson(String personCode) {
		int id = getPersonIDOld(personCode);
		if (id == -1) {
			return;
		}
		
		String queryE = "DELETE FROM Emails WHERE PersonsID=?";
		String queryC = "UPDATE Company SET PersonID=? WHERE PersonsID=?";
		String queryMI = "UPDATE MainInvoice SET SalesDudeID=? WHERE PersonsID=?";
		String queryPR = "UPDATE Products SET PersonsID=? WHERE PersonsID=?";
		String queryP = "DELETE FROM Persons WHERE PersonsID=?";
		// Should we delete the address?
		
		//Delete Emails
		dbConn.setQuery(queryE);
		dbConn.setIntParam(1, id);
		dbConn.runUpdate();
		//Update companies
		dbConn.setQuery(queryC);
		dbConn.setNullParam(1);
		dbConn.setIntParam(2, id);
		dbConn.runUpdate();
		//Update MainInvoice
		dbConn.setQuery(queryMI);
		dbConn.setNullParam(1);
		dbConn.setIntParam(2, id);
		dbConn.runUpdate();
		//Update products
		dbConn.setQuery(queryPR);
		dbConn.setNullParam(1);
		dbConn.setIntParam(2, id);
		dbConn.runUpdate();
		//Delete dudes
		dbConn.setQuery(queryP);
		dbConn.setIntParam(1, id);
		dbConn.runUpdate();
	}
	
	/**
	 * Method to add a person record to the database with the provided data. 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, 
			String street, String city, String state, String zip, String country) {
		//Generate an Address
		addCountry(country);
		int countryID = getCountryID(country);
		addState(state, countryID);
		int stateID = getStateID(state, countryID);
		addCity(city, stateID);
		int cityID = getCityID(city, stateID);
		addZip(zip, countryID);
		int zipID = getZipID(zip, countryID);
		addStreet(street, countryID);
		int streetID = getStreetID(street, countryID);
		addAddress(countryID, stateID, cityID, zipID, streetID);
		int addressID = getAddressID(countryID, stateID, cityID, zipID, streetID);
			
		//Generate a Person
		String queryP = "INSERT INTO Persons (PersCode, FirstName, LastName, AddressID)"+
						" VALUES (?, ?, ?, ?)";
		//Insert a Person
		dbConn.setQuery(queryP);
		dbConn.setStringParam(1, personCode);
		dbConn.setStringParam(2, firstName);
		dbConn.setStringParam(3, lastName);
		dbConn.setIntParam(4, addressID);
		dbConn.runUpdate();
	}
	
	/**
	 * Gets a persons ID from old system
	 * @param code
	 * @return - int of Persons ID
	 */
	public static int getPersonIDOld(String code) {
		String queryP = "SELECT PersonsID FROM Persons WHERE PersCode = ?";
		//Match ID
		dbConn.setQuery(queryP);
		dbConn.setStringParam(1, code);
		String id = dbConn.runQuery("PersonsID");
		if (id != null) {
			return Integer.parseInt(id);
		}
		return -1;
	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		int id = getPersonIDOld(personCode);
		if (id == -1) {
			System.out.println("Cannot Add to Email.");
			return;
		}
		String queryE = "INSERT INTO Emails (PersonsID, Email) VALUES (?, ?)";
		dbConn.setQuery(queryE);
		dbConn.setIntParam(1, id);
		dbConn.setStringParam(2, email);
		dbConn.runUpdate();
	}
	
	/**
	 * Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {
		String queryMI = "UPDATE MainInvoice SET CompanyID = ?";
		String queryC = "DELETE FROM Company WHERE CompanyID > ?";
		// Should we delete the address?
		// Set invoice company to null
		dbConn.setQuery(queryMI);
		dbConn.setNullParam(1);
		dbConn.runUpdate();
		// Delete Company
		dbConn.setQuery(queryC);
		dbConn.setIntParam(1, 0);
		dbConn.runUpdate();
	}

	public static void addCustomer(String customerCode, String type, String primaryContactPersonCode, String name, 
			String street, String city, String state, String zip, String country) {
		//Generate an Address
		addCountry(country);
		int countryID = getCountryID(country);
		addState(state, countryID);
		int stateID = getStateID(state, countryID);
		addCity(city, stateID);
		int cityID = getCityID(city, stateID);
		addZip(zip, countryID);
		int zipID = getZipID(zip, countryID);
		addStreet(street, countryID);
		int streetID = getStreetID(street, countryID);
		addAddress(countryID, stateID, cityID, zipID, streetID);
		int addressID = getAddressID(countryID, stateID, cityID, zipID, streetID);
				
		//Generate a company
		int id = getPersonIDOld(primaryContactPersonCode);
		String queryC = "INSERT INTO Company (PersonsID, AddressID, Name, TypeThing, CompCode)"+
						" VALUES (?, ?, ?, ?, ?)";
		dbConn.setQuery(queryC);
		dbConn.setIntParam(1, id);
		dbConn.setIntParam(2, addressID);
		dbConn.setStringParam(3, name);
		dbConn.setStringParam(4, type);
		dbConn.setStringParam(5, customerCode);
		dbConn.runUpdate();
	}

	/**
	 * Removes all product records from the database
	 */
	public static void removeAllProducts() {
		removeAllInvoices();
		String queryPR = "DELETE FROM Products WHERE ProductID > ?";
		dbConn.setQuery(queryPR);
		dbConn.setIntParam(1, 0);
		dbConn.runUpdate();
	}

	/**
	 * Removes a particular product record from the database corresponding to the
	 * provided <code>productCode</code>
	 * @param assetCode
	 */
	public static void removeProduct(String productCode) {
		int id = getProductIDOld(productCode);
		if (id != -1) {
			return;
		}
		String queryI = "DELETE FROM Invoice WHERE ProductID = ?";
		String queryPR = "DELETE FROM Products WHERE ProductID = ?";
		//Delete Associated Invoices
		dbConn.setQuery(queryI);
		dbConn.setIntParam(1, id);
		dbConn.runUpdate();
		//Delete Product
		dbConn.setQuery(queryPR);
		dbConn.setIntParam(1, id);
		dbConn.runUpdate();
	}
	
	/**
	 * Fetches productID by old system code
	 * @param code
	 * @return
	 */
	public static int getProductIDOld(String code) {
		String queryPR = "SELECT ProductID FROM Products WHERE ProdCode=?";
		//Fetch ProductsID
		dbConn.setQuery(queryPR);
		dbConn.setStringParam(1, code);
		String id = dbConn.runQuery("ProductID");
		if (id != null) {
			return Integer.parseInt(id);
		}
		return -1;
	}

	/**
	 * Adds an equipment record to the database with the
	 * provided data.  
	 */
	public static void addEquipment(String productCode, String name, Double pricePerUnit) {
		String queryP = "INSERT INTO Products (TypeThing, ProdCode, UnitMoneh, Name) "+
				"VALUES (?, ?, ?, ?)";
		dbConn.setQuery(queryP);
		dbConn.setStringParam(1, "E");				//Type
		dbConn.setStringParam(2, productCode);		//ProductCode
		dbConn.setDoubleParam(3, pricePerUnit);		//UnitCode
		dbConn.setStringParam(4, name);				//Name
		dbConn.runUpdate();
	}
	
	/**
	 * Adds an license record to the database with the
	 * provided data.  
	 */
	public static void addLicense(String productCode, String name, double serviceFee, double annualFee) {
		String queryP = "INSERT INTO Products (TypeThing, ProdCode, Name, Fee, AnnualingCostings) "+
				"VALUES (?, ?, ?, ?, ?)";
		dbConn.setQuery(queryP);
		dbConn.setStringParam(1, "L");				//Type
		dbConn.setStringParam(2, productCode);		//ProductCode
		dbConn.setStringParam(3, name);				//Name
		dbConn.setDoubleParam(4, serviceFee);		//Fee
		dbConn.setDoubleParam(5, annualFee);		//AnnualingCostings
		dbConn.runUpdate();
	}

	/**
	 * Adds an consultation record to the database with the
	 * provided data.  
	 */
	public static void addConsultation(String productCode, String name, String consultantPersonCode, Double hourlyFee) {
		int id = getPersonIDOld(consultantPersonCode);
		String queryP = "INSERT INTO Products (TypeThing, ProdCode, PersonsID, HourMoneh, Name) "+
				"VALUES (?, ?, ?, ?, ?)";
		dbConn.setQuery(queryP);
		dbConn.setStringParam(1, "C");				//Type
		dbConn.setStringParam(2, productCode);		//ProductCode
		dbConn.setIntParam(3, id);					//PersonID
		dbConn.setDoubleParam(4, hourlyFee);		//HourMoneh
		dbConn.setStringParam(5, name);				//Name
		dbConn.runUpdate();
	}
	
	/**
	 * Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		String queryI = "DELETE FROM Invoice WHERE MainInvoiceID > ?";
		String queryMI = "DELETE FROM MainInvoice WHERE MainInvoiceID > ?";
		//Delete Invoices
		dbConn.setQuery(queryI);
		dbConn.setIntParam(1, 0);
		dbConn.runUpdate();
		//Delete MainInvoices
		dbConn.setQuery(queryMI);
		dbConn.setIntParam(1, 0);
		dbConn.runUpdate();
	}
	
	/**
	 * Removes the invoice record from the database corresponding to the
	 * provided <code>invoiceCode</code>
	 * @param invoiceCode
	 */
	public static void removeInvoice(String invoiceCode) {
		int id = getMInvoiceIDOld(invoiceCode);
		if (id == -1) {
			System.out.println("Cannot remove invoice.");
			return;
		}
		String queryI = "DELETE FROM Invoice WHERE MainInvoiceID=?";
		String queryMI = "DELETE FROM MainInvoice WHERE MainInvoiceID=?";		
		//Remove Invoice
		dbConn.setQuery(queryI);
		dbConn.setIntParam(1, id);
		dbConn.runUpdate();
		//Remove MainInvoice
		dbConn.setQuery(queryMI);
		dbConn.setIntParam(1, id);
		dbConn.runUpdate();
	}
		
	public static int getMInvoiceIDOld(String code) {
		String queryMI = "SELECT MainInvoiceID FROM MainInvoice WHERE InvoiceCode=?";
		//Fetch MainInvoiceID
		dbConn.setQuery(queryMI);
		dbConn.setStringParam(1, code);
		String id = dbConn.runQuery("MainInvoiceID");
		if (id != null) {
			return Integer.parseInt(id);
		}
		return -1;
	}
	
	/**
	 * Adds an invoice record to the database with the given data.  
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode) {
		int cID = getCustomerIDOld(customerCode);
		int pID = getPersonIDOld(salesPersonCode);
		if((cID == -1) || (pID == -1)) {
			return;
		}
		String queryI = "INSERT INTO MainInvoice (InvoiceCode, CompanyID, SalesDudeID) "
				+"VALUES (?, ?, ?)";
		dbConn.setQuery(queryI);
		dbConn.setStringParam(1, invoiceCode);
		dbConn.setIntParam(2, cID);
		dbConn.setIntParam(3, pID);
		dbConn.runUpdate();
	}	
	
	public static int getCustomerIDOld(String code) {
		String queryC = "SELECT CompanyID FROM Company WHERE CompCode=?";
		//Fetch Company
		dbConn.setQuery(queryC);
		dbConn.setStringParam(1, code);
		String id = dbConn.runQuery("CompanyID");
		if (id != null) {
			return Integer.parseInt(id);
		}
		return -1;
	}
	
	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of units
	 */
	public static void addEquipmentToInvoice(String invoiceCode, String productCode, int numUnits) {
		int mID = getMInvoiceIDOld(invoiceCode);
		int pID = getProductIDOld(productCode);
		if((mID == -1) || (pID == -1)) {
			return;
		}
		if(numUnits < 0) {
			System.out.println("You're a dumbass."); 	// @author anonymous
			return;
		}
		
		String queryI = "INSERT INTO Invoice (ProductID, NumUnits, MainInvoiceID)"
				+ " VALUES (?, ?, ?)";
		dbConn.setQuery(queryI);
		dbConn.setIntParam(1, pID);				//ProductID
		dbConn.setIntParam(2, numUnits);		//NumUnits
		dbConn.setIntParam(3, mID);				//MainInvoiceID
		dbConn.runUpdate();
	}
	
	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * begin/end dates
	 */
	public static void addLicenseToInvoice(String invoiceCode, String productCode, String startDate, String endDate) {
		int mID = getMInvoiceIDOld(invoiceCode);
		int pID = getProductIDOld(productCode);
		if((mID == -1) || (pID == -1)) {
			return;
		}
		String queryL = "INSERT INTO Invoice (ProductID, StartDate, EndDate, MainInvoiceID) "+
				"VALUES (?,?,?,?)";
		dbConn.setQuery(queryL);
		dbConn.setIntParam(1, pID);
		dbConn.setStringParam(2, startDate);
		dbConn.setStringParam(3, endDate);
		dbConn.setIntParam(4, mID);
		dbConn.runUpdate();
	}

	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of billable hours.
	 */
	public static void addConsultationToInvoice(String invoiceCode, String productCode, double numHours) {
		int mID = getMInvoiceIDOld(invoiceCode);
		int pID = getProductIDOld(productCode);
		if((mID == -1) || (pID == -1)) {
			return;
		}
		String queryC = "INSERT INTO Invoice (ProductID, NumHours, MainInvoiceID) "+
				"VALUES (?,?,?)";
		dbConn.setQuery(queryC);
		dbConn.setIntParam(1, pID);
		dbConn.setDoubleParam(2, numHours);
		dbConn.setIntParam(3, mID);
		dbConn.runUpdate();
	}
	
	/**
	 * Adds a country if not exists
	 * @param name
	 * @return - boolean true if success
	 */
	public static void addCountry(String name) {
		int id = getCountryID(name);
		if (id != -1) {
			return;
		}
		String queryC = "INSERT INTO Country (Abrv, Name) VALUES (?, ?)";
		// Spawn a Country
		dbConn.setQuery(queryC);
		dbConn.setNullParam(1);
		dbConn.setStringParam(2, name);
		dbConn.runUpdate();
	}
	
	/**
	 * Fetches country ID by Name
	 * @param country
	 * @return - int of ID
	 */
	private static int getCountryID(String country) {
		String queryC = "SELECT CountryID FROM Country WHERE Name=?";
		//Fetch CountryID
		dbConn.setQuery(queryC);
		dbConn.setStringParam(1, country);
		String id = dbConn.runQuery("CountryID");
		if (id != null) {
			return Integer.parseInt(id);
		}
		return -1;
	}
	
	/**
	 * Adds a state in a country if not exists
	 * @param name
	 * @param countryID
	 * @return - boolean true if success
	 */
	public static void addState(String name, int countryID) {
		int id = getStateID(name, countryID);
		if (id != -1) {
			return;
		}
		String queryS = "INSERT INTO State (Abrv, Name, CountryID) VALUES (?, ?, ?)";
		// Spawn a State
		dbConn.setQuery(queryS);
		dbConn.setNullParam(1);
		dbConn.setStringParam(2, name);
		dbConn.setIntParam(3, countryID);
		dbConn.runUpdate();
	}
	
	/**
	 * Fetches a state by name and country
	 * @param state
	 * @param countryID
	 * @return - int of ID
	 */
	private static int getStateID(String state, int countryID) {
		String queryS = "SELECT StateID FROM State WHERE Name=? AND CountryID=?";
		//Fetch StateID
		dbConn.setQuery(queryS);
		dbConn.setStringParam(1, state);
		dbConn.setIntParam(2, countryID);
		String id = dbConn.runQuery("StateID");
		if (id != null) {
			return Integer.parseInt(id);
		}
		return -1;
	}
	
	/**
	 * Adds a City by Name if exists in a State
	 * @param name
	 * @param stateID
	 * @return - boolean true if success
	 */
	public static void addCity(String name, int stateID) {
		int id = getCityID(name, stateID);
		if (id != -1) {
			return;
		}
		String queryC = "INSERT INTO City (Name, StateID) VALUES (?, ?)";
		// Spawn a City
		dbConn.setQuery(queryC);
		dbConn.setStringParam(1, name);
		dbConn.setIntParam(2, stateID);
		dbConn.runUpdate();
	}
	
	/**
	 * Fetches City ID by Name from a state
	 * @param city
	 * @return - int of ID
	 */
	private static int getCityID(String city, int stateID) {
		String queryC = "SELECT CityID FROM City WHERE Name=? AND StateID=?";
		//Fetch CountryID
		dbConn.setQuery(queryC);
		dbConn.setStringParam(1, city);
		dbConn.setIntParam(2, stateID);
		String id = dbConn.runQuery("CityID");
		if (id != null) {
			return Integer.parseInt(id);
		} return -1;
	}
	
	/**
	 * Adds a zipcode if not exists in a country
	 * @param zip
	 * @param countryID
	 * @return - boolean true if added
	 */
	public static void addZip(String zip, int countryID) {
		int id = getZipID(zip, countryID);
		if (id != -1) {
			return;
		}
		String queryZ = "INSERT INTO Zip (ZipCode, CountryID) VALUES (?, ?)";
		// Spawn a Zip code
		dbConn.setQuery(queryZ);
		dbConn.setStringParam(1, zip);
		dbConn.setIntParam(2, countryID);
		dbConn.runUpdate();
	}
	
	/**
	 * Fetches zip code ID by code
	 * @param zip
	 * @return - int of zipcode ID
	 */
	private static int getZipID(String zip, int countryID) {
		String queryZ = "SELECT ZipID FROM Zip WHERE ZipCode=? AND CountryID=?";
		//Fetch ZipID
		dbConn.setQuery(queryZ);
		dbConn.setStringParam(1, zip);
		dbConn.setIntParam(2, countryID);
		String id = dbConn.runQuery("ZipID");
		if (id != null) {
			return Integer.parseInt(id);
		} return -1;
	}
	
	/**
	 * Adds a street to a country if not exists
	 * @param name
	 * @param countryID
	 * @return - boolean true if success
	 */
	public static void addStreet(String name, int countryID) {
		int id = getStreetID(name, countryID);
		if (id != -1) {
			return;
		}
		String queryS = "INSERT INTO Street (Name, CountryID) VALUES (?, ?)";
		// Spawn a Street
		dbConn.setQuery(queryS);
		dbConn.setStringParam(1, name);
		dbConn.setIntParam(2, countryID);
		dbConn.runUpdate();
	}
	
	/**
	 * Fetches street ID by street name in a country
	 * @param street
	 * @return - int of street ID
	 */
	private static int getStreetID(String street, int countryID) {
		String queryS = "SELECT StreetID FROM Street WHERE Name=? AND CountryID=?";
		//Fetch StreetID
		dbConn.setQuery(queryS);
		dbConn.setStringParam(1, street);
		dbConn.setIntParam(2, countryID);
		String id = dbConn.runQuery("StreetID");
		if (id != null) {
			return Integer.parseInt(id);
		} return -1;
	}
	
	/**
	 * Adds an address if not exists
	 * @param countryID
	 * @param stateID
	 * @param cityID
	 * @param zipID
	 * @param streetID
	 * @return - boolean true if success
	 */
	public static void addAddress(int countryID, int stateID, int cityID, int zipID, int streetID) {
		int id = getAddressID(countryID, stateID, cityID, zipID, streetID);
		if (id != -1) {
			return;
		}
		String queryA = "INSERT INTO Address (CountryID, StateID, CityID, ZipID, StreetID)"+
				" VALUES (?, ?, ?, ?, ?)";
		// Spawn an Address
		dbConn.setQuery(queryA);
		dbConn.setIntParam(1, countryID);
		dbConn.setIntParam(2, stateID);
		dbConn.setIntParam(3, cityID);
		dbConn.setIntParam(4, zipID);
		dbConn.setIntParam(5, streetID);
		dbConn.runUpdate();
	}
	
	/**
	 * Gets an Address ID by valid address information
	 * @param countryID
	 * @param stateID
	 * @param cityID
	 * @param zipID
	 * @param streetID
	 * @return - int of Address ID
	 */
	public static int getAddressID(int countryID, int stateID, int cityID, int zipID, int streetID) {
		String queryA = "SELECT AddressID FROM Address WHERE CountryID=? AND StateID=? AND CityID=?"+
				" AND ZipID=? AND StreetID=?";
		//Fetch AddressID
		dbConn.setQuery(queryA);
		dbConn.setIntParam(1, countryID);
		dbConn.setIntParam(2, stateID);
		dbConn.setIntParam(3, cityID);
		dbConn.setIntParam(4, zipID);
		dbConn.setIntParam(5, streetID);
		String id = dbConn.runQuery("AddressID");
		if (id != null) {
			return Integer.parseInt(id);
		} return -1;
	}
}