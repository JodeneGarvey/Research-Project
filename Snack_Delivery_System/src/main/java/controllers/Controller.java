package controllers;// Controller

import Interface.IAddEmployee;
import Item.Employee;
import Repository.EmployeeFactory;

public class Controller {
	IAddEmployee employeeRepo = EmployeeFactory.createEmployeeRepo();

	public boolean addEmployee(int parseInt, String text, String text2) {
		if(employeeRepo.Add(new Employee(parseInt,text,text2))==1) {
			return true;
		}
		return false;
	}

}
