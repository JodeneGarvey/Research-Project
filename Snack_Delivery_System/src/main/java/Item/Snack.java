package Snack_Delivery_System;

public class Snack {

	private String name;
	private float price;
	private String image;
	
	
	public Snack(String name, float price, String image) {
		super();
		this.name = name;
		this.price = price;
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Snack [Name=" + name + ", Price=" + price + ", Image=" + image + "]";
	}
	
	
	
}
