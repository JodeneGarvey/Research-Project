package Databases;

//Open Closed Principle//

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Item.Employee;
import Repository.EmployeeRepository;


@Service
public class EmployeeDatabase extends SQLProvider<Employee> {


	private EmployeeRepository employeeRepository;


	private static final String Table_Name = "employee";
	
	
	protected void createDatabase() {
		try {
			stat = con.createStatement();
			if(stat.execute("Create Table if not exsisted " + Table_Name +  " ( id INTEGER PRIMARY KEY AUTOINCREMENT, username varchar(45), password varchar(45) )")) {
				
			}else {
				System.out.println("Table Created");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	
	public List<Employee> viewAll() {
		List<Employee> Attributes = new ArrayList<Employee>();
		employeeRepository.findAll()
		.forEach(Attributes::add);
		return Attributes;
		
	}

	
	public Employee show(int id) {
		return employeeRepository.findOne(id);
		
	}

	
	public void Update(Employee Entity, int id) {
		employeeRepository.save(Entity);
		
	}
		
	

	
	public void Delete(int id) {
		employeeRepository.delete(id);
		
			
	}
		
		

	public void Add(Employee Entity){
		employeeRepository.save(Entity);
		
	}

	
}
