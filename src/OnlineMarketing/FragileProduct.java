package OnlineMarketing;

public class FragileProduct extends Product{

	public FragileProduct(String category, String name, double price, double weight, int quantity) {
		super(category, name, price, weight, quantity);
		setCargoPrice(weight*2*3.5);
	}

}
