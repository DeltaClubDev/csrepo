package unl.cse;

/*
 * Persons Object serialize function for XML
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root", namespace = "unl.cse")
public class PersonsHub {
	
	@XmlElementWrapper(name = "people")
	@XmlElement(name = "person")
	private final List<Persons> personsCollection;
	
	public PersonsHub() {
		this.personsCollection = new ArrayList<Persons>();
	}
	
	public List<Persons> getCollection() {
    	return Collections.unmodifiableList(this.personsCollection);
	}
	
	public void addPersons(Persons newPersons) {
		this.personsCollection.add(newPersons);
	}
}
