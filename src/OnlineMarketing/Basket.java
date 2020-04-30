package OnlineMarketing;

import java.util.HashMap;
import java.util.Map;

public class Basket {
	private double cargoCost;
	private Inventory<Product> inventory; // it has its own inventory 
	
	public Basket() {
		inventory=new ProductInventory<Product>();
		this.setCargoCost(0);
	}
	public void addProductToBasket(Product product,int quantity) {
		inventory.addElementToInventory(product, quantity);
	}
	public void removeProductFromBasket(Product product,int quantity) {
		inventory.removeElementFromInventory(product, quantity);
	}
	public double costOfItemsInBasket() {//we need method to calculate all cost of basket
		return inventory.costOfItemsInInventory();
	}
	public double getCargoCost() {
		return cargoCost;
	}
	public void setCargoCost(double cargoCost) {
		this.cargoCost = cargoCost;
	}
	public void totalCargoCost() {//we need method to calculate all cargo cost of basket
		double cost=inventory.cargoCostOfItemsInInventory();
		this.setCargoCost(cost);
	}
	public HashMap<Product,Integer> getInventory() {
		return inventory.getInventory();
		
	}
}
