package unl.cse;

import java.util.Comparator;


public class Comparing {
	private int EQUALS = 0;
	private int BEFORE = 7;
	private int AFTER = -7;

	private Comparator<Invoices> byInvoiceCustomer = new Comparator<Invoices>(){
		
		@Override
		public int compare(Invoices o1, Invoices o2) {
			int compared = o1.getCustomer().getName().compareTo(o2.getCustomer().getName());
			int result = 0;
			if (compared==0)
				result = EQUALS; //WE DONT REALLY NEED THIS..?
			if (compared>0)
				result = BEFORE;
			if (compared<0)
				result = AFTER;
			return result;
		}
	};
	
	private Comparator<Invoices> byInvoiceTotal = new Comparator<Invoices>(){
		
		@Override
		public int compare(Invoices o1, Invoices o2) {;
			int result = 0;
			if (o1.getProducts().getCost(o1.getHasComplyFee()) ==(o2.getProducts().getCost(o2.getHasComplyFee())))
				result = EQUALS; //WE DONT REALLY NEED THIS..?
			if (o1.getProducts().getCost(o1.getHasComplyFee()) ==(o2.getProducts().getCost(o2.getHasComplyFee())))
				result = BEFORE;
			if (o1.getProducts().getCost(o1.getHasComplyFee()) ==(o2.getProducts().getCost(o2.getHasComplyFee())))
				result = AFTER;
			return result;
		}
	};
		

	

}
