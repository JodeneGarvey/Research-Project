package Repository.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
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

public class MysqlOrderRepository implements IAddOrder{

	Connection con;
	Statement stat;
	ResultSet res;
	public MysqlOrderRepository() {
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
	public int Add(Order Entity) {
		try {
			String sql = "Insert into shopdb.order values (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Entity.getOrder_ID());
			ps.setInt(2, Entity.getQuantity());
			ps.setFloat(3, Entity.getTotal_Cost());
			ps.setString(4, Entity.getLocation());
			ps.setString(5, Entity.getStatus());
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
}

