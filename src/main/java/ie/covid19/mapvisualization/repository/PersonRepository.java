package ie.covid19.mapvisualization.repository;

import ie.covid19.mapvisualization.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findByName(String name);
}
