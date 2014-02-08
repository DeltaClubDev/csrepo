package unl.cse;

/*
 * Assets Object serialize function for XML
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root", namespace = "unl.cse")
public class AssetsHub {

	private final List<Assets> assetsCollection;
	
	public AssetsHub() {
		this.assetsCollection = new ArrayList<Assets>();
	}
	
	@XmlElementWrapper(name = "assets")
	@XmlElement(name = "asset")
	public List<Assets> getCollection() {
    	return Collections.unmodifiableList(this.assetsCollection);
	}
	
	public void addAssets(Assets newAssets) {
		this.assetsCollection.add(newAssets);
	}
}