package unl.cse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The <b>Address</b> class was designed to be used for multiple types
 * of objects. This is because Companies, and People will have addresses.
 * All values in this Object run risks of being null!
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.1.0
 */
@XmlRootElement(namespace = "unl.cse")
@XmlType(propOrder = {"inCode", "customCode", "salesCode", "prodList"}) //invoice Code, customer code, salesperson code, product list
public class Invoices {
	private String inCode;
	private String customCode;
	private String salesCode;
	private String salesFirstName;
	private String salesLastName;
	
	
	@XmlElementWrapper(name = "products")
	@XmlElement(name = "product")
	private List<String> prodList = new ArrayList<String>();
	
	public Invoices(){}
	
	public Invoices(List<String> prodList2){
		for(String s : prodList2) {
			List<String> pList = Arrays.asList(s.split(":"));
			if(pList.size() == 3) {
				
			}
		}
	}

	/**
	 * 
	 * @return - Returns a String
	 */
	public String getInCode() {
		return inCode;
	}
	
	/**
	 * 
	 * @param inCode - Accepts a String
	 */
	@XmlElement(name = "inCode")
	public void setInCode(String inCode) {
		if ((inCode != null) && (!inCode.trim().isEmpty())) {
			this.inCode = inCode.trim();
		} else {
			this.inCode = "N/A";
		}
	}
	/**
	 * 
	 * @return - Returns a String
	 */
	public String getCustomCode() {
		return customCode;
	}
	/**
	 * 
	 * @param customCode - Accepts a String
	 */
	@XmlElement(name = "customCode")
	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}
	/**
	 * 
	 * @return - Returns a String
	 */
	public String getSalesCode() {
		return salesCode;
	}
	/**
	 * 
	 * @param salesCode - Accepts a String
	 */
	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}
	/**
	 * 
	 * @return - Returns an <b>ArrayList</b> of products
	 */
	public List<String> getProdList() {
		return Collections.unmodifiableList(this.prodList);
	}
	/**
	 * <i>setProdList</i> will take an array of Strings and add them into an <b>ArrayList</b>.
	 * An invoice can have any number of products or none at all, if their are none "N/A" is indicated.
	 * 
	 * @param emails - Will take an array of products
	 */
	public void setProdList(List<String> prodList2) {
		for (String s : prodList2) {
			List<String> pList = Arrays.asList(s.split(":"));
			if(pList.size() == 3){
				
			}
		}
	}
}
