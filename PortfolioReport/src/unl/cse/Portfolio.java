package unl.cse;

public class Portfolio {
	
	private String code;
	private String owner;
	private String manager;
	private String benefit;
	private String assets[][];
	private double totFees;
	private double totCommission;
	private double weightOmega;
	private double totReturn;
	private double totAssets;

	public Portfolio (String code, String owner, String manager, String benefit, String assets[][]) {
		this.code = code;
		this.owner = owner;
		this.manager = manager;
		this.benefit = benefit;
		this.assets = assets;
	}

	public String getCode() {
		return code;
	}

	public String getOwner() {
		return owner;
	}

	public String getManager() {
		return manager;
	}
	
	public String getBenefit() {
		return benefit;
	}

	public double getTotFees() {
		return totFees;
	}

	public double getTotCommission() {
		return totCommission;
	}

	public double getWeightOmega() {
		return weightOmega;
	}

	public double getTotReturn() {
		return totReturn;
	}

	public double getTotAssets() {
		return totAssets;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

	public void setTotFees(double totFees) {
		this.totFees = totFees;
	}

	public void setTotCommission(double totCommission) {
		this.totCommission = totCommission;
	}

	public void setWeightOmega(double weightOmega) {
		this.weightOmega = weightOmega;
	}

	public void setTotReturn(double totReturn) {
		this.totReturn = totReturn;
	}

	public void setTotAssets(double totAssets) {
		this.totAssets = totAssets;
	}
}