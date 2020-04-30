package OnlineMarketing;

public class Product {
	private String category;
	private String name;
	private double price;
	private double weight;
	private int quantity;
	private double cargoPrice;
	public Product(String category, String name, double price, double weight, int quantity) {
		this.category = category;
		this.name = name;
		this.price = price;
		this.weight = weight;
		this.quantity = quantity;
		this.cargoPrice = weight*2;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getCargoPrice() {
		return cargoPrice;
	}
	public void setCargoPrice(double cargoPrice) {
		this.cargoPrice = cargoPrice;
	}
	
}
