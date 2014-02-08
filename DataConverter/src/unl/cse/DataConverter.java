package unl.cse;

/*
 * Main DataConverter file, responsible for the control flow of functions
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class DataConverter {
	
	private static final String DEPOSIT = "D";
	private static final String STOCK = "S";
	private static final String PRIVATE_INVESTMENT = "P";
	private static final AssetsHub a = new AssetsHub();
	private static final PersonsHub p = new PersonsHub();

	
	public DataConverter() {
		loadFilePersons();
		loadFileAssets();
	}
	
	//Persons Object parser and XML generator
	private void loadFilePersons() {
		Scanner s = null;
    	try {
			s = new Scanner(new File("data/Persons.dat"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
    	s.nextLine();
    	while(s.hasNextLine()) {
    		String line = s.nextLine();
			String tokens[] = line.split(";");
			String id = tokens[0];
			String[] broker = tokens[1].split(",");
			String[] names = tokens[2].split(",");
			String[] address = tokens[3].split(",");
			String[] derp = {"N/A"};
			String[] emails = (tokens.length == 5) ? tokens[4].split(",") : derp;
			Persons dudes;
			dudes = new Persons();
			dudes.setBroker(broker);
			dudes.setId(id);
			dudes.setEmails(emails);
			ArrayList<Address> addressCollection = new ArrayList<Address>();
			Address adr = new Address();
			adr.setStreet(address[0]);
			adr.setCity(address[1]);
			adr.setState(address[2]);
			adr.setZip(address[3]);
			adr.setCountry(address[4]);
			addressCollection.add(adr);
			dudes.setAddress(adr);
			ArrayList<Name> nameCollection = new ArrayList<Name>();
			Name jose = new Name();
			jose.setFirstname(names[0]);
			jose.setLastname(names[1]);
			nameCollection.add(jose);
			dudes.setName(jose);
			p.addPersons(dudes);
    	}
    	try {
			JAXBContext cont = JAXBContext.newInstance(PersonsHub.class);
			Marshaller marsh = cont.createMarshaller();
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File f = new File("data/Persons.xml");
			marsh.marshal(p, f);
			marsh.marshal(p, System.out);
		} catch (JAXBException e) {
			System.out.println("Error creating persons xml: "+e);
			e.printStackTrace();
		}
	}
	
	//Assets Object parser and XML generator
	private void loadFileAssets() {
		Scanner s = null;
    	try {
			s = new Scanner(new File("data/Assets.dat"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
    	s.nextLine();
    	while(s.hasNextLine()) {
    		String line = s.nextLine();
    		String tokens[] = line.split(";");
    		String code = tokens[0];
    		String type = tokens[1];
			String label = tokens[2];
    		
    		if (type.equals(DEPOSIT)) {
    			String apr = tokens[3];
    			Assets as = new Deposits(code, type, label, apr);
    			a.addAssets(as);
    		} else if (type.equals(STOCK)) {
    			String qrtDividen = tokens[3];
    			String bRateReturn = tokens[4];
    			String omega = tokens[5];
    			String stockSymb = tokens[6];
    			String sharePrice = tokens[7];
    			Assets as = new Stocks(code, type, label, qrtDividen, bRateReturn, omega, stockSymb, sharePrice);
    			a.addAssets(as);
    		} else if (type.equals(PRIVATE_INVESTMENT)) {
    			String qrtDividen = tokens[3];
    			String bRateReturn = tokens[4];
    			String omega = tokens[5];
    			String totVal = tokens[6];
    			Assets as = new PrivetInvest(code, type, label, qrtDividen, bRateReturn, omega, totVal);
    			a.addAssets(as);
    		} else {
    			System.out.println("Warning! Asset "+code+" has no type!");
    		}
    	}
    	
    	try {
			JAXBContext cont = JAXBContext.newInstance(AssetsHub.class);
			Marshaller marsh = cont.createMarshaller();
			marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File f = new File("data/Assets.xml");
			marsh.marshal(a, f);
			marsh.marshal(a, System.out);
		} catch (JAXBException e) {
			System.out.println("Error creating persons xml: "+e);
			e.printStackTrace();
		}
	}
	
	//DataConverter starter
	public static void main(String[] args) {
		DataConverter dc = new DataConverter();
	}
}