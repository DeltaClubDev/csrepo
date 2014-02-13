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
	private final List<Persons> personsList;
	
	public PersonsHub() {
		this.personsList = new ArrayList<Persons>();
	}
	
	public List<Persons> getCollection() {
    	return Collections.unmodifiableList(this.personsList);
	}
	
	public void addPersons(Persons newPersons) {
		this.personsList.add(newPersons);
	}
}