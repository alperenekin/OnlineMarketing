package OnlineMarketing;

import java.util.Map.Entry;

public class Supplier extends RegularUser{
	private Shop shop;
	private double endorsement;
	private boolean isapproved;
	private Inventory<Product> returnProducts;//Products that wanted to return by user is added to that inventory
	private Inventory<Product> soldProducts;
	public Supplier(String username, String password, double balance, ContactInfo info,Shop shop) {
		super(username, password, balance, info);
		this.shop=shop;
		soldProducts=new ProductInventory<Product>();
		returnProducts=new ProductInventory<Product>();
		this.setIsapproved(false);//suppliers are not approved as default
	}
	public void removeProductFromShop(Product product,int quantity) {
		this.shop.removeProductFromShop(product, quantity);
	}
	public void addProductToSoldProducts(Product product,int quantity) {
		soldProducts.addElementToInventory(product, quantity);
	}
	public void removeProductFromReturnProduct(Product product,int quantity) {
		returnProducts.removeElementFromInventory(product, quantity);
	}
	public void addProductToReturnProducts(Product product) {
		returnProducts.addElementToInventory(product, 1);
	}
	public void showReturnProducts() {// in order to print products that wanted to be returned
		int count=1;
		if(returnProducts.getInventory().isEmpty()) {
			System.out.println("There is no bought product");
			return;
		}
		for(Entry<Product, Integer> product:returnProducts.getInventory().entrySet()) {
			System.out.println(count+")"+" " +product.getKey().getName()+" quantity: "+product.getValue()+"\n");
			count++;
		}
	}
	public Product getReturntProduct(int index) {
		int count=1;
		if(returnProducts.getInventory().isEmpty()) {
			System.out.println("There is no bought product");
			return null;
		}
		for(Entry<Product, Integer> product:returnProducts.getInventory().entrySet()) {
			if(count==index) {
				return product.getKey();
			}
			count++;
		}
		return null;
	}
	public double acceptReturnedProduct(Product product,int quantity) {
		//when supplier accepts returned product,it is tranfered to shop of supplier
		double lostMoney=0;
		returnProducts.removeElementFromInventory(product, quantity);
		shop.addProductToShop(product, quantity);
		lostMoney=product.getPrice()*quantity;
		return lostMoney;
	}
	public Product findProduct(String product) throws ProductNotFoundException {
		return shop.findProduct(product);
	}

	public boolean isIsapproved() {
		return isapproved;
	}

	public void setIsapproved(boolean isapproved) {
		this.isapproved = isapproved;
	}

	public double getEndorsement() {
		return endorsement;
	}
	public void setEndorsement(double endorsement) {
		this.endorsement=endorsement;
	}
	public void calculateEndorsement() {//endorsement calculated by sold products
		for(Entry<Product, Integer> product:soldProducts.getInventory().entrySet()) {
			double productPrice=product.getKey().getPrice();
			double cargoPrice=product.getKey().getCargoPrice();
			double quantity=product.getValue();
			this.setEndorsement(getEndorsement()+productPrice*quantity+cargoPrice*quantity);
		}
	}
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
