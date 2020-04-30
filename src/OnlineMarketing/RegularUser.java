package OnlineMarketing;

public abstract class RegularUser extends User{
	private ContactInfo info;
	public RegularUser(String username, String password, double balance,ContactInfo info) {
		super(username, password, balance);
		this.info=info;
	}
	public ContactInfo getInfo() {
		return info;
	}
	public void setInfo(ContactInfo info) {
		this.info = info;
	}
	public void showAdresses() {
		info.showAddresses();
	}
	public Address getAddress(int index) {
		return info.findAddress(index);
	}
}
