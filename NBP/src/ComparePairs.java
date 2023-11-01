import java.util.Comparator;

public class ComparePairs implements Comparator<Pairs>{
	
	public int compare(Pairs p1,Pairs p2){
		
		if(p1.getPurchases()==p2.getPurchases())
			return 0;
		else if(p1.getPurchases()>p2.getPurchases())
			return -1;  
		else  
			return 1;
	}

}
