package unl.cse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Downloads the Customer data and creates the <b>Customer</b> objects
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.5.0
 */
public class CustomerData {
	private Connection conn;
	private CustomersHub chub = new CustomersHub();
	
	public void downloadCustomers(PersonsHub phub) {
		String query = "SELECT * FROM Company WHERE CompanyID > ?";
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
			PreparedStatement psAddr = conn.prepareStatement(queryAddr);
			ps.setInt(1, 0);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// Customer Data
				int companyID = rs.getInt("CompanyID");
				int personsID = rs.getInt("PersonsID");
				int addressID = rs.getInt("AddressID");
				String persCode = "N/A";
				for (Persons p : phub.getCollection()) {
					if (personsID == p.getPrimaryKey()) {
						persCode = p.getId();
					}
				}
				String name = rs.getString("Name");
				String type = rs.getString("TypeThing");
				String code = rs.getString("CompCode");
				Customer c = createCustomer(companyID, personsID, addressID, name, type, code, persCode);
				// Address Data
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
				c.addAddress(a);
				// Persons Data
				for (Persons p : phub.getCollection()) {
					if (personsID == p.getPrimaryKey()) {
						c.addHumanRep(p);
					}
				}
				chub.addCustomer(c);
			}

			if ((ps != null) || (!ps.isClosed())) {
				ps.close();
			} if (psAddr != null) {
				psAddr.close();
			} if ((this.conn != null) || (!this.conn.isClosed())) {
				this.conn.close();
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public Customer createCustomer(int id, int persId, int addrId, String name, String type, String code, String persCode) {
		Customer c = new Customer();
		c.setPrimaryKey(id);
		c.setPersonsID(persId);
		c.setAddressID(addrId);
		c.setName(name);
		c.setCode(code);
		c.setPersonsCode(persCode);
		c.setType(type.charAt(0));
		return c;
	}
	
	public CustomersHub getCustomers() {
		return chub;
	}
}