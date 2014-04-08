package unl.cse;

/**
 * <b>Equipment</b> is a very simple product that requires no objects
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.1.0
 */

public class Equipment {
	
	private int primaryKey;
	private String code;
	private String name;
	private double unitPrice;
	private int invUnits;
	private double total;
	private double taxed;
	private double cost;
	private String info;
	
	public Equipment() { }

	public void setPrimaryKey(int key) {
		this.primaryKey = key;
	}
	
	/**
	 * @param code - Primary indicator for an equipment, requires String
	 */
	public void setCode(String code) {
		this.code = code.trim();
	}
	
	/**
	 * @param name - Accepts a String
	 */
	public void setName(String name) {
		this.name = name.trim();
	}
	
	/**
	 * 
	 * @param unitPrice - Accepts a String, however parses to a Double.
	 * Note that if their is a failure in parsing, the default setting
	 * is 0.0
	 */
	public void setUnitPrice(String unitPrice) {
		try {
			this.unitPrice = Double.parseDouble(unitPrice.trim());
		} catch (Exception e) {
			e.printStackTrace();
			this.unitPrice = 0.0;
		}
	}
	
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setInvUnits(String invUnits) {
		try {
			this.invUnits = Integer.parseInt(invUnits);
		} catch (Exception e) {
			e.printStackTrace();
			this.invUnits = 0;
		}
	}
	
	public void setTotal() {
		this.total = this.getUnitPrice() * this.getInvUnits();
	}
	
	public void setTaxed(boolean fee) {
		if (fee == false) {
			this.taxed = this.getTotal() * 0.07;
		} else {
			this.taxed = 0.0;
		}
	}
	
	public void setCost() {
		this.cost = this.getTotal() + this.getTaxed();
	}
	
	public void setInfo() {
		this.info = this.getInvUnits()+" units at $"+this.getUnitPrice()+"/unit";
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
	public double getUnitPrice() {
		return this.unitPrice;
	}
	
	public int getInvUnits() {
		return this.invUnits;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public double getTaxed() {
		return this.taxed;
	}
	
	public String getInfo() {
		return this.info;
	}
}