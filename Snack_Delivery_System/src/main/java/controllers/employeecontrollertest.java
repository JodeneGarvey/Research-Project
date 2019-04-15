package controllers;

import static org.junit.Assert.*;

import org.junit.Test;

public class employeecontrollertest {

	@Test
	public void test() {
		Controller employee = new Controller();
		boolean output = employee.addEmployee(11, "Penny", "P@Smith");
		assertEquals(true,output);
	}

}
