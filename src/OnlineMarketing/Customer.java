package OnlineMarketing;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Customer extends RegularUser{
	private Basket basket;
	private Inventory<Product> boughtProducts;
	public Customer(String username, String password, double balance, ContactInfo info) {
		super(username, password, balance, info);
		basket=new Basket();
		boughtProducts=new ProductInventory<Product>();
	}
	public HashMap<Product,Integer> getBasketInventory() {
		return basket.getInventory();
	}
	public void addProductToBasket(Product product,int quantity) {
		basket.addProductToBasket(product, quantity);
	}
	public void removeProductFromBasket(Product product,int quantity) {
		basket.removeProductFromBasket(product, quantity);
	}
	
	public void addProductToBoughtProducts(Product product,int quantity) {
		boughtProducts.addElementToInventory(product, quantity);
	}
	public double payProduct(Product product,int quantity) {//money part of trade happens here
		double cargoPrice;
		basket.totalCargoCost();//basket cargo cost is calculated and setted
		double totalPrice=basket.costOfItemsInBasket();//total price of products in the basket calculated
		if(totalPrice>2000) { //if total price is greater than 2000 cargo is free
			cargoPrice=0;
		}
		else {
			cargoPrice=product.getCargoPrice()*quantity;;
		}
		double totalCostProduct=cargoPrice+product.getPrice()*quantity;
		this.setBalance(this.getBalance()-totalCostProduct);
		return totalCostProduct; //returns total cost at the end
	}
	public Product  returnProduct(Product product,int quantity) {//it removes quantity of product and return that product
		boughtProducts.removeElementFromInventory(product, quantity);
		return  product;
	}
	public void customersBoughtProducts() {// in order to print bought products by customer
		int count=1;
		if(boughtProducts.getInventory().isEmpty()) {
			System.out.println("There is no bought product");
			return;
		}
		for(Entry<Product, Integer> product:boughtProducts.getInventory().entrySet()) {
			System.out.println(count+")"+" " +product.getKey().getName()+" quantity: "+product.getValue()+"\n");
			count++;
		}
	}
	public Product getBoughtProduct(int index) {//In order to choose product from bought products
		int count=1;
		if(boughtProducts.getInventory().isEmpty()) {
			System.out.println("There is no bought product");
			return null;
		}
		for(Entry<Product, Integer> product:boughtProducts.getInventory().entrySet()) {
			if(count==index) {
				return product.getKey();
			}
			count++;
		}
		return null;
	}
	public void customersBasket() {
		int count=1;
		if(basket.getInventory().isEmpty()) {
			System.out.println("Basket is empty!!");
		}
		for(Entry<Product, Integer> product:basket.getInventory().entrySet()) {
			System.out.println(count+")"+" " +product.getKey().getName()+" quantity: "+product.getValue()+"\n");
			count++;
		}
	}
	public Product getProductFromBasket(int index) {
		int count=1;
		if(basket.getInventory().isEmpty()) {
			System.out.println("Basket is empty!!");
			return null;
		}
		for(Entry<Product, Integer> product:basket.getInventory().entrySet()) {
			if(count==index) {
				return product.getKey();
			}
			count++;
		}
		return null;
	}
	
}

//önce objeyi sepete ekle
//sonra payment yapýldýðýnda baskettan bir elemaný al,onu supplierdan sil ve sonra onu bought products a obje yaratýp ekle