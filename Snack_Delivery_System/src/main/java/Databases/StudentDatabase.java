package Databases;

//Open Closed Principle//

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Item.Student;
import Repository.StudentRepository;

@Service
public class StudentDatabase extends SQLProvider<Student> {
	
	@Autowired
	private StudentRepository studentRepository;
	
	private static final String Table_Name = "students";
	
	
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

	
	public List<Student> viewAll() {
		List<Student> Attributes = new ArrayList<Student>();
		studentRepository.findAll()
		.forEach(Attributes::add);
		return Attributes;
		
	}

	@Override
	public Student show(int id) {
		return studentRepository.findOne(id);
		/*
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
		*/
	}

	
	public void Update(Student Entity, int id) {
		studentRepository.save(Entity);
		
	}
		
	

	
	public void Delete(int id) {
		studentRepository.delete(id);
		
	}
		
		

	
	public void Add(Student Entity) {
		 studentRepository.save(Entity);
		
	}

}
