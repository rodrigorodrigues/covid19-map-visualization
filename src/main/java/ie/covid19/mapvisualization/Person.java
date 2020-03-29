package ie.covid19.mapvisualization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NodeEntity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate dateOfBirth;

    private String name;

    private Double latitude;

    private Double longitude;

    private Boolean requireCocooning;

    private String email;

    /**
     * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
     * to ignore the direction of the relationship.
     * https://dzone.com/articles/modelling-data-neo4j
     */
    @Relationship(type = "CLOSECONTACTS")
    private Set<Person> closeContacts;

    @Relationship(type = "DISEASES", direction = Relationship.UNDIRECTED)
    private Set<Disease> diseases;

    @Relationship(type = "CONTACTNUMBERS", direction = Relationship.UNDIRECTED)
    private Set<ContactNumber> contactNumbers;

    public void addContactNumber(ContactNumber contactNumber) {
        if (contactNumber == null) {
            contactNumbers = new HashSet<>();
        }
        contactNumbers.add(contactNumber);
    }

    public void addDisease(Disease disease) {
        if (disease == null) {
            diseases = new HashSet<>();
        }
        diseases.add(disease);
    }

    public void addCloseContact(Person person) {
        if (closeContacts == null) {
            closeContacts = new HashSet<>();
        }
        closeContacts.add(person);
    }

    public String toString() {

        return this.name + "'s teammates => "
                + Optional.ofNullable(this.closeContacts).orElse(
                Collections.emptySet()).stream()
                .map(Person::getName)
                .collect(Collectors.toList());
    }
}
