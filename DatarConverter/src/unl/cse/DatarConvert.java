package unl.cse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class DatarConvert{
	private final String PERSONS_DATA_FILE = "datar/Persons.dat";
	private final String CUSTOMER_DATA_FILE = "datar/Customers.dat";
	private final String PRODUCTS_DATA_FILE = "datar/Products.dat";
	private final String PERSONS_XML_FILE = "datar/Persons.xml";
	private final String CUSTOMER_XML_FILE = "datar/Customers.xml";
	private final String PRODUCTS_XML_FILE = "datar/Products.xml";
	private final String PERSONS_JSON_FILE = "datar/Persons.json";
	private final String PRODUCTS_JSON_FILE = "datar/Products.json";
	private final String CUSTOMERS_JSON_FILE = "datar/Customers.json";
	private PersonsHub phub = new PersonsHub();
	private ProductsHub prhub = new ProductsHub();
	private CustomersHub chub = new CustomersHub();
	/*private JsonObject derps = new JsonObject();
	private Gson jsonAdd = new Gson();
	private Gson jsonProd = new Gson();
	private Gson jsonGov = new Gson();*/
	
	public DatarConvert(boolean xmlOut, boolean genXML, boolean genJson) {

		parsePersonsDat();
		parseCustomersDat();
		parseProductDat();
		
		if (genXML == true) {
			System.out.println("**** Converting data files into XML ****\n");
			System.out.println("Parsing Persons data file...");
			parsePersonsXml(xmlOut);
			System.out.println("Parsing Customers data file...");
			parseCustomersXml(xmlOut);
			System.out.println("Parsing Product data file...");
			parseProductXml(xmlOut);
			System.out.println("\n**** Conversion complete ****");
		}
		
		if (genJson == true) {
			parsePersonsJson();
		}
	}
	
	public void parsePersonsXml(boolean out) {
		System.out.println("Generating XML file: "+PERSONS_XML_FILE);
    	try {
			JAXBContext cont = JAXBContext.newInstance(PersonsHub.class);
			Marshaller marsh = cont.createMarshaller();
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File f = new File(PERSONS_XML_FILE);
			marsh.marshal(phub, f);
			if (out == true) {
				marsh.marshal(phub, System.out);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	//LEXI! Y U NO MAKE?!
	private void parsePersonsJson() {
		final JsonSerializationContext context;
		final JsonObject derps = new JsonObject();
		
		final JsonArray emails = new JsonArray();
		
		
		String derpsString = "";
		for(Persons p : phub.getCollection()) {
			
			final JsonElement joAddr = context.serialize(p.getAddress().get(0).getStreet());
			
			derps.addProperty("id", p.getId());
			derps.addProperty("firstName", p.getFirstName());
			derps.addProperty("lastName", p.getLastName());
			derps.addProperty("address", joAddr);
			derps.addProperty("email", emails);
			
			
		
			//HOLD ON
		/*	jsonAdd.put("street", p.getAddress().get(0).getStreet());
			jsonAdd.put("city", p.getAddress().get(0).getCity());
			jsonAdd.put("state", p.getAddress().get(0).getState());
			jsonAdd.put("zip", p.getAddress().get(0).getZip());
			jsonAdd.put("country", p.getAddress().get(0).getCountry());	
			
			derps.put("id", p.getId());
			derps.put("firstName", p.getFirstName());
			derps.put("lastName", p.getLastName());
			derps.put("address", jsonAdd);
			derps.put("email", p.getEmails());
			
			*/
		
		derpsString = derpsString + derps.toString();
		}
		FileWriter out = null;
		try {
			out = new FileWriter(PERSONS_JSON_FILE);
			out.write(derpsString);
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void parsePersonsDat() {
		Scanner s = null;
    	try {
			s = new Scanner(new File(PERSONS_DATA_FILE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    	String line = null;    	
    	try {
    	while(!(line = s.nextLine()).isEmpty()) {
			String tokens[] = line.split(";");
			String code = tokens[0];
			
			if (tokens.length > 2) {
				String[] names = tokens[1].split(",");
				String[] address = tokens[2].split(",");
				String[] derp = {"N/A"};
				String[] emails = (tokens.length == 4) ? tokens[3].split(",") : derp;
				Persons dudes = new Persons();
				dudes.setId(code);
				dudes.setFirstName(names[0]);
				dudes.setLastName(names[1]);
				dudes.setEmails(emails);
			
				ArrayList<Address> addressList = new ArrayList<Address>();
				Address adr = new Address();
				adr.setStreet(address[0]);
				adr.setCity(address[1]);
				adr.setState(address[2]);
				adr.setZip(address[3]);
				adr.setCountry(address[4]);
				addressList.add(adr);
				dudes.setAddress(adr);
				phub.addPersons(dudes);
			}
    	}} catch (Exception e) {
    		
    	}
    	s.close();
	}
	
	public void parseCustomersXml(boolean out) {
		System.out.println("Generating XML file: "+CUSTOMER_XML_FILE);
    	try {
			JAXBContext cont = JAXBContext.newInstance(CustomersHub.class);
			Marshaller marsh = cont.createMarshaller();
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File f = new File(CUSTOMER_XML_FILE);
			marsh.marshal(chub, f);
			if (out == true) {
				marsh.marshal(chub, System.out);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	//LEXI! Y U NO MAKE?!
	public void parseCustomerJson() {
		for(GovComp g : chub.getGovList()){
			jsonGov.put("", );
		}
	}
	
	public void parseCustomersDat() {
		Scanner s = null;
    	try {
			s = new Scanner(new File(CUSTOMER_DATA_FILE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    	s.nextLine();
    	String line = null;
    	try {
    	while(!(line = s.nextLine()).isEmpty()) {
			String tokens[] = line.split(";");
			
			if (tokens.length > 4) {
				String code = tokens[0];
				String type = tokens[1];
				String id = tokens[2];
				String name = tokens[3];
				String[] address = tokens[4].split(",");
				
				ArrayList<Address> adrList = new ArrayList<Address>();
				Address adr = new Address();
				adr.setStreet(address[0]);
				adr.setCity(address[1]);
				adr.setState(address[2]);
				adr.setZip(address[3]);
				adr.setCountry(address[4]);
				adrList.add(adr);
			
				if (type.equals("G") || type.equals("g")) {
					GovComp gov = new GovComp();
					gov.setCode(code);
					gov.setName(name);
					gov.addAddress(adr);
					gov.addHumanRep(phub.getPersonInfo(id));
					chub.addGovList(gov);
					
				} else if (type.equals("C") || type.equals("c")) {
					PubComp pub = new PubComp();
					pub.setCode(code);
					pub.setName(name);
					pub.addAddress(adr);
					pub.addHumanRep(phub.getPersonInfo(id));
					chub.addPubList(pub);
					
				} else {
					System.out.println("Error: Invalid Company Type for: "+name);
				}
			}
    	}} catch (Exception e) {
    		
    	}
    	s.close();
	}
	
	public void parseProductXml(boolean out) {
		System.out.println("Generating XML file: "+PRODUCTS_XML_FILE);
    	try {
			JAXBContext cont = JAXBContext.newInstance(ProductsHub.class);
			Marshaller marsh = cont.createMarshaller();
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File f = new File(PRODUCTS_XML_FILE);
			marsh.marshal(prhub, f);
			if (out == true) {
				marsh.marshal(prhub, System.out);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	//LEXI! Y U NO MAKE?!
	public void parseProductJson() {

	}
	
	public void parseProductDat() {
		Scanner s = null;
    	try {
			s = new Scanner(new File(PRODUCTS_DATA_FILE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    	s.nextLine();
    	String line = null;
    	try {
    	while(!(line = s.nextLine()).isEmpty()) {
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
					c.addConsultList(phub.getPersonInfo(valueOne));
					prhub.addConsutation(c);
				
				} else {
					System.out.println("Error: Invalid Product Type for: "+name);
				}
			}
		}} catch (Exception e) {
    		
    	}
		s.close();
	}
	
	public static void main(String[] args) {
		boolean XML_CONSOLE_OUTPUT = false;
		boolean XML_GENERATE_DATA = false;
		boolean JSON_GENERATE_DATA = true;
		new DatarConvert(XML_CONSOLE_OUTPUT, XML_GENERATE_DATA, JSON_GENERATE_DATA);
	}
}
