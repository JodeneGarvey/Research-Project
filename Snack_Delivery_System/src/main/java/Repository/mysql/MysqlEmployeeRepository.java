package Repository.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Interface.IAddEmployee;
import Interface.IDelete;
import Interface.ISearchEmployee;
import Interface.IShowEmployee;
import Interface.IUpdateEmployee;
import Item.Employee;

public class MysqlEmployeeRepository implements IAddEmployee, IDelete, ISearchEmployee, IUpdateEmployee, IShowEmployee {

	Connection con;
	Statement stat;
	ResultSet res;
	
	
public MysqlEmployeeRepository() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	@Override
	public int Add(Employee Entity) {
		try {
			String sql = "Insert into employee values (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Entity.getId());
			ps.setString(2, Entity.getUsername());
			ps.setString(3, Entity.getPassword());
			ps.executeUpdate();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Employee> viewAll() {
		List<Employee> Attributes = new ArrayList<Employee>();
		try {
			stat = con.createStatement();
			String sql = "SELECT * FROM employee ";
			
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
	public int Update(Employee Entity, int id) {
		String update ="UPDATE +Table_Name SET id=?,username=?, password=? WHERE id=?";
		try {
			stat.executeQuery(update);
			
			update = "SELECT username, password FROM employee";
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
	public Employee show(int id) {
		String search = "SELECT id,username FROM employee WHERE id = ? ";
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
	public int Delete(int id) {
		String delete = "DELETE FROM employee WHERE id = ?";
		try {
			stat.executeQuery(delete);
			
			
			delete = "SELECT id,username,password FROM employee";
			
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
	
	

}
