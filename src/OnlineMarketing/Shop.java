package OnlineMarketing;

import java.util.Map.Entry;

public class Shop {
	private String category;
	private String title;
	private String taxNumber;
	private Inventory<Product> inventory;
	public Shop(String category,String title,String taxNumber) {
		inventory=new ProductInventory<Product>();
		this.category=category;
		this.title=title;
		this.taxNumber=taxNumber;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTaxNumber() {
		return taxNumber;
	}
	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	public void addProductToShop(Product product,int quantity) {
		inventory.addElementToInventory(product, quantity);
	}
	public void removeProductFromShop(Product product,int quantity) {
		inventory.removeElementFromInventory(product, quantity);
	}
	public Product findProduct(String product) throws ProductNotFoundException {
		return inventory.findProduct(product);
	}
}
