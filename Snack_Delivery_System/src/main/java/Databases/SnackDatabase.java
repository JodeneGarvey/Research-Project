package Databases;

//Open Closed Principle//

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Item.Snack;
import Repository.SnackRepository;

@Service
public class SnackDatabase extends SQLProvider<Snack> {

	@Autowired
	private SnackRepository snackRepository;
	
	private static final String Table_Name = "snack";
	
	public SnackDatabase() {
		super();
	}
	
	
	
	protected void createDatabase() {
		try {
			stat = con.createStatement();
			if(stat.execute("Create Table if not exsisted " + Table_Name +  " (name varchar(40) PRIMARY KEY, price float, image byte[] )")) {
				
			}else {
				System.out.println("Table Created");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public List<Snack> viewAll() {
		List<Snack> Items = new ArrayList<Snack>();
		snackRepository.findAll()
		.forEach(Items::add);
		return Items;
		}
		
	

	
	public Snack show(String name) {
		return snackRepository.findOne(name);
		
	}

	
	public void Update(Snack Entity , String name) {
		snackRepository.save(Entity);
		
	}
		
	

	
	public void Delete(String name) {
		snackRepository.delete(name);
	}


	
	public void Add(Snack Entity) {
			snackRepository.save(Entity);

			
	}



	@Override
	public Snack show(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void Update(Snack Entity, int id) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub
		
	}



	

}
