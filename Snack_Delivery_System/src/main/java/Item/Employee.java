package Item;

import javax.persistence.Id;
import javax.persistence.Entity;
//Single Responsibility Principle//


@Entity
public class Employee {

	@Id
	private int id;
	private String username;
	private String password;
	
	public Employee() {
		this(0,"","");
	}
	
	public Employee(int id,String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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



	@Override
	public String toString() {
		return "Employee [Employee ID= " +id+ "Username= " +username+ ", Password= "+ password +" ]";
	}
	
	
}
