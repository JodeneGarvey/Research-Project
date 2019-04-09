package Databases;

//Open Closed Principle//

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Item.Order;
import Repository.OrderRepository;

@Service
public class OrderDatabase extends SQLProvider<Order>{// Factory Pattern
	
	@Autowired
	private OrderRepository orderRepository;
	
	private static final String Table_Name = "orders";
	
	
	protected void createDatabase() {
		try {
			stat = con.createStatement();
			if(stat.execute("Create Table if not exsisted " + Table_Name +  " (Order_ID INTEGER PRIMARY KEY AUTOINCREMENT, Quantity INTEGER, Total_Cost FLOAT, location varchar(50), Status INTEGER )")) {
				
			}else {
				System.out.println("Table Created");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
	public List<Order> viewAll() {
		List<Order> Attributes = new ArrayList<Order>();
		orderRepository.findAll()
		.forEach(Attributes::add);
		return Attributes;
		
	}

	
	public Order show(int Order_ID) {
		return orderRepository.findOne(Order_ID);
		
	}
	
	
	public void Update(Order Entity, int Order_ID) {
		orderRepository.save(Entity);
		
	}
		
	

	
	public void Delete(int Order_ID) {
		orderRepository.delete(Order_ID);
		
	}
		
		

	
	public void Add(Order Entity) {
		orderRepository.save(Entity);
	}


}
