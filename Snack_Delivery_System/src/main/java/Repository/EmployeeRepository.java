package Repository;

//Repository Pattern

import org.springframework.data.repository.CrudRepository;

import Item.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
