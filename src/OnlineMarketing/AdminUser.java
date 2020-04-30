package OnlineMarketing;

public class AdminUser extends User{

	public AdminUser(String username, String password, double balance) {
		super(username, password, balance);
	}
	public void approveSupplier(Supplier supplier) {// admin can approve a supplier
		supplier.setIsapproved(true);
	}
}
