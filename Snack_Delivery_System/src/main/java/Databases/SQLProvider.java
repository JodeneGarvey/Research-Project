package Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.lang.*;
import java.sql.*;

import Item.Employee;

abstract public class SQLProvider<D> {

	
	protected Connection con = null;
	protected Statement stat = null;
	protected ResultSet res = null;
	
	
	private static final String Driver = "com.mysql.cj.jdbc.Driver";
	
	public SQLProvider(){
		try {
				Class.forName(Driver).newInstance();
				String url = "jdbc:mysql://localhost:3306/shopdb";
				con = DriverManager.getConnection(url,"Jodene","patrice");
				
				createDatabase();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
	

	abstract protected void createDatabase();
	
	abstract public List<D> viewAll();
	
	abstract public D show(int id);
	
	abstract public int Update(D Entity, int id);
	
	abstract public int Delete(int id);
	
	abstract public int Add(D Entity);
	
	


	
	
}
