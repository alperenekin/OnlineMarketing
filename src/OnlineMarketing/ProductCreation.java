package OnlineMarketing;

import java.util.ArrayList;

public class ProductCreation {
	private ArrayList<FragileProduct> accessories;
	private ArrayList<FastConsumptionProduct> cosmetics;
	private ArrayList<Product> housewares;
	private ArrayList<Product> electronics;
	private ArrayList<ArrayList<String>> products;
	private FileIO file;
	public ProductCreation() {// the list of products seperated from each other
		accessories=new ArrayList<FragileProduct>();
		cosmetics=new ArrayList<FastConsumptionProduct>();
		housewares=new ArrayList<Product>();
		electronics=new ArrayList<Product>();
		file=new FileIO();
		products=file.readCsv("products");
	}
	public ArrayList<FragileProduct> accessoryList() { // it creates list of accessories
		for(int i=1;i<products.size();i++) {
			if(products.get(i).get(0).equals("accessories")) {
				String category=products.get(i).get(0);
				String name=products.get(i).get(1);
				double price=Double.parseDouble(products.get(i).get(2));
				double weight=Double.parseDouble(products.get(i).get(3));
				int quantity=Integer.parseInt(products.get(i).get(4));
				FragileProduct acessory=new FragileProduct(category,name,price,weight,quantity);
				accessories.add(acessory);
			}
		}
		return accessories;
	}
	public ArrayList<FastConsumptionProduct> cosmeticList() {//creates list of cosmetics
		for(int i=1;i<products.size();i++) {
			if(products.get(i).get(0).equals("cosmetic")) {
				String category=products.get(i).get(0);
				String name=products.get(i).get(1);
				double price=Double.parseDouble(products.get(i).get(2));
				double weight=Double.parseDouble(products.get(i).get(3));
				int quantity=Integer.parseInt(products.get(i).get(4));
				FastConsumptionProduct cosmetic=new FastConsumptionProduct(category,name,price,weight,quantity);
				cosmetics.add(cosmetic);
				
			}
		}
		return cosmetics;
	}
	public ArrayList<Product> housewareList() {//creates list of housewares
		for(int i=1;i<products.size();i++) {
			if(products.get(i).get(0).equals("houseware")) {
				String category=products.get(i).get(0);
				String name=products.get(i).get(1);
				double price=Double.parseDouble(products.get(i).get(2));
				double weight=Double.parseDouble(products.get(i).get(3));
				int quantity=Integer.parseInt(products.get(i).get(4));
				Product houseware=new Product(category,name,price,weight,quantity);
				housewares.add(houseware);
			}
		}
		return housewares;
	}
	public ArrayList<Product> electronicList() {//creates list of electronics
		for(int i=1;i<products.size();i++) {
			if(products.get(i).get(0).equals("electronic")) {
				String category=products.get(i).get(0);
				String name=products.get(i).get(1);
				double price=Double.parseDouble(products.get(i).get(2));
				double weight=Double.parseDouble(products.get(i).get(3));
				int quantity=Integer.parseInt(products.get(i).get(4));
				Product electronic=new Product(category,name,price,weight,quantity);
				electronics.add(electronic);
			}
		}
		return electronics;
	}
}
