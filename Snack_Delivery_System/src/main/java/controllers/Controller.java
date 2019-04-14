package controllers;// Controller for Employee Page

import Databases.SQLProvider;
import Interface.IAddEmployee;
import Interface.IDelete;
import Interface.IUpdateEmployee;
import Item.Employee;
import Item.Student;
import Repository.EmployeeFactory;

public class Controller {
	//Dependency Inversion Principle
	IAddEmployee employeeRepo = EmployeeFactory.createEmployeeRepo();

	IDelete employee = EmployeeFactory.deleteEmployeeRepo();
	
	IUpdateEmployee updateEmployee = EmployeeFactory.updateEmployeeRepo();
	
	//Singleton Pattern
	SQLProvider<Student> db;
	
	private volatile static Controller currentController = null;
	
	private Controller() {
		if(currentController != null) {
			throw new RuntimeException("Some sort of problem with both threads");
		}
	}
	public static Controller getInstance() {
		if(currentController==null) {
			currentController = new Controller();
		}
		
		synchronized(Controller.class) {//One thread at a time
			return currentController;
		}
	}
	
	
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
