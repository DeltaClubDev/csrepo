package unl.cse;

import java.util.List; 
import java.util.ArrayList;
import java.util.Collections;

/**
 * <b>Consultation</b> is a type of product where the instances live in
 * the <b>ProductsHub</b> class. This class will make use of the
 * <b>Persons</b> object as someone has to be a consultant.
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.5.0
 */

public class Consultation {
	private int primaryKey;
	private int personsID;
	private String code;
	private String name;
	private double hourPrice;
	private Persons humanRep;
	private double hours;
	private double total;
	private double taxed;
	private double cost;
	private String info;
	
	/**
	 * 
	 * @param code - Primary indicator for a consultation, requires String
	 */
	public void setCode(String code) {
		this.code = code.trim();
	}
	
	/**
	 * 
	 * @param name - Accepts a String
	 */
	public void setName(String name) {
		this.name = name.trim();
	}
	
	/**
	 * 
	 * @param hourPrice - Accepts a String, however parses to a Double.
	 * Note that if their is a failure in parsing, the default setting
	 * is 0.0
	 */
	public void setHourPrice(String hourPrice) {
		try {
			this.hourPrice = Double.parseDouble(hourPrice.trim());
		} catch (Exception e) {
			e.printStackTrace();
			this.hourPrice = 0.0;
		}
	}
	
	public void setHourPrice(double hourPrice) {
		this.hourPrice = hourPrice;
	}
	
	/**
	 * 
	 * @param consult - Accepts the <b>Persons</b> instance of the
	 * corresponding id to indicate which person if any owns this
	 * consultation
	 */
	public void addHumanRep(Persons alien) {
		this.humanRep = alien;
	}
	
	public void setHours(String hours) {
		try {
			this.hours = Double.parseDouble(hours.trim());
		} catch (Exception e) {
			e.printStackTrace();
			this.hours = 0.0;
		}
	}
	
	public void setTotal() {
		this.total = this.getHours() * this.getHourPrice();
	}
	
	public void setTaxed(boolean fee) {
		if (fee == false) {
			this.taxed = this.getTotal() * 0.0425;
		} else {
			this.taxed = 0.0;
		}
	}
	
	public void setCost() {
		this.cost = this.getTotal() + this.getTaxed();
	}
	
	public void setInfo() {
		this.info = this.getHours()+" hrs at $"+this.getHourPrice()+"/hr";
	}
	
	/**
	 * 
	 * @return - Returns a String
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 
	 * @return - Returns a String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return - Returns a Double
	 */
	public double getHourPrice() {
		return hourPrice;
	}
	
	/**
	 * 
	 * @return - Returns the consultant which is a <b>Persons</b> object
	 */
	public Persons getHumanRep() {
		return this.humanRep;
	}
	
	public double getHours() {
		return this.hours;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public double getTaxed() {
		return this.taxed;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public String getInfo() {
		return this.info;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public int getPersonsID() {
		return personsID;
	}

	public void setPersonsID(int personsID) {
		this.personsID = personsID;
	}
}