package Databases;


import java.sql.SQLException;
import java.util.List;

import Snack_Delivery_System.Student;

public class StudentDatabase extends SQLProvider<Student> {
	
	private static final String Table_Name = "Students";
	
	@Override
	protected void createDatabase() {
		try {
			stat = con.createStatement();
			if(stat.execute("Create Table if not exsisted " + Table_Name +  " (id INTEGER(7) PRIMARY KEY, name varchar(40), location varchar(50) )")) {
				
			}else {
				System.out.println("Table Created");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Student> viewAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student show(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Update(Student Entity, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMultiple(int[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Add(Student Entity) {
		// TODO Auto-generated method stub
		return 0;
	}

}
