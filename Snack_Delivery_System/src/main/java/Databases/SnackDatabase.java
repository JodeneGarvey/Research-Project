package Databases;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Item.Snack;
import Item.Student;

public class SnackDatabase extends SQLProvider<Snack> {

	private static final String Table_Name = "Snack";
	
	public SnackDatabase() {
		super();
	}
	
	
	@Override
	protected void createDatabase() {
		try {
			stat = con.createStatement();
			if(stat.execute("Create Table if not exsisted " + Table_Name +  " (name varchar(40) PRIMARY KEY, price float, image BLOB )")) {
				
			}else {
				System.out.println("Table Created");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Snack> viewAll() {
		List<Snack> Items = new ArrayList<Snack>();
		
		try {
			stat = con.createStatement();
			String sql = "SELECT * FROM " +Table_Name ;
			
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				Snack item = new Snack();
				item.setName(res.getString(1));
				item.setPrice(res.getFloat(2));
				item.setImage(res.getString(3));
				
				Items.add(item);
				
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return Items;
		}
		
	

	@Override
	public Snack show(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Update(Snack Entity , int id) {
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
	public int Add(Snack Entity) {
		String sql = "INSERT INTO "+Table_Name+ " (name, price, image) values (?, ?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, Entity.getName());
			st.setFloat(2, Entity.getPrice());
			st.setString(3, Entity.getImage());
			return st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
