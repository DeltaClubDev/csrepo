package unl.cse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainInvoiceData {
	private Connection conn;
	private InvoicesHub ihub = new InvoicesHub();
	
	public void downloadInvoices(PersonsHub phub, ProductsHub prhub, CustomersHub chub) {
		String query = "SELECT * FROM MainInvoice WHERE MainInvoiceID > ?";
		String queryI = "SELECT * FROM Invoice WHERE MainInvoiceID = ?";
		String queryPT = "SELECT TypeThing FROM Products WHERE ProductID = ?";
		
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
				Invoices i = new Invoices();
				// MainInvoice Data
				int mainInvoiceID = rs.getInt("MainInvoiceID");
				i.setPrimaryKey(mainInvoiceID);
				String code = rs.getString("InvoiceCode");
				i.setCode(code);
				int custID = rs.getInt("CompanyID");
				i.setCustomerID(custID);
				int persID = rs.getInt("SalesDudeID");
				i.setPersonID(persID);
				// Check Person
				for (Persons p : phub.getCollection()) {
					if (p.getPrimaryKey() == persID) {
						i.setPerson(p);
					}
				}
				// Check type of customer
				for (Customer c : chub.getCustomerList()) {
					if (c.getPrimaryKey() == custID) {
						if ((c.getType() == 'G') || (c.getType() == 'g')) {
							i.setHasComplyFee(true);
						} else {
							i.setHasComplyFee(false);
						}
						i.setCustomer(c);
					}
				}
				// Invoice Data
				PreparedStatement psInv = conn.prepareStatement(queryI);
				psInv.setInt(1, mainInvoiceID);
				ResultSet rsInv = psInv.executeQuery();
				while (rsInv.next()) {
					char type = 'N';
					int invoiceID = rsInv.getInt("InvoiceID");
					int prodID = rsInv.getInt("ProductID");
					// Get product Type
					PreparedStatement psType = conn.prepareStatement(queryPT);
					psType.setInt(1, prodID);
					ResultSet rsType = psType.executeQuery();
					while (rsType.next()) {
						type = rsType.getString("TypeThing").charAt(0);
					}
					// Set Products
					if ((type == 'E') || (type == 'e')) {
						for (Equipment e : prhub.getEquipList()) {
							if (e.getPrimaryKey() == prodID) {
								i.setEquipment(e, rsInv.getString("NumUnits"), invoiceID);
							}
						}
					} else if ((type == 'L') || (type == 'l')) {
						for (License l : prhub.getLiceList()) {
							if (l.getPrimaryKey() == prodID) {
								i.setLicense(l, rsInv.getString("StartDate"), rsInv.getString("EndDate"), invoiceID);
							}
						}
					} else if ((type == 'C') || (type == 'c')) {
						for (Consultation c : prhub.getConsultList()) {
							if (c.getPrimaryKey() == prodID) {
								i.setConsultant(c, rsInv.getString("NumHours"), invoiceID);
							}
						}
					}
				}
				ihub.addInvoices(i);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public InvoicesHub getInvoices() {
		return ihub;
	}
}