package controllers;

import Interface.IAddOrder;
import Item.Order;
import Repository.OrderFactory;

public class OrderController {

	IAddOrder orderRepo = OrderFactory.createOrderRepo();
	
	
	
	public boolean addOrder(int order_id, int quantity, float cost, String location, String status) {
		if(orderRepo.Add(new Order(order_id,quantity,cost,location,status))==1) {
			return true;
		}else {
			return false;
		}
	}
	
}
