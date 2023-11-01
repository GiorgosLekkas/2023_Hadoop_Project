import java.util.Comparator;

public class CompareProducts implements Comparator<Product>{
	
	public int compare(Product p1,Product p2){
		
		if(Integer.parseInt(p1.getQuantity())==Integer.parseInt(p2.getQuantity()))
			if(Integer.parseInt(p1.getCustomers())==Integer.parseInt(p2.getCustomers()))
				return 0;
			else if(Integer.parseInt(p1.getCustomers())>Integer.parseInt(p2.getCustomers()))
				return -1;  
			else  
				return 1;
		else if(Integer.parseInt(p1.getQuantity())>Integer.parseInt(p2.getQuantity()))
			return -1;  
		else  
			return 1;
	}

}
