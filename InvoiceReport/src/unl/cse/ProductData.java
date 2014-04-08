package unl.cse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductData {
	private Connection conn;
	private ProductsHub prhub = new ProductsHub();
	
	public void downloadProducts(PersonsHub phub) {
		String query = "SELECT * FROM Products WHERE ProductID > ?";
		
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
				int id = rs.getInt("ProductID");
				String type = rs.getString("TypeThing");
				String code = rs.getString("ProdCode");
				String name = rs.getString("Name");
				if ((type.equals("E")) || (type.equals("e"))) {
					double unitMoneh = rs.getDouble("UnitMoneh");
					Equipment e = createEquipment(id, code, name, unitMoneh);
					prhub.addEquipment(e);
				} else if ((type.equals("C")) || (type.equals("c"))) {
					int persID = rs.getInt("PersonsID");
					double hourFee = rs.getDouble("HourMoneh");
					for (Persons p : phub.getCollection()) {
						if (persID == p.getPrimaryKey()) {
							Consultation c = createConsult(id, code, name, persID, hourFee, p);
							prhub.addConsutation(c);
						}
					}
				} else if ((type.equals("L")) || (type.equals("l"))) {
					double fee = rs.getDouble("Fee");
					double annualFee = rs.getDouble("AnnualingCostings");
					License l = createLicense(id, code, name, fee, annualFee);
					prhub.addLicense(l);
				}
			} 
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public Equipment createEquipment(int id, String code, String name, double unitMoneh) {
		Equipment e = new Equipment();
		e.setPrimaryKey(id);
		e.setCode(code);
		e.setName(name);
		e.setUnitPrice(unitMoneh);
		return e;
	}
	
	public License createLicense(int id, String code, String name, double fee, double annualFee) {
		License l = new License();
		l.setPrimaryKey(id);
		l.setCode(code);
		l.setName(name);
		l.setPrice(fee);
		l.setAnnualPrice(annualFee);
		return l;
	}
	
	public Consultation createConsult(int id, String code, String name, int persId, double hourFee, Persons p) {
		Consultation c = new Consultation();
		c.setPrimaryKey(id);
		c.setCode(code);
		c.setName(name);
		c.setPersonsID(persId);
		c.setHourPrice(hourFee);
		c.addHumanRep(p);
		return c;
	}
	
	public ProductsHub getProducts() {
		return prhub;
	}
}