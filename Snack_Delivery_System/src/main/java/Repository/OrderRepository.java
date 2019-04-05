package Repository;

import org.springframework.data.repository.CrudRepository;

import Item.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
