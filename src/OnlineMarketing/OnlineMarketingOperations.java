package OnlineMarketing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class OnlineMarketingOperations {
	private ArrayList<Customer> customers;
	private ArrayList<Supplier> suppliers;
	private ArrayList<AdminUser> admins;
	private UserCreation users;
	public  OnlineMarketingOperations(){
		users=new UserCreation();
		customers=users.customerList();
		suppliers=users.supplierList();
		admins= users.adminList();
	}
	public User checkUserExist(String username) {
		User user=null;
		for(Customer customer:customers) {
			if(customer.getUsername().equals(username)) {
				user=customer;
				return user;
			}
		}
		for(Supplier supplier:suppliers) {
			if(supplier.getUsername().equals(username)) {
				user=supplier;
				return user;
			}
		}
		for(AdminUser admin:admins) {
			if(admin.getUsername().equals(username)) {
				user=admin;
				return user;
			}
		}
		System.out.println("Username is not exist");
		return null;
	}
	public void signIn(User user,String username,String password) { // in order to sign in
		if(user.signIn(username, password)) {
			user.setSignedIn(true);
		}
		else {
			user.setSignedIn(false);
			System.out.println("You couldnt sign in!!");
		}
	}
	public void addProductToBasket(Customer customer,String product,int quantity) throws ProductNotFoundException {
		Product searchedProduct=null;
		for(Supplier supplier:suppliers) {
			if(supplier.findProduct(product)!=null) {
				searchedProduct=supplier.findProduct(product);//first find product from related supplier then add to basket
				break;
			}
		}
		try {
			if(searchedProduct==null){//if product is not exist throws exception
				throw new ProductNotFoundException();
				
			}
			System.out.println(searchedProduct.getName()+" is added to basket!");
			customer.addProductToBasket(searchedProduct, quantity);
		}
		catch(ProductNotFoundException e){
			System.out.println(e.getMessage());
		}
	}
	public void executeShopping(Customer customer) throws ProductNotFoundException {
		Supplier supplier=null;
		HashMap<Product,Integer> basketInventory=customer.getBasketInventory();
		if(basketInventory.isEmpty()) {
			System.out.println("Your basket is empty");
			return;
		}
		for(Entry<Product, Integer> inventory:basketInventory.entrySet()) {
			Product product=inventory.getKey();//take the product from basket
			int quantity=inventory.getValue();//take the quantity of that product from basket
			supplier=findSupplierOfProduct(product.getName());
			if(!supplier.isIsapproved()) {
				System.out.println(supplier.getShop().getTitle()+" is not approved by admin");
				break;
			}
			supplier.removeProductFromShop(product, quantity);//remove the product as much as its quantity from supplier's shop
			supplier.addProductToSoldProducts(product, quantity);
			customer.addProductToBoughtProducts(product, quantity);//add bought product to customer's bought products
			double supplierProfit=customer.payProduct(product,quantity);//calculates money that customer should pay and decrease it from customer's balance and returns
			double adminCut=supplierProfit*0.02;//calculate money of admin
			admins.get(0).setBalance(admins.get(0).getBalance()+adminCut);
			supplierProfit=supplierProfit-adminCut;
			supplier.depositMoney(supplierProfit);//SuplierProfit(Price+cargo) is added to supplier's balance
		}
		System.out.println("Your payment is succesfull!\nYour current balance is: "+customer.getBalance());
		basketInventory.clear();
	}
	private Supplier findSupplierOfProduct(String product) throws ProductNotFoundException {
		//in order to find supplier of a product.Then we can take product from that suppliers shop
		Product product1=null;
		for(Supplier supplier:suppliers) {
			if(supplier.findProduct(product)!=null) {
				product1=supplier.findProduct(product);
				return supplier;
			}
		}
		try {
			if(product1==null){
				throw new ProductNotFoundException();
			}
		}
		catch(ProductNotFoundException e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void approveAllSuppliers(AdminUser admin) {
		for(Supplier supplier:suppliers) {
			admin.approveSupplier(supplier);
		}
		System.out.println("Suppliers are approved!!");
	}
	public void productsOfCustomer(Customer customer) {//in order to print bought products
		customer.customersBoughtProducts();
	}
	public void showBasket(Customer customer) {// in order to print basket
		customer.customersBasket();
	}
	public void removeProductFromBasket(int index,Customer customer,int quantity) {
		Product product=customer.getProductFromBasket(index);
		customer.removeProductFromBasket(product, quantity);
	}
	public void returnProductRequest(Customer customer,int index,int quantity) throws ProductNotFoundException {
		double customerMoneyBack;
		Product product=customer.getBoughtProduct(index);
		Supplier supplier=findSupplierOfProduct(product.getName());
		customer.returnProduct(product,quantity);//customer gave the products and returned paid money for them
		customerMoneyBack=product.getPrice();
		supplier.addProductToReturnProducts(product);
		customer.depositMoney(customerMoneyBack);//money of customer is transfered
	}
	public void showReturnProductsForCustomer(Supplier supplier) {
		supplier.showReturnProducts();

	}
	public void acceptReturnProduct(Supplier supplier,int index,int quantity){
		double supplierLostMoney;
		Product product=supplier.getReturntProduct(index);
		supplierLostMoney=supplier.acceptReturnedProduct(product,quantity);
		supplier.setBalance(supplier.getBalance()-supplierLostMoney);
		System.out.println("The returned product is accepted");
	}
	public void showAdressOfCustomer(RegularUser regularuser) {
		regularuser.showAdresses();
	}
	public void chooseDeliverAddress(RegularUser regularuser,int index) {//deliver adress can be chosen by supplier or customer so regularuser
		Address adres=regularuser.getAddress(index);
		regularuser.getInfo().setDeliveryAddress(adres);
		System.out.println("This address saved as deliver address!!\n");
	}
	public void depositMoney(User user,double amount) {
		user.depositMoney(amount);
	}
	public void showBalance(User user) {
		System.out.println("Balance: "+user.getBalance());
	}
	public void showEndorsement(Supplier supplier) {
		supplier.calculateEndorsement();
		System.out.println("The endorsement is: "+supplier.getEndorsement());
	}
	public void writeFile(RegularUser user,String title,String country,String city,String district,String street,String door) throws IOException {
		//first readed from file and put to arraylist then added to related part of that arraylist
		//and added back to file
		FileIO file=new FileIO();
		Address adres=new Address(title,country,city,district,street,door);
		user.getInfo().addAddress(adres);
		ArrayList<ArrayList<String>> users=file.readCsv("users");
		for(int i=0;i<users.size();i++){
			for(int k=0;k<users.get(i).size();k++) {
				if(users.get(i).get(k).equals(user.getUsername())){
					users.get(i).add(title);
					users.get(i).add(country);
					users.get(i).add(city);
					users.get(i).add(district);
					users.get(i).add(street);
					users.get(i).add(door);
				}
			}
		}
		FileWriter fw=new FileWriter("users.csv");
		Writer output=new BufferedWriter(fw);
		for(int i=0;i<users.size();i++){
			for(int k=0;k<users.get(i).size();k++) {
				output.write(users.get(i).get(k)+",");	
			}
			output.write("\n");
		}
		output.close();
	}























}
