package OnlineMarketing;

public class FastConsumptionProduct extends Product{

	public FastConsumptionProduct(String category, String name, double price, double weight, int quantity) {
		super(category, name, price, weight, quantity);
		setCargoPrice(weight*2*3.5);

	}

}
