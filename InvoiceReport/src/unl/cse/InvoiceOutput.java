package unl.cse;

import java.util.ArrayList;
import java.util.List;

public class InvoiceOutput {
	private PersonsHub phub;
	private ProductsHub prhub;
	private CustomersHub chub;
	private InvoicesHub ihub;
	
	public InvoiceOutput(PersonsHub phub, ProductsHub prhub, CustomersHub chub, InvoicesHub ihub) {
		this.phub = phub;
		this.prhub = prhub;
		this.chub = chub;
		this.ihub = ihub;
		parseInvoicesTxt();
	}
	
	public int getAlign(double size, String str) {
		int result = 0;
		if (((size - str.length()) / 2.0) != (Math.floor((size - str.length()) / 2.0))) {
			result = 1;
		}
		return result;
	}
	
	public String getFormat(double size, String str) {
		String result = "%"+(Math.floor((((size - str.length()) / 2)+getAlign(size, str))))+"s%"+str.length()+"s%"+
		(Math.floor(((size - str.length()) / 2)))+"s";
		return result;
	}
	
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
		System.out.print("\n+------+"); //ID
		System.out.print("----------------------------------------------+"); //Customer
		System.out.print("------------------------------+"); // Sales person
		System.out.print("-------------+"); // Subtotal
		System.out.print("-------------+"); // Fees
		System.out.print("-------------+"); // Taxes
		System.out.print("-------------+\n"); // Total
		
		System.out.print("|");
		System.out.printf("%2s%2s%2s","","ID","");
		System.out.print("|");
		
		System.out.printf("%19s%8s%19s","", "Customer", "");
		System.out.print("|");
		
		System.out.printf("%9s%12s%9s", "", "Sales person", "");
		System.out.printf("|");
		
		System.out.printf("%2s%8s%3s", "", "Subtotal", "");
		System.out.print("|");
		
		System.out.printf("%4s%4s%5s", "", "Fees", "");
		System.out.print("|");
		
		System.out.printf("%4s%5s%4s", "", "Taxes", "");
		System.out.print("|");
		
		System.out.printf("%4s%5s%4s", "", "Total", "");
		System.out.print("|\n");
		
		System.out.print("+------+"); //ID
		System.out.print("----------------------------------------------+"); //Customer
		System.out.print("------------------------------+"); // Sales person
		System.out.print("-------------+"); // Subtotal
		System.out.print("-------------+"); // Fees
		System.out.print("-------------+"); // Taxes
		System.out.print("-------------+\n"); // Total
		
		// Iterate through Invoice list and print summary details
		for (Invoices i : ihub.getCollection()) {
			System.out.print("|");
			System.out.print(i.getInCode());
			//Company name
			String company = chub.getCompName(i.getCustomCode());
			System.out.print("|");
			System.out.printf(getFormat(46.0, company), "", company, "");
			
			//Sales person name
			String name = phub.getLastName(i.getSalesCode())+" "+phub.getFirstName(i.getSalesCode());
			System.out.print("|");
			System.out.printf(getFormat(30.0, name), "", name, "");
			
			//Subtotal
			String subtotalF = cashToString(i.getSubTotal());
			System.out.print("|");
			try {
				System.out.printf(getFormat(12.0, subtotalF), "", "$"+subtotalF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			
			//Fees
			double fee = i.getFee();
			if (i.getHasComplyFee() == true) {
				fee += 125.00;
			}
			String feeF = cashToString(fee);
			System.out.print("|");
			try {
				System.out.printf(getFormat(12.0, feeF), "", "$"+feeF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			
			//Taxes
			double taxes = 0.00;
			if (i.getHasComplyFee() == false) {
				taxes = i.getTaxes();
			}
			String taxF = cashToString(taxes);
			System.out.print("|");
			try {
				System.out.printf(getFormat(12.0, taxF), "", "$"+taxF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			
			//Total
			String totalF = cashToString(i.getSubTotal() + fee + taxes);
			System.out.print("|");
			try {
				System.out.printf(getFormat(12.0, totalF), "", "$"+totalF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			System.out.print("|");
			
			
			System.out.print("\n");
			System.out.print("+------+"); //ID
			System.out.print("----------------------------------------------+"); //Customer
			System.out.print("------------------------------+"); // Sales person
			System.out.print("-------------+"); // Subtotal
			System.out.print("-------------+"); // Fees
			System.out.print("-------------+"); // Taxes
			System.out.print("-------------+\n"); // Total
		}
				
		System.out.print("\n");
		System.out.println("~============================~");
		System.out.println("| Individual Invoice Reports |");
		System.out.println("~============================~");
		System.out.print("\n");

		// Iterate through Invoice list and print details
		for (Invoices i : ihub.getCollection()) {
			System.out.println("++--------++");
			System.out.printf("%3s%6s%3s", "|| ", i.getInCode()," ||\n");
			System.out.println("++--------++");
			
			String name = phub.getLastName(i.getSalesCode())+" "+phub.getFirstName(i.getSalesCode());
			System.out.printf("\n\t%14s%-30s", "Sales person: ", name);
			System.out.print("\n");
			
			String company = chub.getCompName(i.getCustomCode());
			System.out.println("\t[]---------------[]");
			System.out.printf("\t%3s%13s%3s", "|| ", "Customer Data"," ||\n");
			System.out.println("\t[]---------------[]\n\t:");
			System.out.printf("\t%1s==> %-10s%-46s\n", ":", "Name: ", company+" ("+i.getCustomCode()+")");
	
			//customer data
			List<Address> tempAddr = new ArrayList<Address>();
			List<Persons> tempDude = new ArrayList<Persons>();
			tempAddr = chub.getCompAddr(i.getCustomCode());
			tempDude = chub.getHumanRep(i.getCustomCode());
			String street = "N/A";
			String city = "N/A";
			String country = "N/A";
			String state = "N/A";
			String zip = "N/A";
			String firstName = "N/A";
			String lastName = "N/A";
			String dudeId = "N/A";
			for (Address s : tempAddr) {
				street = s.getStreet();
				city = s.getCity();
				country = s.getCountry();
				state = s.getState();
				zip = s.getZip();
			}
			for (Persons p : tempDude) {
				firstName = p.getFirstName();
				lastName = p.getLastName();
				dudeId = p.getId();
			}
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
			System.out.print("-------------+\n\t:"); // Total
			System.out.print("|");
			System.out.printf("%1s%4s%1s","","Code","");
			System.out.print("|");
			System.out.printf("%29s%7s%29s","","Product","");
			System.out.print("|");
			System.out.printf("%4s%4s%5s", "", "Fees", "");
			System.out.print("|");
			System.out.printf("%4s%5s%4s", "", "Taxed", "");
			System.out.print("|");
			System.out.printf("%4s%5s%4s", "", "Total", "");
			System.out.print("|\n\t:");
			System.out.print("+------+"); // Code
			System.out.print("-----------------------------------------------------------------+"); // Product
			System.out.print("-------------+"); // Fees
			System.out.print("-------------+"); // Taxed
			System.out.print("-------------+"); // Total
			
			List<String> codes = i.getCodes(i.getCustomCode());
			for (String c : codes) {
				char type = prhub.getType(c);
				System.out.print("\n\t:|");
				// Code
				System.out.printf(getFormat(6.0, c), "", c, "");
				
				// Product
				System.out.print("|");
				String prodInfo = prhub.getNameById(c, type)+" ("+i.getPayInfo(c, type)+")";					
				System.out.printf(getFormat(65.0, prodInfo), "", prodInfo, "");
				
				// Fees
				String feeF = cashToString(i.getProductFee(c, type));
				System.out.print("|");
				try {
					System.out.printf(getFormat(12.0, feeF), "", "$"+feeF, "");
				} catch (Exception e) {
					System.out.printf("%3s %3s %3s", "", "N/A", "");
				}
				
				// Taxes
				String taxedF = cashToString(i.getProductTax(c, type));
				System.out.print("|");
				try {
					System.out.printf(getFormat(12.0, taxedF), "", "$"+taxedF, "");
				} catch (Exception e) {
					System.out.printf("%3s %3s %3s", "", "N/A", "");
				}

				// Total
				String totF = cashToString(i.getProductTot(c, type));
				System.out.print("|");
				try {
					System.out.printf(getFormat(12.0, totF), "", "$"+totF, "");
				} catch (Exception e) {
					System.out.printf("%3s %3s %3s", "", "N/A", "");
				}
				
				System.out.print("|");
				System.out.print("\n\t:+------+"); // Code
				System.out.print("-----------------------------------------------------------------+"); // Product
				System.out.print("-------------+"); // Fees
				System.out.print("-------------+"); // Taxed
				System.out.print("-------------+"); // Total
			}
			
			// Print out Totals
			System.out.print("\n\t:\n\t:");
			System.out.print("=======================================================> Sub-Totals: ");
			System.out.printf("%-5s", "");
			
			// Total Fee
			double fee = i.getFee();
			if (i.getHasComplyFee() == true) {
				fee += 125.00;
			}
			String feeF = cashToString(fee);
			try {
				System.out.printf(getFormat(12.0, feeF), "", "$"+feeF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}

			// Total Taxes
			double taxes = 0.00;
			String complyFee = "0.00";
			if (i.getHasComplyFee() == false) {
				taxes = i.getTaxes();
			} else if (i.getHasComplyFee() == true) {
				complyFee = "125.00";
			}
			String taxF = cashToString(taxes);
			System.out.print("|");
			try {
				System.out.printf(getFormat(12.0, taxF), "", "$"+taxF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			
			// Sub Total
			String subtotalF = cashToString(i.getSubTotal());
			System.out.print("|");
			try {
				System.out.printf(getFormat(12.0, subtotalF), "", "$"+subtotalF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			
			// Comply Fee
			System.out.print("\n\t:=======================================================> Compliance Fee:");
			System.out.printf("%30s", "");
			try {
				System.out.printf(getFormat(12.0, complyFee), "", "$"+complyFee, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			
			// Total!
			System.out.printf("\n\t:%56s%60s", "", "___________________________________________________________");
			System.out.print("\n\t:=======================================================> Total: ");
			System.out.printf("%38s", "");
			String totalF = cashToString(i.getSubTotal() + fee + taxes);
			try {
				System.out.printf(getFormat(12.0, totalF), "", "$"+totalF, "");
			} catch (Exception e) {
				System.out.printf("%3s %3s %3s", "", "N/A", "");
			}
			System.out.printf("\n\t%57s%60s", "", "###########################################################");
			
			System.out.print("\n\n");
		}
	}	
}