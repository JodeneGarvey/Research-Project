package Item;

//Single Responsibility Principle//
public class Snack {

	private String name;
	private float price;
	private byte[] image;
	
	
	public Snack(String name, float price, byte[] image) {
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
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Snack [Name=" + name + ", Price=" + price + ", Image=" + image + "]";
	}
	
	
	
}
