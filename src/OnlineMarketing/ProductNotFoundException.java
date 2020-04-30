package OnlineMarketing;

public class ProductNotFoundException extends Exception {
	public ProductNotFoundException() {
		super("Product is not found in inventories!!");
	}
	public ProductNotFoundException(String message) {
		super(message);
	}
}
