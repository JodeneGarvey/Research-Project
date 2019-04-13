package Repository;

import Interface.IAddOrder;
import Repository.mysql.MysqlOrderRepository;

public class OrderFactory {

	public static IAddOrder createOrderRepo() {
		return new MysqlOrderRepository();
	}
	
}
