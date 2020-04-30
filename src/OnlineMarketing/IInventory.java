package OnlineMarketing;

import java.util.HashMap;

public interface IInventory<T> {
	public void addElementToInventory(T product,int quantity);
	public boolean removeElementFromInventory(T product,int quantity);
	public double costOfItemsInInventory();
	public double cargoCostOfItemsInInventory();
	public  HashMap<T, Integer> getInventory();
	public Product findProduct(String product)  throws ProductNotFoundException;
}
