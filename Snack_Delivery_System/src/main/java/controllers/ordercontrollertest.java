package controllers;

import static org.junit.Assert.*;

import org.junit.Test;

public class ordercontrollertest {

	@Test
	public void test() {
		OrderController order = new OrderController();
		boolean output = order.addOrder(56778,1 , 150, "LT23", "Pending");
		assertEquals(true,output);
	}

}
