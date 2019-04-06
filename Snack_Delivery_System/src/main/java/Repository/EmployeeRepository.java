package Repository;

import org.springframework.data.repository.CrudRepository;

import Item.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
