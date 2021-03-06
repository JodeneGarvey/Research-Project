package Functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Interface.IAddStudent;
import Interface.IDelete;
import Interface.ISearchStudent;
import Interface.IShowStudent;
import Interface.IUpdateStudent;
import Item.Student;

//Interface Segregation Principle//

public class StudentRecords implements IAddStudent,IDelete,ISearchStudent,IUpdateStudent,IShowStudent  {

	protected Connection con = null;
	protected Statement stat = null;
	protected ResultSet res = null;
	
	private static final String Table_Name = "students";
	
	public int Update(Student Entity, int id) {
		String update ="UPDATE +Table_Name WHERE id = ? ";
		try {
			stat.executeQuery(update);
			
			update = "SELECT id, name, location FROM +Table_Name";
			res = stat.executeQuery(update);
			while(res.next()) {
				res.getInt(id);
				String name = res.getString(2);
				String location = res.getString(3);
				
				System.out.println("Student ID: " +id);
				System.out.println("Student Name: " +name);
				System.out.println("Student Location: " +location);
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

	
	public Student show(int id) {
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

	
	public int Delete(int id) {
		String delete = "DELETE FROM +Table_Name WHERE id = ?";
		try {
			stat.executeQuery(delete);
			
			
			delete = "SELECT id,name,location FROM +Table_Name";
			
			res = stat.executeQuery(delete);
			while(res.next()) {
				res.getInt(id);
				String name = res.getString(2);
				String location = res.getString(3);
				
				System.out.println("Student ID: " +id);
				System.out.println("Student Name: " +name);
				System.out.println("Student Location: " +location);
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
	public int Add(Student Entity) {
		String sql = "INSERT INTO "+Table_Name+ " (id, name, location) values (?, ?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, Entity.getId());
			st.setString(2, Entity.getName());
			st.setString(3, Entity.getLocation());
			return st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	
	public List<Student> viewAll() {
		List<Student> Attributes = new ArrayList<Student>();
		try {
			stat = con.createStatement();
			String sql = "SELECT * FROM " +Table_Name;
			
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				Student attribute = new Student();
				attribute.setId(res.getInt(1));
				attribute.setName(res.getString(2));
				attribute.setLocation(res.getString(3));
				
				Attributes.add(attribute);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return Attributes;
	}

}
