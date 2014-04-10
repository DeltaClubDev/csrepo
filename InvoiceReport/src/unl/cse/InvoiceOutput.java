package unl.cse;

import java.util.List;

/**
 * <b>InvoiceOutput</b> was created in order to help keep the <b>DataConverter</b> easier
 * to read. Here, a great deal of time was spent by a person with OCD to nicely format an
 * output for all the invoice data.
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.5.0
 */
public class InvoiceOutput {
	private InvoicesHub ihub;
	
	/**
	 * <i>InvoiceOutput</i> constructor
	 * 
	 * @param phub - <b>PersonsHub</b> object containing all the Persons instances
	 * @param prhub - <b>ProductsHub</b> object containing all the Products instances
	 * @param chub - <b>CustomersHub</b> object containing all the Customers instances
	 * @param ihub - <b>InvoicesHub</b> object containing all the Invoice instances
	 */
	public InvoiceOutput(InvoicesHub ihub) {
		this.ihub = ihub;
		parseInvoicesTxt();
	}
	
	/**
	 * <i>getAlign</i> is a function that was commonly used, so we made this reusable
	 * 
	 * @param size - size of the box to center the text in (double)
	 * @param str - text to fill the box (String)
	 * @return - Returns an integer
	 */
	public int getAlign(double size, String str) {
		int result = 0;
		if (((size - str.length()) / 2.0) != (Math.floor((size - str.length()) / 2.0))) {
			result = 1;
		}
		return result;
	}
	
	/**
	 * <i>getFormat</i> is the main juice to get centered text boxes. This fuction is used often
	 * so it became necessary to make a method out of it.
	 * 
	 * @param size - size of the box to center the text in (double)
	 * @param str - text to fill the box (String)
	 * @return - Returns a String
	 */
	public String getFormat(double size, String str) {
		String result = "%"+(Math.floor((((size - str.length()) / 2)+getAlign(size, str))))+"s%"+str.length()+"s%"+
		(Math.floor(((size - str.length()) / 2)))+"s";
		return result;
	}
	
	/**
	 * <i>cashToString</i> is commonly used to convert from doubles to String
	 * 
	 * @param cash - value to convert into a String (double)
	 * @return - Returns a String
	 */
	public String cashToString(double cash) {
		String result = "N/A";
		try {
			result = String.format("%.2f", cash).trim();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return result;
	}
	
	public void parseInvoicesTxt() {
		System.out.println("~=====================~");
		System.out.println("| Summary of Invoices |");
		System.out.println("~=====================~");
		
		//Printing Table Header for Invoice Summary
		System.out.print("\n+---------+"); //ID
		System.out.print("-------------------------------------------+"); //Customer
		System.out.print("------------------------------+"); // Sales person
		System.out.print("---------------+"); // Subtotal
		System.out.print("-------------+"); // Fees
		System.out.print("-------------+"); // Taxes
		System.out.print("---------------+\n"); // Total
		
		System.out.print("|");
		System.out.printf("%3s%2s%4s","","ID","");
		System.out.print("|");
		
		System.out.printf("%17s%8s%18s","", "Customer", "");
		System.out.print("|");
		
		System.out.printf("%9s%12s%9s", "", "Sales person", "");
		System.out.printf("|");
		
		System.out.printf("%3s%8s%4s", "", "Subtotal", "");
		System.out.print("|");
		
		System.out.printf("%4s%4s%5s", "", "Fees", "");
		System.out.print("|");
		
		System.out.printf("%4s%5s%4s", "", "Taxes", "");
		System.out.print("|");
		
		System.out.printf("%5s%5s%5s", "", "Total", "");
		System.out.print("|\n");
		
		System.out.print("+---------+"); //ID
		System.out.print("-------------------------------------------+"); //Customer
		System.out.print("------------------------------+"); // Sales person
		System.out.print("---------------+"); // Subtotal
		System.out.print("-------------+"); // Fees
		System.out.print("-------------+"); // Taxes
		System.out.print("---------------+\n"); // Total
		
		// Iterate through Invoice list and print summary details
		for (Invoices i : ihub.getCollection()) {
			String id = i.getCode();
			System.out.print("|");
			try {
				System.out.printf(getFormat(9.0, id), "", id, "");
			} catch (Exception e) {
				System.out.printf("%3s%3s%3s","","N/A","");
			}
			//Company name
			String company = i.getCustomer().getName();
			System.out.print("|");
			try {
				System.out.printf(getFormat(43.0, company), "", company, "");
			} catch (Exception e) {
				System.out.printf("%13s %13s %14s", "", "N/A", "");
			}
			//Sales person name
			String name = i.getPerson().getFirstName()+", "+i.getPerson().getLastName();
			System.out.print("|");
			try {
				System.out.printf(getFormat(30.0, name), "", name, "");
			} catch (Exception e) {
				System.out.printf("%9s %9s %9s", "", "N/A", "");
			}
			//Subtotal
			String subtotalF = cashToString(i.getProducts().getSubTotal());
			System.out.print("|");
			try {
				System.out.printf(getFormat(14.0, subtotalF), "", "$"+subtotalF, "");
			} catch (Exception e) {
				System.out.printf("%4s %3s %4s", "", "N/A", "");
			}
			//Fees
			double fee = i.getProducts().getFee(i.getHasComplyFee());
			String feeF = cashToString(fee);
			System.out.print("|");
			try {
				System.out.printf(getFormat(12.0, feeF), "", "$"+feeF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			//Taxes
			double taxes = i.getProducts().getTaxed(i.getHasComplyFee());
			String taxF = cashToString(taxes);
			System.out.print("|");
			try {
				System.out.printf(getFormat(12.0, taxF), "", "$"+taxF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			//Total
			String totalF = cashToString(i.getProducts().getCost(i.getHasComplyFee()));
			System.out.print("|");
			try {
				System.out.printf(getFormat(14.0, totalF), "", "$"+totalF, "");
			} catch (Exception e) {
				System.out.printf("%4s %3s %4s", "", "N/A", "");
			}
			System.out.print("|");
			
			System.out.print("\n");
			System.out.print("+---------+"); //ID
			System.out.print("-------------------------------------------+"); //Customer
			System.out.print("------------------------------+"); // Sales person
			System.out.print("---------------+"); // Subtotal
			System.out.print("-------------+"); // Fees
			System.out.print("-------------+"); // Taxes
			System.out.print("---------------+\n"); // Total
		}
				
		System.out.print("\n");
		System.out.println("~============================~");
		System.out.println("| Individual Invoice Reports |");
		System.out.println("~============================~");
		System.out.print("\n");

		// Iterate through Invoice list and print details
		for (Invoices i : ihub.getCollection()) {
			System.out.println("++-----------++");
			System.out.printf("%3s%-9s%3s", "|| ", i.getCode()," ||\n");
			System.out.println("++-----------++");
			
			String name = i.getPerson().getFirstName()+", "+i.getPerson().getLastName();
			System.out.printf("\n\t%14s%-30s", "Sales person: ", name);
			System.out.print("\n");
			
			String company = i.getCustomer().getName();
			System.out.println("\t[]---------------[]");
			System.out.printf("\t%3s%13s%3s", "|| ", "Customer Data"," ||\n");
			System.out.println("\t[]---------------[]\n\t:");
			System.out.printf("\t%1s==> %-10s%-46s\n", ":", "Name: ", company+" ("+i.getCustomer().getCode()+")");
	
			//customer data
			String street = i.getCustomer().getAddress().getStreet();
			String city = i.getCustomer().getAddress().getCity();
			String country = i.getCustomer().getAddress().getCountry();
			String state = i.getCustomer().getAddress().getState();
			String zip = i.getCustomer().getAddress().getZip();
			String firstName = i.getCustomer().getHumanRep().getFirstName();
			String lastName = i.getCustomer().getHumanRep().getLastName();
			String dudeId = i.getCustomer().getPersonsCode();

			System.out.printf("\t%1s==> %-10s%-46s\n", ":", "Country: ", country);
			System.out.printf("\t%1s==> %-10s%-46s\n", ":", "State: ", state);
			System.out.printf("\t%1s==> %-10s%-46s\n", ":", "City: ", city);
			System.out.printf("\t%1s==> %-10s%-46s\n", ":", "Street: ", street);
			System.out.printf("\t%1s==> %-10s%-46s\n\t:\n", ":", "Zip Code: ", zip);
			System.out.printf("\t%1s=====> %-16s%-46s\n", ":", "Human Resource: ", lastName+", "+firstName+" ("+dudeId+")");
			System.out.print("\n");
			
			//invoice data
			System.out.println("\t[]---------------[]");
			System.out.printf("\t%3s%12s%3s", "|| ", " Invoice Data"," ||\n");
			System.out.print("\t[]---------------[]\n\t:\n\t:");
			System.out.print("+------+"); // Code
			System.out.print("-----------------------------------------------------------------+"); // Product
			System.out.print("-------------+"); // Fees
			System.out.print("-------------+"); // Taxed
			System.out.print("---------------+\n\t:"); // Total
			System.out.print("|");
			System.out.printf("%1s%4s%1s","","Code","");
			System.out.print("|");
			System.out.printf("%29s%7s%29s","","Product","");
			System.out.print("|");
			System.out.printf("%4s%4s%5s", "", "Fees", "");
			System.out.print("|");
			System.out.printf("%4s%5s%4s", "", "Taxed", "");
			System.out.print("|");
			System.out.printf("%5s%5s%5s", "", "Total", "");
			System.out.print("|\n\t:");
			System.out.print("+------+"); // Code
			System.out.print("-----------------------------------------------------------------+"); // Product
			System.out.print("-------------+"); // Fees
			System.out.print("-------------+"); // Taxed
			System.out.print("---------------+"); // Total
			
			List<String> codes = i.getProducts().getCodes();
			for (String c : codes) {
				System.out.print("\n\t:|");
				// Code
				try {
					System.out.printf(getFormat(6.0, c), "", c, "");
				} catch (Exception e) {
					System.out.printf(getFormat(6.0, "N/A"), "", "N/A", "");
				}
				// Product
				System.out.print("|");
				String prodInfo = i.getProducts().getNameByCode(c)+" ("+i.getProducts().getInfoByCode(c)+")";					
				try {
					System.out.printf(getFormat(65.0, prodInfo), "", prodInfo, "");
				} catch (Exception e) {
					System.out.printf(getFormat(65.0, "N/A"), "", "N/A", "");
				}
				// Fees
				String feeF = cashToString(i.getProducts().getFeeByCode(c, i.getHasComplyFee()));
				System.out.print("|");
				try {
					System.out.printf(getFormat(12.0, feeF), "", "$"+feeF, "");
				} catch (Exception e) {
					System.out.printf("%3s %3s %3s", "", "N/A", "");
				}
				
				// Taxes
				String taxedF = cashToString(i.getProducts().getTaxedByCode(c, i.getHasComplyFee()));
				System.out.print("|");
				try {
					System.out.printf(getFormat(12.0, taxedF), "", "$"+taxedF, "");
				} catch (Exception e) {
					System.out.printf("%3s %3s %3s", "", "N/A", "");
				}

				// Total
				String totF = cashToString(i.getProducts().getSubTotalByCode(c));
				System.out.print("|");
				try {
					System.out.printf(getFormat(14.0, totF), "", "$"+totF, "");
				} catch (Exception e) {
					System.out.printf("%4s %3s %4s", "", "N/A", "");
				}
				
				System.out.print("|");
				System.out.print("\n\t:+------+"); // Code
				System.out.print("-----------------------------------------------------------------+"); // Product
				System.out.print("-------------+"); // Fees
				System.out.print("-------------+"); // Taxed
				System.out.print("---------------+"); // Total
			}
			
			// Print out Totals
			System.out.print("\n\t:\n\t:");
			System.out.print("=======================================================> Sub-Totals: ");
			System.out.printf("%-5s", "");
			
			// Total Fee
			double fee = i.getProducts().getFee(i.getHasComplyFee());
			String feeF = cashToString(fee);
			try {
				System.out.printf(getFormat(12.0, feeF), "", "$"+feeF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}

			// Total Taxes
			String taxF = cashToString(i.getProducts().getTaxed(i.getHasComplyFee()));
			System.out.print("|");
			try {
				System.out.printf(getFormat(12.0, taxF), "", "$"+taxF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			
			// Sub Total
			String subtotalF = cashToString(i.getProducts().getSubTotal());
			System.out.print("|");
			try {
				System.out.printf(getFormat(14.0, subtotalF), "", "$"+subtotalF, "");
			} catch (Exception e) {
				System.out.printf("%4s %3s %4s", "", "N/A", "");
			}
			
			// Comply Fee
			String complyFee = "0.00";
			if (i.getHasComplyFee() == true) {
				complyFee = "125.00";
			}
			System.out.print("\n\t:=======================================================> Compliance Fee:");
			System.out.printf("%30s", "");
			try {
				System.out.printf(getFormat(12.0, complyFee), "", "$"+complyFee, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			
			// Total!
			System.out.printf("\n\t:%56s%60s", "", "_____________________________________________________________");
			System.out.print("\n\t:=======================================================> Total: ");
			System.out.printf("%38s", "");
			String totalF = cashToString(i.getProducts().getCost(i.getHasComplyFee()));
			try {
				System.out.printf(getFormat(14.0, totalF), "", "$"+totalF, "");
			} catch (Exception e) {
				System.out.printf("%4s %3s %4s", "", "N/A", "");
			}
			System.out.printf("\n\t%57s%62s", "", "#############################################################");
			System.out.print("\n\n");
		}
	}	
}