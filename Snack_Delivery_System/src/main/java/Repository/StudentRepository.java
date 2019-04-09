package Repository;

//Repository Pattern

import org.springframework.data.repository.CrudRepository;

import Item.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
