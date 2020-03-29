package ie.covid19.mapvisualization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class Disease {
    @Id
    private String name;
}
