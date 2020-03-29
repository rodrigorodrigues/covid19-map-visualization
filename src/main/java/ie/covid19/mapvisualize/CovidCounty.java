package ie.covid19.mapvisualize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NodeEntity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class CovidCounty {
    @Id
    private String name;

    private Integer population;

    private Double latitude;

    private Double longitude;

    private Integer totalCases;

    @Relationship(type = "PEOPLE", direction = Relationship.UNDIRECTED)
    private Set<Person> people;

    public String toString() {
        return this.name + "'s people => "
                + Optional.ofNullable(this.people).orElse(
                Collections.emptySet()).stream()
                .map(Person::getName)
                .collect(Collectors.toList());
    }
}
