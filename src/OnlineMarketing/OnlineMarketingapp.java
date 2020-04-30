package OnlineMarketing;

import java.io.IOException;
import java.util.Scanner;

public class OnlineMarketingapp {
	private OnlineMarketingOperations operation;
	private Scanner in;
	private User user;
	public OnlineMarketingapp() {
		operation=new OnlineMarketingOperations();
	    in=new Scanner(System.in);
	   
	}
	public void menu() throws IOException, ProductNotFoundException {
		 
		int signedin=0;
		int firstmenu=1;
		String input="-1";
	
		while(!input.equals("0")) {
			if(firstmenu==1) {
				System.out.println("Please enter the operation number that you want to perform");
				System.out.println("1) Sign In \n0)Exit");
				input = in.nextLine();
			}
			firstmenu=0;
			if(input.equals("0")) {
				break;
			}
			if(input.equals("1") && signedin==0) {
				System.out.println("Enter username:");
				String username=in.nextLine();
				System.out.println("Enter your password: ");
				String password=in.nextLine();
				user=operation.checkUserExist(username);
				operation.signIn(user, username, password);
				if(user.isSignedIn()) {
					signedin=1;
				}
			}
			if(user.isSignedIn() && user instanceof Customer) {
				System.out.println("1) Add Product to Your Basket \n2)Confirm your basket and Make payment \n3)Return Product \n4)Choose Your Deliver adress  \n5)Deposit Money \n6)See your balance \n7)Remove Product From Basket \n8)Show Products in Basket \n9)New adress \n10)Sign Out");
				input=in.nextLine();
				if(input.equals("1")) {
					System.out.println("Enter product name");
					String product=in.nextLine();
					System.out.println("Enter quantity: ");
					String quantity=in.nextLine();
					operation.addProductToBasket((Customer) user, product,Integer.parseInt(quantity));
				}
				else if(input.equals("2")) {
					operation.executeShopping((Customer) user);
				}
				else if(input.equals("3")) {
					System.out.println("Choose product to return");
					operation.productsOfCustomer((Customer) user);
					String choice=in.nextLine();
					System.out.println("Enter quantity: ");
					String quantity=in.nextLine();
					operation.returnProductRequest((Customer) user, Integer.parseInt(choice),Integer.parseInt(quantity));
				}
				else if(input.equals("4")) {
					System.out.println("Choose an address title you want to save as deliver");
					operation.showAdressOfCustomer((Customer) user);
					String choice=in.nextLine();
					operation.chooseDeliverAddress((Customer) user, Integer.parseInt(choice));
				}
				else if(input.equals("5")) {
					System.out.println("Enter amount you want to deposit: ");
					double amount=in.nextDouble();
					operation.depositMoney(user, amount);
				}
				else if(input.equals("6")) {
					operation.showBalance(user);
					System.out.println();
				}
				else if(input.equals("7")) {
					operation.showBasket((Customer) user);
					System.out.println("Enter product you want to remove:");
					String choice=in.nextLine();
					System.out.println("Enter quantity: ");
					String quantity=in.nextLine();
					operation.removeProductFromBasket(Integer.parseInt(choice),(Customer) user, Integer.parseInt(quantity));
				}
				else if(input.equals("8")) {
					operation.showBasket((Customer) user);
				}
				else if(input.equals("9")) {
					System.out.println("Enter adress title: ");
					String title=in.nextLine();
					System.out.println("Enter country:");
					String country=in.nextLine();
					System.out.println("Enter City:");
					String city=in.nextLine();
					System.out.println("Enter district");
					String district=in.nextLine();
					System.out.println("Enter street:");
					String street=in.nextLine();
					System.out.println("Enter door number:");
					String door=in.nextLine();
					operation.writeFile((RegularUser) user,title,country,city,district,street,door);
						
				}
				else if(input.equals("10")) {
					user.setSignedIn(false);
					signedin=0;
					firstmenu=1;
				}
			}
			else if(user.isSignedIn() && user instanceof Supplier) {
				System.out.println("1)Choose Your Deliver adress \n2)Check Endorsement \n3)New adress \n4)Accept returning product \n5)Sign out");
				input=in.nextLine();
				if(input.equals("1")) {
					System.out.println("Choose an address title you want to save as deliver address");
					operation.showAdressOfCustomer((Supplier) user);
					String choice=in.nextLine();
					operation.chooseDeliverAddress((Supplier) user, Integer.parseInt(choice));
				}
				else if(input.equals("2")) {
					operation.showEndorsement((Supplier) user);
				}
				else if(input.equals("3")) {
					System.out.println("Enter adress title: ");
					String title=in.nextLine();
					System.out.println("Enter country:");
					String country=in.nextLine();
					System.out.println("Enter City:");
					String city=in.nextLine();
					System.out.println("Enter district");
					String district=in.nextLine();
					System.out.println("Enter street:");
					String street=in.nextLine();
					System.out.println("Enter door number:");
					String door=in.nextLine();
					operation.writeFile((RegularUser) user,title,country,city,district,street,door);
						
				}
				else if(input.equals("4")) {
					operation.showReturnProductsForCustomer((Supplier) user);
					System.out.println("Choose a product from return requested products");
					String choice=in.nextLine();
					System.out.println("Enter quantity: ");
					int quantity=in.nextInt();
					operation.acceptReturnProduct((Supplier) user, Integer.parseInt(choice),quantity);
				}
				else if(input.equals("5")) {
					user.setSignedIn(false);
					signedin=0;
					firstmenu=1;
				}
			}
			else if(user.isSignedIn() && user instanceof AdminUser) {
				System.out.println("1)Approve Suppliers \n2)Sign out");
				input=in.nextLine();
				if(input.equals("1")) {
					operation.approveAllSuppliers((AdminUser) user);
				}
				else if(input.equals("2")) {
					user.setSignedIn(false);
					signedin=0;
					firstmenu=1;
				}
			}
		}
	}
}
