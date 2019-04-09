package Repository;

//Repository Pattern

import org.springframework.data.repository.CrudRepository;

import Item.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
