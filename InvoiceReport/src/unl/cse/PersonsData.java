package unl.cse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonsData {
	private Connection conn;
	private PersonsHub phub = new PersonsHub();
	
	public void downloadPersons() {
		String query = "SELECT * FROM Persons WHERE PersonsID > ?";
		String queryEmails = "SELECT Email FROM Emails WHERE PersonsID = ?";
		String queryAddr = "SELECT * FROM Address WHERE AddressID = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		try {
			conn = DriverManager.getConnection(DBConnection.DB_URL, DBConnection.DB_USERNAME, DBConnection.DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, 0);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// Persons data
				int personID = rs.getInt("PersonsID");
				String persCode = rs.getString("PersCode");
				String lastName = rs.getString("LastName");
				String firstName = rs.getString("FirstName");
				int addressID = rs.getInt("AddressID");
				Persons p = createPersons(personID, persCode, firstName, lastName, addressID);
				// Email data
				PreparedStatement psEmails = conn.prepareStatement(queryEmails);
				psEmails.setInt(1, personID);
				ResultSet rsEmails = psEmails.executeQuery();
				List<String> emails = new ArrayList<String>();
				while (rsEmails.next()) {
					String email = rsEmails.getString("Email");
					emails.add(email);
				}
				setEmails(p, emails);
				// Address data
				PreparedStatement psAddr = conn.prepareStatement(queryAddr);
				psAddr.setInt(1, addressID);
				ResultSet rsAddr = psAddr.executeQuery();
				AddressData ad = new AddressData();
				Address a = new Address();
				while (rsAddr.next()) {
					int countryID = rsAddr.getInt("CountryID");
					int stateID = rsAddr.getInt("StateID");
					int cityID = rsAddr.getInt("CityID");
					int zipID = rsAddr.getInt("ZipID");
					int streetID = rsAddr.getInt("StreetID");
					a = ad.createAddress(addressID, countryID, stateID, cityID, zipID, streetID);
				}
				p.setAddress(a);
				phub.addPersons(p);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public Persons createPersons(int id, String code, String firstName, String lastName, int addrID) {
		Persons p = new Persons();
		p.setPrimaryKey(id);
		p.setAddressID(addrID);
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setId(code);
		return p;
	}
	
	public void setEmails(Persons p, List<String> e) {
		String[] emails = new String[e.size()];
		for (int i=0; i<e.size(); i++) {
			emails[i] = e.get(i);
		}
		p.setEmails(emails);
	}
	
	public PersonsHub getPersons() {
		return phub;
	}
}