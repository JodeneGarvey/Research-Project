package Repository;

import Interface.IAddEmployee;
import Repository.mysql.MysqlEmployeeRepository;

public class EmployeeFactory {
	
	public static IAddEmployee createEmployeeRepo() {
		return new MysqlEmployeeRepository();
	}

}
