package OnlineMarketing;

import java.util.ArrayList;

public class ContactInfo {
	private String telephoneNo;
	private String email;
	private ArrayList<Address> adres;
	private Address deliveryAddress;// it has delivery address which is decided by user
	public ContactInfo(String telephoneNo, String email, ArrayList<Address> adres) {
		this.setTelephoneNo(telephoneNo);
		this.setEmail(email);
		this.adres=adres;
	}
	public String getTelephoneNo() {
		return telephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void addAddress(Address address) {
		adres.add(address);
	}
	public Address findAddress(int index) {
		int count=1;
		for(Address address:adres) {
			if(count==index) {
				return address;
			}
			count++;
		}
		return null;
	}
	public void showAddresses(){// this method in order to print addresses for user
		int count=1;
		for(Address address:adres) {
			System.out.println(count+") "+address.getTitle());
			count++;
		}
	}
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
}
