package Item;


import javax.persistence.Entity;
import javax.persistence.Id;

//Single Responsibility Principle//

@Entity
public class Order {

	@Id
	private int Order_ID;
	private int Quantity;
	private float Total_Cost = 0;
	private String Location;
	private String Status;
	
	public Order(){
		this(0,0,0.0f,"","");
	}
	
	public Order(int order_ID, int quantity, float total_Cost, String location, String status) {
		super();
		Order_ID = order_ID;
		Quantity = quantity;
		Total_Cost = total_Cost;
		Location = location;
		Status = status;
	}
	public int getOrder_ID() {
		return Order_ID;
	}
	public void setOrder_ID(int order_ID) {
		Order_ID = order_ID;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public float getTotal_Cost() {
		return Total_Cost;
	}
	public void setTotal_Cost(float total_Cost) {
		Total_Cost = total_Cost;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "Order [Order_ID=" + Order_ID + ", Quantity=" + Quantity + ", Total_Cost=" + Total_Cost + ", Location="
				+ Location + ", Status=" + Status + "]";
	}
	
	
}
