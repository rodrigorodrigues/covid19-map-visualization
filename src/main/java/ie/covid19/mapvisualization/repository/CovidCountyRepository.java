package ie.covid19.mapvisualization.repository;

import ie.covid19.mapvisualization.CovidCounty;
import org.springframework.data.repository.CrudRepository;

public interface CovidCountyRepository extends CrudRepository<CovidCounty, String> {
    CovidCounty findByName(String name);
}
