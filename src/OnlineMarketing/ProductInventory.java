package OnlineMarketing;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ProductInventory<T extends Product> extends Inventory<T>{	
	public ProductInventory() {
		inventory=new HashMap<T,Integer>();
	}
	public Product findProduct(String product) throws ProductNotFoundException{// in order to find product from inventory
		for(Map.Entry<T, Integer> products: inventory.entrySet()) {
			if(products.getKey().getName().equals(product)) {
				return products.getKey();
			}
		}
		return null;
		
	}
	@Override
	//in order to element a Generic type product to inventory
	public void addElementToInventory(T product, int quantity) {
		if(inventory.keySet().contains(((T) product))) {
			inventory.replace((T)  product, inventory.get(((T) product))+quantity);
		}
		else {
			inventory.put((T) product, quantity);
		}
	}
	@Override
	//in order to remove a generic type element from inventory 
	public boolean removeElementFromInventory(T product, int quantity) {
		boolean bool=false;
		int currentQuantity=inventory.get((T) product);
		if(currentQuantity<=quantity) {
			inventory.remove((T) product);//if the amount that wanted to remove is more than quantity exist
										//it deletes it from map
			bool= true;
		}
		else {
			inventory.replace((T) product,currentQuantity-quantity);//otherwise the quantity is just changed
			bool= true;
		}
		return bool;
	}
	public double costOfItemsInInventory() { //totalcost of inventory
		double cost=0;
		for(Entry<T, Integer> inventory:inventory.entrySet()) {
			cost+=inventory.getKey().getPrice()*inventory.getValue();
		}
		return cost;
	}
	public double cargoCostOfItemsInInventory() {//totalcargo cost of inventory
		double cargoCost=0;
		for(Entry<T, Integer> products:inventory.entrySet()) {
			cargoCost+=products.getKey().getCargoPrice()*products.getValue();
		}
		return cargoCost;
	}
	public HashMap<T, Integer> getInventory() {
		return inventory;
	}
}
