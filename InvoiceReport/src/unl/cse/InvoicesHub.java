package unl.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Main Persons class to interface with the Persons data
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.1.0
 */

@XmlRootElement(name = "invoices", namespace = "unl.cse")
public class InvoicesHub {
	
	@XmlElement(name = "invoices")
	private final List<Invoices> invoicesList;
	
	/**
	 * Creates an ArrayList for <b>Persons</b> Object
	 */
	public InvoicesHub() {
		this.invoicesList = new ArrayList<Invoices>();
	}
	
	/**
	 * 
	 * @param newInvoices - Must be an instance of <b>Invoices</b> Object, will be added to
	 * the hub.
	 */
	
	public void addInvoices(Invoices newInvoices) {
		this.invoicesList.add(newInvoices);
	}
	
	/**
	 * 
	 * @return - Gives the entire <b>InvoicesHub</b> Object as an 
	 * <i>unmodifiableList</i>
	 */
	public List<Invoices> getCollection() {
    	return Collections.unmodifiableList(this.invoicesList);
	}
}