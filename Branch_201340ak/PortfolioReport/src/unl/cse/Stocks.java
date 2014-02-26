package unl.cse;

/*
 * Extension from the Assets Object
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stocks")
public class Stocks extends Assets{

	private String qrtDividen;
	private String bRateReturn;
	private String omega;
	private String stockSymb;
	private String sharePrice;
	
	public Stocks(String code, String label, String type, String qrtDividen, String bRateReturn, String omega, String stockSymb, String sharePrice) {
		super(code, label, type);
		this.qrtDividen = qrtDividen;
		this.bRateReturn = bRateReturn;
		this.omega = omega;
		this.stockSymb = stockSymb;
		this.sharePrice = sharePrice;
	}
	
	public Stocks() {}

	public void setQrtDividen(String qrtDividen) {
		this.qrtDividen = qrtDividen;
	}

	public void setbRateReturn(String bRateReturn) {
		this.bRateReturn = bRateReturn;
	}

	public void setOmega(String omega) {
		this.omega = omega;
	}

	public void setStockSymb(String stockSymb) {
		this.stockSymb = stockSymb;
	}

	public void setSharePrice(String sharePrice) {
		this.sharePrice = sharePrice;
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

	@XmlElement(name = "stock_symbol")
	public String getStockSymb() {
		return stockSymb;
	}

	@XmlElement(name = "share_price")
	public String getSharePrice() {
		return sharePrice;
	}
}