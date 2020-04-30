package OnlineMarketing;

public abstract class User {
	private String username;
	private  String password;
	private double balance;
	private boolean signedIn;
	
	public User(String username, String password, double balance) {
		this.username = username;
		this.password = password;
		this.balance = 0;
		setSignedIn(false);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void depositMoney(double amount) {
		 this.setBalance(this.getBalance()+amount);
	}
	public boolean signIn(String username,String password) {
		if(this.getUsername().equals(username) && this.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	public boolean isSignedIn() {
		return signedIn;
	}
	public void setSignedIn(boolean signedIn) {
		this.signedIn = signedIn;
	}
}
