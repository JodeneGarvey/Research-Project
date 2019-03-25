package Functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Interface.IAddOrder;
import Interface.IDelete;
import Interface.ISearchOrder;
import Interface.IShowOrder;
import Interface.IUpdateOrder;
import Item.Order;

//Interface Segregation Principle//

public class OrderRecords implements IAddOrder, IDelete, ISearchOrder,IUpdateOrder,IShowOrder{
	
	protected Connection con = null;
	protected Statement stat = null;
	protected ResultSet res = null;
	
	
	private static final String Table_Name = "orders";
	
	@Override
	public List<Order> viewAll() {
		List<Order> Attributes = new ArrayList<Order>();
		try {
			stat = con.createStatement();
			String sql = "SELECT * FROM " +Table_Name;
			
			res = stat.executeQuery(sql);
			
			while(res.next()) {
				Order attribute = new Order();
				attribute.setOrder_ID(res.getInt(1));
				attribute.setQuantity(res.getInt(2));
				attribute.setTotal_Cost(res.getFloat(3));
				attribute.setLocation(res.getString(4));
				attribute.setStatus(res.getString(5));
				
				Attributes.add(attribute);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return Attributes;
		
	}
	@Override
	public int Update(Order Entity, int Order_ID) {
		String update ="UPDATE +Table_Name WHERE Order_ID = ? ";
		try {
			stat.executeQuery(update);
			
			update = "SELECT Order_ID, Quantity, Total_Cost, Location, Status FROM +Table_Name";
			res = stat.executeQuery(update);
			while(res.next()) {
				res.getInt(Order_ID);
				int Quantity = res.getInt(2);
				float Total_Cost = res.getFloat(3);
				String Location = res.getString(4);
				String Status = res.getString(5);
				
				System.out.println("Order ID: " +Order_ID);
				System.out.println("Quantity: " +Quantity);
				System.out.println("Total Cost: " +Total_Cost);
				System.out.println("Location: " +Location);
				System.out.println("Order Status: "+Status);
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
	public Order show(int id) {
		String search = "SELECT Order_ID, Quantity, Total_Cost, Location, Status FROM +Table_Name WHERE Order_ID = ? ";
		try {
			res = stat.executeQuery(search);
			if(res.next()) {
				do {
					System.out.println(res.getInt(1)+","+res.getInt(2)+","+res.getFloat(3)+","+res.getString(4)+","+res.getString(5));
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
	public int Delete(int Order_ID) {
		String delete = "DELETE FROM +Table_Name WHERE Order_ID = ?";
		try {
			stat.executeQuery(delete);
			
			
			delete = "SELECT Order_ID, Quantity, Total_Cost, location, Status FROM +Table_Name";
			
			res = stat.executeQuery(delete);
			while(res.next()) {
				res.getInt(Order_ID);
				int Quantity = res.getInt(2);
				float Total_Cost = res.getFloat(3);
				String Location = res.getString(4);
				String Status = res.getString(5);
				
				System.out.println("Order ID: " +Order_ID);
				System.out.println("Quantity: " +Quantity);
				System.out.println("Total Cost: " +Total_Cost);
				System.out.println("Location: " +Location);
				System.out.println("Status: " +Status);
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
	public int Add(Order Entity) {
		String sql = "INSERT INTO "+Table_Name+ " (Order_ID, Quantity, Total_Cost, Location, Status) values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, Entity.getOrder_ID());
			st.setInt(2, Entity.getQuantity());
			st.setFloat(3, Entity.getTotal_Cost());
			st.setString(4, Entity.getLocation());
			st.setString(5, Entity.getStatus());
			return st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}

}
