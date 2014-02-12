package unl.cse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.json.simple.JSONObject;

//TODO: finish Java Docs and JSON parser!

public class DatarConvert {
	private final String PERSONS_DATA_FILE = "datar/Persons.dat";
	private final String CUSTOMER_DATA_FILE = "datar/Customers.dat";
	private final String PRODUCTS_DATA_FILE = "datar/Products.dat";
	private final String PERSONS_XML_FILE = "datar/Persons.xml";
	private final String CUSTOMER_XML_FILE = "datar/Customers.xml";
	private final String PRODUCTS_XML_FILE = "datar/Products.xml";
	private PersonsHub phub = new PersonsHub();
	private ProductsHub prhub = new ProductsHub();
	private CustomersHub chub = new CustomersHub();
	private JSONObject derps = new JSONObject();
	
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
	public void parsePersonsJson() {
		ArrayList<Persons> temp = new ArrayList<Persons>();
		temp.addAll(phub.getCollection());
		
		for(Persons p : temp) {
			derps.put("id", p.getId());
			derps.put("firstName", p.getFirstName());
			derps.put("lastName", p.getLastName());
			derps.put("address", p.getAddress()); //P.GETADDRESS NEEDS TO BE ANOTHER JSONOBJECT INSTEAD
			derps.put("email", p.getEmails()); //see above
			
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