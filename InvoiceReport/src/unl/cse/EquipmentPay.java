package unl.cse;

public class EquipmentPay {
	private String code;
	private int units;
	private double total;
	private double taxed;
	private double cost;
	private String info;
	
	public EquipmentPay() {}

	public void setCode(String code) {
		this.code = code;
	}

	public void setUnits(int units) {
		this.units = units;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public void setTaxed(double taxed) {
		this.taxed = taxed;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getCode() {
		return this.code;
	}

	public int getUnits() {
		return this.units;
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