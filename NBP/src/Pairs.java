
public class Pairs {
	
	private String product1 = "";
	private String product2 = "";
	private int purchases = 0;

	public Pairs() {
		
	}
	
	public Pairs(String product1, String product2, int purchases) {
		this.product1 = product1;
		this.product2 = product2;
		this.purchases = purchases;
	}
	
	public String getPair() {
		return product1 + " " + product2;
	}
	
	public int getPurchases() {
		return purchases;
	}
	
}
