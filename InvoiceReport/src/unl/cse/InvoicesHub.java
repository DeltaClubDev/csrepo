package unl.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Interface for the Invoices
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.5.0
 */

public class InvoicesHub {
	
	private final List<Invoices> invoicesList;
	
	/**
	 * Creates an ArrayList for <b>Invoice</b> Object
	 */
	public InvoicesHub() {
		this.invoicesList = new ArrayList<Invoices>();
	}
	
	/**
	 * @param newInvoices - Must be an instance of <b>Invoices</b> Object, will be added to
	 * the hub.
	 */
	public void addInvoices(Invoices newInvoices) {
		this.invoicesList.add(newInvoices);
	}
	
	/**
	 * @return - Returns a List of Invoices
	 */
	public List<Invoices> getCollection() {
    	return Collections.unmodifiableList(this.invoicesList);
	}
}