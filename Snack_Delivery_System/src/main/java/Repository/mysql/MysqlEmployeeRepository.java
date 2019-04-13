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


//Model for Employee
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
		try {
			String sql = "Update employee set username = ?, password = ? where id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, Entity.getUsername());
			ps.setString(2, Entity.getPassword());
			ps.setInt(3, id);
			
			ps.executeUpdate();
				}catch(SQLException e) {
					e.printStackTrace();
			}
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
		try {
			String sql ="Delete from employee where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	

}
