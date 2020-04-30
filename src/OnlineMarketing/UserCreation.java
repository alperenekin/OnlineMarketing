package OnlineMarketing;

import java.util.ArrayList;

public class UserCreation {
	private ArrayList<Customer> customers;
	private ArrayList<Supplier> suppliers;
	private ArrayList<AdminUser> admins;
	private FileIO file;
	private ArrayList<ArrayList<String>> users;
	public UserCreation(){
		customers=new ArrayList<Customer>();
		suppliers=new ArrayList<Supplier>();
		admins= new ArrayList<AdminUser>();
		file=new FileIO();
		users=file.readCsv("users");
	}
	public ArrayList<Customer> customerList() {//list of customers are created
		for(int i=1;i<users.size();i++) {
			if(users.get(i).get(0).equals("2")) {
				ArrayList<Address> adresses=new ArrayList<Address>();
				String username=users.get(i).get(1);
				String password=users.get(i).get(2);
				double activeBalance=Double.parseDouble(users.get(i).get(3));
				String telephone=users.get(i).get(4);
				String email=users.get(i).get(5);
				String adressTitle=users.get(i).get(9);
				String country=users.get(i).get(10);
				String city=users.get(i).get(11);
				String district=users.get(i).get(12);
				String street=users.get(i).get(13);
				String doorNumber=users.get(i).get(14);
				Address adres=new Address(adressTitle,country,city,district,street,doorNumber);
				adresses.add(adres);
				ContactInfo info=new ContactInfo(telephone,email,adresses);
				Customer customer=new Customer(username,password,activeBalance,info);
				customers.add(customer);
			}
		}
		return customers;
	}
	public ArrayList<Supplier> supplierList() {//list of suppliers are created
		String adressTitle=null;
		String country=null;;
		String city=null;;
		String street=null;;
		String doorNumber=null;;
		String district=null;
		for(int i=1;i<users.size();i++) {//for the one who has more than one address ,this for creates their adress
			if(users.get(i).get(0).equals("3")) {
				ArrayList<Address> adresses=new ArrayList<Address>();
				String username=users.get(i).get(1);
				String password=users.get(i).get(2);
				double activeBalance=Double.parseDouble(users.get(i).get(3));
				String telephone=users.get(i).get(4);
				String email=users.get(i).get(5);
				String shopCategory=users.get(i).get(6);
				String shopTitle=users.get(i).get(7);
				String taxNumber=users.get(i).get(8);
				Shop shop=new Shop(shopCategory,shopTitle,taxNumber);
				this.addProductToShop(shop);
				for(int j=9;j<users.get(i).size();j++){
					if(j%6==3) {
						adressTitle=users.get(i).get(j);	
					}
					else if(j%6==4) {
						country=users.get(i).get(j);
					}
					else if(j%6==5) {
						city=users.get(i).get(j);
					}
					else if(j%6==0) {
						district=users.get(i).get(j);
					}
					else if(j%6==1) {
						street=users.get(i).get(j);
					}
					else if(j%6==2) {
						doorNumber=users.get(i).get(j);
					}
				}
				Address adres=new Address(adressTitle,country,city,district,street,doorNumber);
				adresses.add(adres);
				ContactInfo info=new ContactInfo(telephone,email,adresses);
				Supplier supplier=new Supplier(username,password,activeBalance,info,shop);
				suppliers.add(supplier);
			}
		}
		return suppliers;
	}
	public ArrayList<AdminUser> adminList() {//list of admins are created
		for(int i=1;i<users.size();i++) {
			if(users.get(i).get(0).equals("1")) {
				String username=users.get(i).get(1);
				String password=users.get(i).get(2);
				double activeBalance=Double.parseDouble(users.get(i).get(3));
				AdminUser admin=new AdminUser(username,password,activeBalance);
				admins.add(admin);
			}
		}
		return admins;
	}
	private void addProductToShop(Shop shop) {//for every supplier their shop is created here
		ProductCreation products=new ProductCreation();
		if(shop.getCategory().equals("houseware")) {
			ArrayList<Product> housewares=products.housewareList();
			for(Product houseware:housewares) {
			    shop.addProductToShop(houseware, houseware.getQuantity());
			}
		}
		else if(shop.getCategory().equals("accessories")) {
			ArrayList<FragileProduct> acessories=products.accessoryList();
			for(FragileProduct accessory:acessories) {
			    shop.addProductToShop(accessory, accessory.getQuantity());
			}
		}
		else if(shop.getCategory().equals("cosmetic")) {
			ArrayList<FastConsumptionProduct> cosmetics=products.cosmeticList();
			for(FastConsumptionProduct cosmetic:cosmetics) {
			    shop.addProductToShop(cosmetic, cosmetic.getQuantity());
			}
		}
		else if(shop.getCategory().equals("electronic")) {
			ArrayList<Product> electronics=products.electronicList();
			for(Product electronic:electronics) {
			    shop.addProductToShop(electronic, electronic.getQuantity());
			}
		}
	}
}
