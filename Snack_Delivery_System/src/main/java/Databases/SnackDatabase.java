package Databases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Item.Snack;


public class SnackDatabase extends SQLProvider<Snack> {

	private static final String Table_Name = "Snack";
	
	public SnackDatabase() {
		super();
	}
	
	
	@Override
	protected void createDatabase() {
		try {
			stat = con.createStatement();
			if(stat.execute("Create Table if not exsisted " + Table_Name +  " (name varchar(40) PRIMARY KEY, price float, image String )")) {
				
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
		String search = "SELECT name, price, image FROM +Table_Name WHERE name = ? ";
		try {
			res = stat.executeQuery(search);
			if(res.next()) {
				do {
					System.out.println(res.getString(1)+","+res.getFloat(2)+","+res.getString(3));
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
	public int Update(Snack Entity , int id) {
		String update ="UPDATE +Table_Name WHERE name = ? ";
		try {
			stat.executeQuery(update);
			
			update = "SELECT name, price, image FROM +Table_Name";
			res = stat.executeQuery(update);
			while(res.next()) {
				String name = res.getString(1);
				float price = res.getFloat(2);
				String image = res.getString(3);
				
				System.out.println("Snack Name: " +name);
				System.out.println("Snack Price: " +price);
				System.out.println("Snack Image: " +image);
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
		String delete = "DELETE FROM +Table_Name WHERE name = ?";
		try {
			stat.executeQuery(delete);
			
			
			delete = "SELECT name,price, image FROM +Table_Name";
			
			res = stat.executeQuery(delete);
			while(res.next()) {
				String name = res.getString(1);
				Float price = res.getFloat(2);
				String image = res.getString(3);
				
				System.out.println("Name of Snack: " +name);
				System.out.println("Snack Price: " +price);
				System.out.println("Image of Snack: " +image);
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
	public int Add(Snack Entity) {
			String sql ="INSERT INTO "+Table_Name+ " (name, price, image) values (?, ?, ?)";
			File imgFile = new File(Entity.getImage());
			try {
				FileInputStream file = new FileInputStream(imgFile);
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, Entity.getName());
				st.setFloat(2, Entity.getPrice());
				st.setBinaryStream(3, (InputStream)file,(int)imgFile.length());
				return st.executeUpdate();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("Snack Added");
			return 0;

			
	}

}
