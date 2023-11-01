
public class Product {
	
	private String id;
	private String quantity;
	private String price;
	private String customers;
	private float av_price;

	public Product() {
		
	}
	
	public Product(String id, String quantity, String price, String customers) {
		int p = Integer.parseInt(price), q = Integer.parseInt(quantity);
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.customers = customers;
		av_price = (float) p/q;
	}
	
	public String getId() {
		return id;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public String getCustomers() {
		return customers;
	}
	
	public String getAvprice() {
		return String.valueOf(av_price);
	}
	
}
