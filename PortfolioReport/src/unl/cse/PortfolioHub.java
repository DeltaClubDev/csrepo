package unl.cse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PortfolioHub {
private final List<Portfolio> portfolioCollection;
	
	public PortfolioHub() {
		this.portfolioCollection = new ArrayList<Portfolio>();
	}
	
	public List<Portfolio> getCollection() {
    	return Collections.unmodifiableList(this.portfolioCollection);
	}
	
	public void addPortfolio(Portfolio newPortfolio) {
		this.portfolioCollection.add(newPortfolio);
	}
	
	public List<Name> nameMatch(String owner) {
    	List<Name> results = new ArrayList<Name>();
		for(Portfolio p : this.portfolioCollection) {
    		if(owner.equals(p.getCode())) {
    		} else {
    		}
    	}
    	return results;
    }
}