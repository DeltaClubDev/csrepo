package unl.cse;

/*
 * Extension from the Assets Object
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deposits")
public class Deposits extends Assets {

	private String apr;
	
	public Deposits(String code, String label, String type, String apr) {
		super(code, label, type);
		this.apr = apr;
	}
	
	public Deposits() {}

	public void setApr(String apr) {
		this.apr = apr;
	}

	@XmlElement(name = "apr")
	public String getApr() {
		return apr;
	}
}