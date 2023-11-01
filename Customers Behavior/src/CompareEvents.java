import java.util.Comparator;

public class CompareEvents implements Comparator<Events>{
	
	public int compare(Events e1,Events e2){
		
		if(e1.getNumber()==e2.getNumber())
			return 0;
		else if(e1.getNumber()>e2.getNumber())
			return -1;  
		else  
			return 1;
	}

}
