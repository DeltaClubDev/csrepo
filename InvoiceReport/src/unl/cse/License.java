package unl.cse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <b>License</b> is a very simple product that requires no objects
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.5.0
 */

public class License {

	private int primaryKey;
	private String code;
	private String name;
	private double price;
	private double annualPrice;
	private String startDate;
	private String endDate;
	private int days;
	private double total;
	private double taxed;
	private double cost;
	private String info;
	
	
	public License() {}
	
	public void setPrimaryKey(int key) {
		this.primaryKey = key;
	}
	
	/**
	 * @param code - Primary indicator for a license, requires String
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
	 * @param price - Accepts a String, however parses to a Double.
	 * Note that if their is a failure in parsing, the default setting
	 * is 0.0
	 */
	public void setPrice(String price) {
		try {
			this.price = Double.parseDouble(price.trim());
		} catch (Exception e) {
			e.printStackTrace();
			this.price = 0.0;
		}
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * 
	 * @param annualPrice - Accepts a String, however parses to a Double.
	 * Note that if their is a failure in parsing, the default setting
	 * is 0.0
	 */
	public void setAnnualPrice(String annualPrice) {
		try {
			this.annualPrice = Double.parseDouble(annualPrice.trim());
		} catch (Exception e) {
			e.printStackTrace();
			this.annualPrice = 0.0;
		}
	}
	
	public void setAnnualPrice(double annualPrice) {
		this.annualPrice = annualPrice;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public void setDays() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    try {
		    Date start = sdf.parse(this.getStartDate());
		    Date end = sdf.parse(this.getEndDate());
		    Calendar startCal = Calendar.getInstance();
		    startCal.setTime(start);
		    Calendar endCal = Calendar.getInstance();
		    endCal.setTime(end);
		    double endTime = end.getTime();
		    double startTime = start.getTime();
		    double betweenTime = endTime - startTime;
		    this.days = (int)Math.round((betweenTime) / (1000 * 60 * 60 * 24));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	this.days = 0;
	    }
	}
	
	public void setTotal() {
		this.total = ((this.getDays() * this.getAnnualPrice()) / 365);
	}
	
	public void setTaxed(boolean fee) {
		if (fee == false) {
			this.taxed = this.getTotal() * 0.0425;
		} else {
			this.taxed = 0;
		}
	}
	
	public void setCost() {
		this.cost = this.getTotal() + this.getTaxed() + this.getPrice();
	}
	
	public void setInfo() {
		this.info = this.getDays()+" days at $"+this.getAnnualPrice()+"/yr";
	}
	
	public int getPrimaryKey() {
		return this.primaryKey;
	}
	
	/**
	 * 
	 * @return - Returns a String
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * 
	 * @return - Returns a String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @return - Returns a Double
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * @return - Returns a Double
	 */
	public double getAnnualPrice() {
		return this.annualPrice;
	}
	
	public String getStartDate() {
		return this.startDate;
	}
	
	public String getEndDate() {
		return this.endDate;
	}
	
	public int getDays() {
		return this.days;
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
}