package OnlineMarketing;

public class Address {
	private String title;
	private String country;
	private String city;
	private String district;
	private String street;
	private String doorNumber;
	public Address(String title, String country, String city,String district, String street, String doorNumber) {
		this.title = title;
		this.country = country;
		this.city = city;
		this.setDistrict(district);
		this.street = street;
		this.setDoorNumber(doorNumber);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getDoorNumber() {
		return doorNumber;
	}
	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String toString() {
		return "title: "+this.title + " \ncountry: " +this.country +" \ncity: "+this.city+ " \ndistrict: "
				+ this.district +" \nstreet: " + this.street + " \ndoor: "+ this.doorNumber;
		
	}
	
	
}
