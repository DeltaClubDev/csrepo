package unl.cse;

/*
 * Assets main Object file, has 3 extended classes
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "asset")
@XmlSeeAlso({Deposits.class, Stocks.class, PrivetInvest.class})
public abstract class Assets {
	private String code;
	private String label;
	private String type;
	
	public Assets(String code, String label, String type) {
		this.code = code;
		this.label = label;
		this.type = type;
	}
	
	public Assets() {}

	public void setCode(String code) {
		this.code = code;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name = "code")
	public String getCode() {
		return code;
	}

	@XmlElement(name = "label")
	public String getLabel() {
		return label;
	}

	@XmlElement(name = "type")
	public String getType() {
		return type;
	}
	
}