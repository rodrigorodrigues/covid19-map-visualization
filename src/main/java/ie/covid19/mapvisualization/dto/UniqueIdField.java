
package ie.covid19.mapvisualization.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "isSystemMaintained"
})
public class UniqueIdField {

    @JsonProperty("name")
    private String name;
    @JsonProperty("isSystemMaintained")
    private Boolean isSystemMaintained;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("isSystemMaintained")
    public Boolean getIsSystemMaintained() {
        return isSystemMaintained;
    }

    @JsonProperty("isSystemMaintained")
    public void setIsSystemMaintained(Boolean isSystemMaintained) {
        this.isSystemMaintained = isSystemMaintained;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
