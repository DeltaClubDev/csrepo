package unl.cse;

import com.cinco.InvoiceData;

/**
 * Test Client for the InvoiceData class (api)
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.5.0
 */
@SuppressWarnings("static-access")
public class ClientDerp {
	
	private PersonsHub phub;
	private ProductsHub prhub;
	private CustomersHub chub;
	private InvoicesHub ihub;
	private InvoiceData api = new InvoiceData();
	
	public ClientDerp(PersonsHub phub, ProductsHub prhub, CustomersHub chub, InvoicesHub ihub) {
		this.phub = phub;
		this.prhub = prhub;
		this.chub = chub;
		this.ihub = ihub;
	}
	
	public void uploadPeople() {
		for (Persons p : this.phub.getCollection()) {
			api.addPerson(p.getId(), p.getFirstName(), p.getLastName(), p.getAddress().getStreet(), 
			p.getAddress().getCity(), p.getAddress().getState(), p.getAddress().getZip(), 
			p.getAddress().getCountry());
		}
		for (Persons p : this.phub.getCollection()) {
			for (String e : p.getEmails()) {
				api.addEmail(p.getId(), e);
			}
		}
	}
	
	public void uploadCustomers() {
		for (Customer c : this.chub.getCustomerList()) {
			api.addCustomer(c.getCode(), c.getType()+"", c.getHumanRep().getId(), c.getName(), 
					c.getAddress().getStreet(),	c.getAddress().getCity(), c.getAddress().getState(), 
					c.getAddress().getZip(), c.getAddress().getCountry());
		}
	}
	
	public void uploadProducts() {
		for (Equipment e : this.prhub.getEquipList()) {
			api.addEquipment(e.getCode(), e.getName(), e.getUnitPrice());
		}
		for (License l : this.prhub.getLiceList()) {
			api.addLicense(l.getCode(), l.getName(), l.getPrice(), l.getAnnualPrice());
		}
		for (Consultation c : this.prhub.getConsultList()) {
			api.addConsultation(c.getCode(), c.getName(), c.getHumanRep().getId(), c.getHourPrice());
		}
	}
	
	public void uploadInvoices() {
		for (Invoices i : this.ihub.getCollection()) {
			api.addInvoice(i.getCode(), i.getCustomCode(), i.getSalesCode());
		}
		for (Invoices i : this.ihub.getCollection()) {
			for (Equipment e : i.getProducts().getEquipList()) {
				api.addEquipmentToInvoice(i.getCode(), e.getCode(),
						e.getInvUnits());
			}
		}
		for (Invoices i : this.ihub.getCollection()) {
			for (License lulz : i.getProducts().getLiceList()) {
				api.addLicenseToInvoice(i.getCode(), lulz.getCode(),
						lulz.getStartDate(), lulz.getEndDate());
			}
		}
		for (Invoices i : this.ihub.getCollection()) {
			for (Consultation see : i.getProducts().getConsultList()) {
				api.addConsultationToInvoice(i.getCode(), see.getCode(),
						see.getHours());
			}
		}
	}
	
	public void apiTest() {		
		
		// REMOVE ALL PERSONS
		api.removeAllPersons();
		
		// REMOVE SPECIFIC PERSON
		api.removePerson("321na");
		
		// ADD PERSON (Adding all test datar)
		for (Persons p : this.phub.getCollection()) {
			api.addPerson(p.getId(), p.getFirstName(), p.getLastName(), p.getAddress().getStreet(), 
					p.getAddress().getCity(), p.getAddress().getState(), p.getAddress().getZip(), 
					p.getAddress().getCountry());
		}
		
		// ADD EMAILS
		for (Persons p : this.phub.getCollection()) {
			for (String e : p.getEmails()) {
				api.addEmail(p.getId(), e);
			}
		}
		
		// REMOVE ALL CUSTOMERS (COMPANIES)
		api.removeAllCustomers();
		
		// ADD A CUSTOMER
		for (Customer c : this.chub.getCustomerList()) {
			api.addCustomer(c.getCode(), c.getType()+"", c.getHumanRep().getId(), c.getName(), 
					c.getAddress().getStreet(),	c.getAddress().getCity(), c.getAddress().getState(), 
					c.getAddress().getZip(), c.getAddress().getCountry());
		}
		
		// REMOVE ALL PRODUCTS
		api.removeAllProducts();
		
		// REMOVE SPECIFIC PRODUCTS
		api.removeProduct("ff23");
		
		// ADD EQUIPMENT
		for (Equipment e : this.prhub.getEquipList()) {
			api.addEquipment(e.getCode(), e.getName(), e.getUnitPrice());
		}
		
		// ADD LICENSE
		for (License l : this.prhub.getLiceList()) {
			api.addLicense(l.getCode(), l.getName(), l.getPrice(), l.getAnnualPrice());
		}
		
		// ADD CONSULTATION
		for (Consultation c : this.prhub.getConsultList()) {
			api.addConsultation(c.getCode(), c.getName(), c.getHumanRep().getId(), c.getHourPrice());
		}
		
		// REMOVE ALL INVOICES
		api.removeAllInvoices();
		
		// REMOVE SPECIFIC INVOICE
		api.removeInvoice("INV001");
		
		//ADD INVOICE
		for(Invoices i : this.ihub.getCollection()) {
			api.addInvoice(i.getCode(), i.getCustomCode(), i.getSalesCode());
		} 
		
		// ADD EQUIPMENT TO AN INVOICE
		for (Invoices i : this.ihub.getCollection()) {
			for (Equipment e : i.getProducts().getEquipList()) {
				api.addEquipmentToInvoice(i.getCode(), e.getCode(), e.getInvUnits());
			}
		} 
		
		// ADD LICENSE TO AN INVOICE
		for (Invoices i : this.ihub.getCollection()) {
			for (License lulz : i.getProducts().getLiceList()) {
				api.addLicenseToInvoice(i.getCode(), lulz.getCode(), lulz.getStartDate(), lulz.getEndDate());
			}
		}
		
		// ADD CONSULTATION TO AN INVOICE
		for (Invoices i : this.ihub.getCollection()) {
			for (Consultation see : i.getProducts().getConsultList()) {
				api.addConsultationToInvoice(i.getCode(), see.getCode(), see.getHours());
			}
		}	
	}
	
	public void stahp() {
		api.closeDB();
	}
}
