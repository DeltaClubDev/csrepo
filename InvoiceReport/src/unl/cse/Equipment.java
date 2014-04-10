package unl.cse;

/**
 * <b>Equipment</b> Models the equipment for business and use
 * in our data base.. and invoice stuffs
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.5.0
 */

public class Equipment {
	private double cost;
	private int primaryKey;
	private String code;
	private String name;
	private double unitPrice;
	private int invUnits;
	private double total;
	private double taxed;
	private String info;
	
	public Equipment() { }

	/**
	 * @param key - Primary key of an Equipment (Product) in database
	 */
	public void setPrimaryKey(int key) {
		this.primaryKey = key;
	}
	
	/**
	 * @param code - Old system key reference
	 */
	public void setCode(String code) {
		this.code = code.trim();
	}
	
	public void setName(String name) {
		this.name = name.trim();
	}

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
	
	public String getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}

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
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}