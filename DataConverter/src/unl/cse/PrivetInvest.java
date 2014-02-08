package unl.cse;

/*
 * Extension from the Assets Object
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "private_investment")
public class PrivetInvest extends Assets{

	private String qrtDividen;
	private String bRateReturn;
	private String omega;
	private String totVal;
	
	public PrivetInvest(String code, String label, String type, String qrtDividen, String bRateReturn, String omega, String totVal) {
		super(code, label, type);
		this.qrtDividen = qrtDividen;
		this.bRateReturn = bRateReturn;
		this.omega = omega;
		this.totVal = totVal;
	}
	
	public PrivetInvest() {}

	public void setQrtDividen(String qrtDividen) {
		this.qrtDividen = qrtDividen;
	}

	public void setbRateReturn(String bRateReturn) {
		this.bRateReturn = bRateReturn;
	}

	public void setOmega(String omega) {
		this.omega = omega;
	}

	public void setTotVal(String totVal) {
		this.totVal = totVal;
	}

	@XmlElement(name = "quarter_dividen")
	public String getQrtDividen() {
		return qrtDividen;
	}

	@XmlElement(name = "base_rate_return")
	public String getbRateReturn() {
		return bRateReturn;
	}

	@XmlElement(name = "omega")
	public String getOmega() {
		return omega;
	}

	@XmlElement(name = "total_value")
	public String getTotVal() {
		return totVal;
	}

}
