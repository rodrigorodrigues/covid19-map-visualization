
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
    "wkid",
    "latestWkid"
})
public class SpatialReference {

    @JsonProperty("wkid")
    private Integer wkid;
    @JsonProperty("latestWkid")
    private Integer latestWkid;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("wkid")
    public Integer getWkid() {
        return wkid;
    }

    @JsonProperty("wkid")
    public void setWkid(Integer wkid) {
        this.wkid = wkid;
    }

    @JsonProperty("latestWkid")
    public Integer getLatestWkid() {
        return latestWkid;
    }

    @JsonProperty("latestWkid")
    public void setLatestWkid(Integer latestWkid) {
        this.latestWkid = latestWkid;
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
