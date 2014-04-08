package unl.cse;

import java.util.ArrayList; 
import java.util.Collections;
import java.util.List;

/**
 * <b>CustomersHub</b> Is the main Handler for interfacing with the
 * <b>GovComp</b> and <b>PubComp</b> objects. Currently, their is no
 * difference between the two, and perhaps might be merged into one
 * company class. For now, it is easier to have the separate classes
 * to parse XML data.
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.5.0
 */

public class CustomersHub {

	private final List<Customer> customerList;
	/**
	 * The <b>CustomersHub</b> constructor creates an <b>ArrayList</b> for 
	 * each Company type. This is where each instance can be stored for
	 * later use.
	 */
	public CustomersHub() {
		this.customerList = new ArrayList<Customer>();
	}
	
	/**
	 * 
	 * @param newGovList - Adds an instance of <b>GovComp</b> to the hub.
	 */
	public void addCustomer(Customer newCustomer) {
		this.customerList.add(newCustomer);
	}

	/**
	 * 
	 * @return - Returns an <i>unmodifiableList</i> of all the <b>GovComp</b> instances
	 */
	public List<Customer> getCustomerList() {
		return Collections.unmodifiableList(this.customerList);
	}
	
	public boolean isThere(String code) {
		for(Customer c : this.customerList) {
			if (c.getCode().equals(code.trim())) {
				return true;
			}
		} return false;
	}
	
	public Customer getCustomerByCode(String code) {
		for(Customer c : this.customerList) {
			if (c.getCode().equals(code.trim())) {
				return c;
			}
		} return null;
	}
	
	public String getNameByCode(String code) {
		boolean entered = false;
		for(Customer c : this.customerList) {
			entered = true;
			if (c.getCode().equals(code.trim())) {
				return c.getName();
			}
		} return "N/A";
	}
}