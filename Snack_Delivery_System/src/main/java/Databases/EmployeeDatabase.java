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

import Item.Employee;

public class EmployeeDatabase extends SQLProvider<Employee> {




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
		try {
			stat = con.createStatement();
			String sql = "SELECT * FROM " +Table_Name;
			
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				Employee attribute = new Employee();
				attribute.setId(res.getInt(1));
				attribute.setUsername(res.getString(2));
				attribute.setPassword(res.getString(3));
				
				Attributes.add(attribute);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return Attributes;
	}

	
	public Employee show(int id) {
		String search = "SELECT id,username FROM +Table_Name WHERE id = ? ";
		try {
			res = stat.executeQuery(search);
			if(res.next()) {
				do {
					System.out.println(res.getInt(1)+","+res.getString(2)+","+res.getString(3));
				}while(res.next());
			}else {
				System.out.println("Record not Found");
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	public int Update(Employee Entity, int id) {
		String update ="UPDATE +Table_Name SET id=?,username=?, password=? WHERE id=?";
		try {
			stat.executeQuery(update);
			
			update = "SELECT username, password FROM +Table_Name";
			res = stat.executeQuery(update);
			while(res.next()) {
				res.getInt(id);
				String username = res.getString(2);
				String password = res.getString(3);
				
				System.out.println("Employee ID " +id);
				System.out.println("Employee Username: " +username);
				System.out.println("Employee Password: " +password);
			}
			res.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stat!=null) {
					con.close();
				}
			}catch(SQLException e) {
				
			}try {
				if(con!=null) {
					con.close();
				}
				
				}catch(SQLException e) {
					e.printStackTrace();
			}
		}
		System.out.println("Record Updated");
		return 0;
	}
		
	

	
	public int Delete(int id) {
		String delete = "DELETE FROM +Table_Name WHERE id = ?";
		try {
			stat.executeQuery(delete);
			
			
			delete = "SELECT id,username,password FROM +Table_Name";
			
			res = stat.executeQuery(delete);
			while(res.next()) {
				res.getInt(id);
				String username = res.getString(2);
				String password = res.getString(3);
				
				System.out.println("Employee ID: " +id);
				System.out.println("Employee Username: " +username);
				System.out.println("Employee Password: " +password);
			}
			res.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stat!=null) {
					con.close();
				}
			}catch(SQLException e) {
				
			}try {
				if(con!=null) {
					con.close();
				}
				
				}catch(SQLException e) {
					e.printStackTrace();
			}
		}
		System.out.println("Record Deleted");
		return 0;
			
	}
		
		

	public int Add(Employee Entity){
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO "+Table_Name+ " (id, username, password) values (?,?,?)");
			ps.setInt(1, Entity.getId());
			ps.setString(2, Entity.getUsername());
			ps.setString(3, Entity.getPassword());
			return ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}

	
}
