package Repository;

import Interface.IAddEmployee;
import Interface.IDelete;
import Interface.IUpdateEmployee;
import Repository.mysql.MysqlEmployeeRepository;

//Factory Pattern for Employee
public class EmployeeFactory {
	
	public static IAddEmployee createEmployeeRepo() {
		return new MysqlEmployeeRepository();
	}

	public static IDelete deleteEmployeeRepo() {
		return new MysqlEmployeeRepository();
	}
	
	public static IUpdateEmployee updateEmployeeRepo() {
		return new MysqlEmployeeRepository();
	}
	
	
}
