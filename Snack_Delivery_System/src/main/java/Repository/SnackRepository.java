package Repository;

//Repository Pattern

import org.springframework.data.repository.CrudRepository;

import Item.Snack;

public interface SnackRepository extends CrudRepository<Snack, String> {

}
