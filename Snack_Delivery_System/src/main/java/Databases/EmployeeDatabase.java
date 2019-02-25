package Databases;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Item.Employee;

public abstract class EmployeeDatabase extends SQLProvider<Employee> {

	private static final String Table_Name = "Employee";
	
	@Override
	protected void createDatabase() {
		try {
			stat = con.createStatement();
			if(stat.execute("Create Table if not exsisted " + Table_Name +  " ( id INTEGER PRIMARY KEY AUTOINCREMENT, username varchar(10), password varchar(20) )")) {
				
			}else {
				System.out.println("Table Created");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
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

	@Override
	public Employee show(int id) {
		String search = "SELECT id,name FROM +Table_Name WHERE id = ? ";
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

	@Override
	public int Update(Employee Entity, int id) {
		String update ="UPDATE +Table_Name WHERE id = ? ";
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
		
	

	@Override
	public int Delete(int id) {
		String delete = "DELETE FROM +Table_Name WHERE id = ?";
		try {
			stat.executeQuery(delete);
			
			
			delete = "SELECT id,name,location FROM +Table_Name";
			
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
		
		

	@Override
	public int Add(Employee Entity) {
		String sql = "INSERT INTO "+Table_Name+ " (id, username, password) values (?, ?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, Entity.getId());
			st.setString(2, Entity.getUsername());
			st.setString(3, Entity.getPassword());
			return st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
}
