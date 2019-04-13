package controllers;// Controller for Employee Page

import Interface.IAddEmployee;
import Interface.IDelete;
import Interface.IUpdateEmployee;
import Item.Employee;
import Repository.EmployeeFactory;

public class Controller {
	//Dependency Inversion Principle
	IAddEmployee employeeRepo = EmployeeFactory.createEmployeeRepo();

	IDelete employee = EmployeeFactory.deleteEmployeeRepo();
	
	IUpdateEmployee updateEmployee = EmployeeFactory.updateEmployeeRepo();
	
	
	public boolean addEmployee(int parseInt, String text, String text2) {
		if(employeeRepo.Add(new Employee(parseInt,text,text2))==1) {
			return true;
		}
		return false;
	}
	
	public boolean deleteEmployee(int eid) {
		if(employee.Delete(eid)==1) {
			return true;
		}
		return false;
	}
	
	public boolean updateEmployee(int eid, String user, String pass) {
		if(updateEmployee.Update(new Employee(eid,user,pass), eid)==1) {
			return true;
		}
		return false;
	}
	
	

}
