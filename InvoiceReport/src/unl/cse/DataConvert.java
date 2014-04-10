package unl.cse;

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <b>DataConvert</b> is currently the main class.
 * The functions provided here will parse old data, and
 * convert them into Json (XML was depreciated in 
 * version 0.5.0. Also, Invoices are processed here as 
 * the Invoices data is still considered data 
 * converting.
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.5.0
 */

public class DataConvert {
	
	private final String PERSONS_DATA_FILE = "data/Persons.dat";
	private final String CUSTOMERS_DATA_FILE = "data/Customers.dat";
	private final String PRODUCTS_DATA_FILE = "data/Products.dat";
	private final String INVOICES_DATA_FILE = "data/Invoices.dat";
	private final String PERSONS_JSON_FILE = "data/Persons.json";
	private final String PRODUCTS_JSON_FILE = "data/Products.json";
	private final String CUSTOMERS_JSON_FILE = "data/Customers.json";
	private static PersonsHub phub = new PersonsHub();
	private static ProductsHub prhub = new ProductsHub();
	private static CustomersHub chub = new CustomersHub();
	private static InvoicesHub ihub = new InvoicesHub();

	/**
	 * <b>DataConvert</b> constructor requires functionality parameters
	 * that determines which functions to use.
	 * 
	 * @param genXML - If true, will generate an XML file
	 * @param jsonOut - If true, will output JSON in the console
	 * @param genJson - If true, will generate an JSON file
	 * @param genTxt - Creates an invoice output
	 * @param dbDownload - Downloads objects from data base and creates objects
	 * @param verbose - Used to see exact data (for debugging)
	 */
	public DataConvert(boolean jsonOut, boolean genJson, boolean genTxt, boolean dbDownload, boolean verbose) {
		
		if (dbDownload == false) {
			System.out.println("Parsing Persons data file...");
			parsePersonsDat();
			System.out.println("Parsing Customers data file...");
			parseCustomersDat();
			System.out.println("Parsing Product data file...");
			parseProductsDat();
			System.out.println("Parsing Invoice data file...");
			parseInvoicesDat();
		} else {
			// Persons Download
			System.out.println("\n***** Adding Persons to main collection... *****");
			PersonsData pd = new PersonsData();
			pd.downloadPersons();
			for (Persons p : pd.getPersons().getCollection()) {
				phub.addPersons(p);
				if (verbose == true) {
					System.out.println("Primary Key: "+p.getPrimaryKey());
					System.out.println("Code: "+p.getId());
					System.out.println("Firstname: "+p.getFirstName());
					System.out.println("Lastname: "+p.getLastName());
					for (String e : p.getEmails()) {
						System.out.println("Email: "+e);
					}
					System.out.println("AddressID: "+p.getAddressID());
					System.out.println("Street ID: "+p.getAddress().getStreetID()+"\tStreet: "+p.getAddress().getStreet());
					System.out.println("Zip ID: "+p.getAddress().getZipID()+"\tZip: "+p.getAddress().getZip());
					System.out.println("City ID: "+p.getAddress().getCityID()+"\tCity: "+p.getAddress().getCity());
					System.out.println("State ID: "+p.getAddress().getStateID()+"\tState: "+p.getAddress().getState());
					System.out.println("Country ID: "+p.getAddress().getCountryID()+"\tCountry: "+p.getAddress().getCountry());
					System.out.print("\n");
				}
			}
			
			// Customers Download
			System.out.println("\n***** Adding Customers to main collection... *****");
			CustomerData cd = new CustomerData();
			cd.downloadCustomers(phub);
			for (Customer c : cd.getCustomers().getCustomerList()) {
				chub.addCustomer(c);
				if (verbose == true) {
					System.out.println("Primary Key: "+c.getPrimaryKey());
					System.out.println("Persons ID: "+c.getHumanRep().getPrimaryKey());
					System.out.println("Name: "+c.getName());
					System.out.println("Type: "+c.getType());
					System.out.println("Code: "+c.getCode());
					System.out.println("AddressID: "+c.getAddressID());
					System.out.println("Street ID: "+c.getAddress().getStreetID()+"\tStreet: "+c.getAddress().getStreet());
					System.out.println("Zip ID: "+c.getAddress().getZipID()+"\tZip: "+c.getAddress().getZip());
					System.out.println("City ID: "+c.getAddress().getCityID()+"\tCity: "+c.getAddress().getCity());
					System.out.println("State ID: "+c.getAddress().getStateID()+"\tState: "+c.getAddress().getState());
					System.out.println("Country ID: "+c.getAddress().getCountryID()+"\tCountry: "+c.getAddress().getCountry());
					System.out.print("\n");
				}
			}
			
			// Products Download
			System.out.println("\n***** Adding Products to main collection... *****");
			ProductData prd = new ProductData();
			prd.downloadProducts(phub);
			for (Equipment e : prd.getProducts().getEquipList()) {
				prhub.addEquipment(e);
				if (verbose == true) {
					System.out.println("Primary Key: "+e.getPrimaryKey());
					System.out.println("Code: "+e.getCode());
					System.out.println("Name: "+e.getName());
					System.out.println("UnitMoneh: "+e.getUnitPrice());
					System.out.print("\n");
				}
			}
			for (License l : prd.getProducts().getLiceList()) {
				prhub.addLicense(l);
				if (verbose == true) {
					System.out.println("Primary Key: "+l.getPrimaryKey());
					System.out.println("Code: "+l.getCode());
					System.out.println("Name: "+l.getName());
					System.out.println("Price: "+l.getPrice());
					System.out.println("Annual Price: "+l.getAnnualPrice());
					System.out.print("\n");
				}
			}
			for (Consultation c : prd.getProducts().getConsultList()) {
				prhub.addConsutation(c);
				if (verbose == true) {
					System.out.println("Primary Key: "+c.getPrimaryKey());
					System.out.println("Code: "+c.getCode());
					System.out.println("Name: "+c.getName());
					System.out.println("PersonID: "+c.getPersonsID()+"\t == PersonID: "+c.getHumanRep().getPrimaryKey());
					System.out.println("Hour Fee: "+c.getHourPrice());
					System.out.print("\n");
				}
			}
			
			// Invoice Download
			System.out.println("\n***** Adding Invoices to main collection... *****");
			MainInvoiceData mid = new MainInvoiceData();
			mid.downloadInvoices(phub, prhub, chub);
			for (Invoices i : mid.getInvoices().getCollection()) {
				ihub.addInvoices(i);
				if (verbose == true) {
					System.out.println("Primary Key: "+i.getPrimaryKey());
					System.out.println("Code :"+i.getCode());
					System.out.println("Comply Fee: "+i.getHasComplyFee());
					System.out.println("SalesMan ID: "+i.getPersonID());
					System.out.println("Customer ID: "+i.getCustomerID());
					for (Equipment e : i.getProducts().getEquipList()) {
						System.out.println("Invoice for Equipment:");
						System.out.println("Primary Key: "+e.getPrimaryKey());
						System.out.println("Code: "+e.getCode());
						System.out.println("Name: "+e.getName());
						System.out.println("UnitMoneh: "+e.getUnitPrice());
						System.out.println("Number of Units: "+e.getInvUnits());
						System.out.print("\n");
					}
					for (License l : i.getProducts().getLiceList()) {
						System.out.println("Invoice for License:");
						System.out.println("Primary Key: "+l.getPrimaryKey());
						System.out.println("Code: "+l.getCode());
						System.out.println("Name: "+l.getName());
						System.out.println("Price: "+l.getPrice());
						System.out.println("Annual Price: "+l.getAnnualPrice());
						System.out.println("Start Date: "+l.getStartDate());
						System.out.println("End Date: "+l.getEndDate());
						System.out.print("\n");
					}
					for (Consultation c : i.getProducts().getConsultList()) {
						System.out.println("Invoice for Consultation:");
						System.out.println("Primary Key: "+c.getPrimaryKey());
						System.out.println("Code: "+c.getCode());
						System.out.println("Name: "+c.getName());
						System.out.println("PersonID: "+c.getPersonsID());
						System.out.println("Hour Fee: "+c.getHourPrice());
						System.out.println("Hours: "+c.getHours());
						System.out.print("\n");
					}
				}
			}
		}
			
		if (genJson == true) {
			System.out.println("\n**** Converting data files into JSON ****");
			parsePersonsJson(jsonOut);
			parseCustomersJson(jsonOut);
			parseProductsJson(jsonOut);
			System.out.println("Conversion complete");
		}
		
		if (genTxt == true) {
			System.out.println("\n**** Generating Invoices ****");
			new InvoiceOutput(ihub);
			System.out.println("Conversion complete");
		}
	}

	/**
	 * <i>parsePersonsJson</i> will generate an JSON file using the parsed
	 * data from the old system.
	 * 
	 * @param jsonOut - If true, the JSON file will be displayed in the console
	 */
	private void parsePersonsJson(boolean jsonOut) {
		
		System.out.println("Generating JSON file: " + PERSONS_JSON_FILE +"\n");
	    GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.setPrettyPrinting().disableHtmlEscaping();
	    Gson gson = gsonBuilder.create();
	    String json = gson.toJson(phub);
		
	    if (jsonOut == true) {
	    	System.out.println(json);
	    }
	    
	    FileWriter f = null;
		try {
			f = new FileWriter(PERSONS_JSON_FILE);
			f.write(json);
			f.flush();
			f.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * <i>parsePersonsDat</i> will take the data from the old system
	 * and generate an instance of a person based on the business model
	 */
	public void parsePersonsDat() {
		
		Scanner s = null;
		try {
			s = new Scanner(new File(PERSONS_DATA_FILE));
		} catch (Exception e) {
			e.printStackTrace();
		}

		String line = null;
		try {
			while (!(line = s.nextLine()).isEmpty()) {
				String tokens[] = line.split(";");
				String code = tokens[0];

				if (tokens.length > 2) {
					String[] names = tokens[1].split(",");
					String[] address = tokens[2].split(",");
					String[] derp = { "N/A" };
					String[] emails = (tokens.length == 4) ? tokens[3]
							.split(",") : derp;
					Persons dudes = new Persons();
					dudes.setId(code);
					dudes.setFirstName(names[0]);
					dudes.setLastName(names[1]);
					dudes.setEmails(emails);
					Address adr = new Address();
					adr.setStreet(address[0]);
					adr.setCity(address[1]);
					adr.setState(address[2]);
					adr.setZip(address[3]);
					adr.setCountry(address[4]);
					dudes.setAddress(adr);
					phub.addPersons(dudes);
				}
			}
		} catch (Exception e) {

		}
	}

	/**
	 * <i>parseCustomersJson</i> will generate an JSON file using the parsed
	 * data from the old system.
	 * @param jsonOut - If true, the JSON file will be displayed in the console
	 */
	public void parseCustomersJson(boolean jsonOut) {
		
		System.out.println("Generating JSON file: " + CUSTOMERS_JSON_FILE +"\n");
		GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.setPrettyPrinting().disableHtmlEscaping();
	    Gson gson = gsonBuilder.create();
	    String json = gson.toJson(chub);
		
	    if (jsonOut == true) {
	    	System.out.println(json);
	    }
	    
	    FileWriter f = null;
		try {
			f = new FileWriter(CUSTOMERS_JSON_FILE);
			f.write(json);
			f.flush();
			f.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * <i>parseCustomerDat</i> will parse all the data from the old
	 * system and create Customer objects from the business model.
	 */
	public void parseCustomersDat() {
		Scanner s = null;
		try {
			s = new Scanner(new File(CUSTOMERS_DATA_FILE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		s.nextLine();
		String line = null;
		try {
			while (!(line = s.nextLine()).isEmpty()) {
				String tokens[] = line.split(";");

				if (tokens.length > 4) {
					String code = tokens[0];
					String type = tokens[1];
					String id = tokens[2];
					String name = tokens[3];
					String[] address = tokens[4].split(",");
					
					Customer c = new Customer();
					if (type.equals("G") || type.equals("g")) {
						c.setType('G');
					} else if (type.equals("C") || type.equals("c")) {
						c.setType('C');
					} else {
						System.out.println("Error: Invalid Company Type for: "+ name);
						return;
					}
					
					c.setCode(code);
					c.setName(name);
					Address adr = new Address();
					adr.setStreet(address[0]);
					adr.setCity(address[1]);
					adr.setState(address[2]);
					adr.setZip(address[3]);
					adr.setCountry(address[4]);
					c.addAddress(adr);
					if (phub.isThere(id) == true) {
						c.addHumanRep(phub.getPersonInfo(id));
					}
					chub.addCustomer(c);
				}				
			}
		} catch (Exception e) {

		}
	}

	/**
	 * <i>parseProductsJson</i> will generate an JSON file using the parsed
	 * data from the old system.
	 * 
	 * @param jsonOut - If true, the JSON file will be displayed in the console
	 */
	public void parseProductsJson(boolean jsonOut) {
		
		System.out.println("Generating JSON file: " + PERSONS_JSON_FILE +"\n");
		GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.setPrettyPrinting().disableHtmlEscaping();
	    Gson gson = gsonBuilder.create();
	    String json = gson.toJson(prhub);
		
	    if (jsonOut == true) {
	    	System.out.println(json);
	    }
	    
	    FileWriter f = null;
		try {
			f = new FileWriter(PRODUCTS_JSON_FILE);
			f.write(json);
			f.flush();
			f.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * <i>parseProductsDat</i> will read the data file from the old
	 * system and create instances of all the products from the
	 * business model.
	 */
	public void parseProductsDat() {
		Scanner s = null;
		try {
			s = new Scanner(new File(PRODUCTS_DATA_FILE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		s.nextLine();
		String line = null;
		try {
			while (!(line = s.nextLine()).isEmpty()) {
				String tokens[] = line.split(";");

				if (tokens.length > 3) {
					String code = tokens[0];
					String type = tokens[1];
					String name = tokens[2];
					String valueOne = tokens[3];
					String valueTwo = (tokens.length == 5) ? tokens[4] : null;

					if (type.equals("E") || type.equals("e")) {
						Equipment e = new Equipment();
						e.setCode(code);
						e.setName(name);
						e.setUnitPrice(valueOne);
						prhub.addEquipment(e);

					} else if (type.equals("L") || type.equals("l")) {
						License l = new License();
						l.setCode(code);
						l.setName(name);
						l.setPrice(valueOne);
						l.setAnnualPrice(valueTwo);
						prhub.addLicense(l);

					} else if (type.equals("C") || type.equals("c")) {
						Consultation c = new Consultation();
						c.setCode(code);
						c.setName(name);
						c.setHourPrice(valueTwo);
						if (phub.isThere(valueOne) == true) {
							c.addHumanRep(phub.getPersonInfo(valueOne));
						}
						prhub.addConsutation(c);
					} else {
						System.out.println("Error: Invalid Product Type for: "
								+ name);
					}
				}
			}
		} catch (Exception e) {

		}
	}
	
	/**
	 * <i>parsePersonsDat</i> will take the data from the old system
	 * and generate an instance of an <b>Invoice</b> based on the business model
	 */
	public void parseInvoicesDat() {
		Scanner s = null;
		try {
			s = new Scanner(new File(INVOICES_DATA_FILE));
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.nextLine();
		String line = null;
		try {
			while (!(line = s.nextLine()).isEmpty()) {
				String tokens[] = line.split(";");
				String inCode = tokens[0]; //invoiceCode
				String customCode = tokens[1]; //customers code
				String salesCode = tokens[2]; //salesCode
				String[] prodString = tokens[3].split(",");
				List<String> prodList = Arrays.asList(prodString);
				
				Invoices i = new Invoices();
				i.setCode(inCode);
				i.setCustomCode(customCode);
				i.setSalesCode(salesCode);
				if (chub.isThere(customCode) == true) {
					i.setType(chub.getCustomerByCode(customCode).getType());
					i.setCustomer(chub.getCustomerByCode(customCode));
				}
				if ((i.getType() == 'G') || (i.getType() == 'g')) {
					i.setHasComplyFee(true);
				} else {
					i.setHasComplyFee(false);
				}
				
				if (phub.isThere(salesCode) == true) {
					i.setPerson(phub.getPersonInfo(salesCode));
				}
				
				for (String prods : prodList) {
					List<String> info = Arrays.asList(prods.split(":"));
					if(prhub.equipIsThere(info.get(0))) { // Is a equipment
						i.setEquipment(prhub.getEquipmentById(info.get(0)), info.get(1), -1);
					} else if (prhub.liceIsThere(info.get(0))) { // is License
						i.setLicense(prhub.getLicenseById(info.get(0)), info.get(1), info.get(2), -1);
					} else if (prhub.consultIsThere(info.get(0))) { // is Consultant {
						i.setConsultant(prhub.getConsultById(info.get(0)), info.get(1), -1);
					}
				} ihub.addInvoices(i);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Main driver class
	 * @param args - This parameter is not used
	 */
	public static void main(String[] args) {

		boolean JSON_CONSOLE_OUTPUT = false;
		boolean JSON_GENERATE_DATA = false;
		boolean TXT_GENERATE_DATA = true;
		boolean DB_DOWNLOAD_DATA = true;
		boolean VERBOSE = false;
		
		new DataConvert(JSON_CONSOLE_OUTPUT, JSON_GENERATE_DATA, TXT_GENERATE_DATA, DB_DOWNLOAD_DATA, VERBOSE);
		// Test Data
//		ClientDerp derp = new ClientDerp(phub, prhub, chub, ihub);
//		derp.uploadPeople();
//		derp.uploadCustomers();
//		derp.uploadProducts();
//		derp.uploadInvoices();
//		derp.stahp();
	}
}