package unl.cse;

public class Invoices{
	private int primaryKey;
	private int customerID;
	private int personID;
	private String code;
	private String customCode;
	private String salesCode;
	private char type;
	boolean hasComplyFee;
	private ProductsHub prhub = new ProductsHub();
	private Customer customer;
	private Persons human;
	
	public Invoices() { }
	
	public void setPerson (Persons p) {
		this.human = p;
	}
	
	public void setCustomer (Customer c) {
		this.customer = c;
	}
	
	public void setCode(String code) {
		if ((code != null) && (!code.trim().isEmpty())) {
			this.code = code.trim();
		} else {
			this.code = "N/A";
		}
	}
	
	public void setCustomCode(String code) {
		this.customCode = code;
	}
	
	public void setSalesCode(String code) {
		this.salesCode = code;
	}
	
	public void setType(char type) {
		this.type = type;
	}
	
	public void setHasComplyFee(boolean truth) {
		this.hasComplyFee = truth;
	}
	
	/**
	 * Sets equipment to invoice
	 * @param e
	 */
	public void setEquipment(Equipment e, String numUnits, int invID) {
		Equipment newE = new Equipment();
		if (invID >= 0) {
			newE.setPrimaryKey(invID);
		} else {
			newE.setPrimaryKey(-1);
		}
		newE.setUnitPrice(e.getUnitPrice());
		newE.setCode(e.getCode());
		newE.setName(e.getName());
		newE.setInvUnits(numUnits);
		newE.setTotal();
		newE.setTaxed(this.getHasComplyFee());
		newE.setCost();
		newE.setInfo();
		prhub.addEquipment(newE);
	}
	
	public void setLicense(License l, String startDate, String endDate, int invID) {
		License newL = new License();
		if (invID >= 0) {
			newL.setPrimaryKey(invID);
		} else {
			newL.setPrimaryKey(-1);
		}
		newL.setCode(l.getCode());
		newL.setName(l.getName());
		newL.setAnnualPrice(l.getAnnualPrice());
		newL.setPrice(l.getPrice());
		newL.setStartDate(startDate);
		newL.setEndDate(endDate);
		newL.setDays();
		newL.setTotal();
		newL.setTaxed(this.getHasComplyFee());
		newL.setCost();
		newL.setInfo();
		prhub.addLicense(newL);
	}
	
	public void setConsultant(Consultation c, String hours,int invID) {
		Consultation newC = new Consultation();
		if (invID >= 0) {
			newC.setPrimaryKey(invID);
		} else {
			newC.setPrimaryKey(-1);
		}
		newC.setCode(c.getCode());
		newC.setName(c.getName());
		newC.setHourPrice(c.getHourPrice());
		newC.addHumanRep(c.getHumanRep());
		newC.setHours(hours);
		newC.setTotal();
		newC.setTaxed(this.getHasComplyFee());
		newC.setCost();
		newC.setInfo();
		prhub.addConsutation(newC);
	}
	
	// Getters
	public String getCode() {
		return this.code;
	}
	
	public String getCustomCode() {
		return this.customCode;
	}
	
	public String getSalesCode() {
		return this.salesCode;
	}
	
	public char getType() {
		return this.type;
	}
	
	public boolean getHasComplyFee() {
		return this.hasComplyFee;
	}
	
	public ProductsHub getProducts() {
		return this.prhub;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public int getCustomerID() {
		return customerID;
	}

	public int getPersonID() {
		return personID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}
	
	public Persons getPerson() {
		return this.human;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
}