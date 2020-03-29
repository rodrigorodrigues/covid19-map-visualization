
package ie.covid19.mapvisualization.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "objectIdFieldName",
    "uniqueIdField",
    "globalIdFieldName",
    "geometryType",
    "spatialReference",
    "fields",
    "features"
})
public class CovidCountyStatisticsDto {

    @JsonProperty("objectIdFieldName")
    private String objectIdFieldName;
    @JsonProperty("uniqueIdField")
    private UniqueIdField uniqueIdField;
    @JsonProperty("globalIdFieldName")
    private String globalIdFieldName;
    @JsonProperty("geometryType")
    private String geometryType;
    @JsonProperty("spatialReference")
    private SpatialReference spatialReference;
    @JsonProperty("fields")
    private List<Field> fields = null;
    @JsonProperty("features")
    private List<Feature> features = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("objectIdFieldName")
    public String getObjectIdFieldName() {
        return objectIdFieldName;
    }

    @JsonProperty("objectIdFieldName")
    public void setObjectIdFieldName(String objectIdFieldName) {
        this.objectIdFieldName = objectIdFieldName;
    }

    @JsonProperty("uniqueIdField")
    public UniqueIdField getUniqueIdField() {
        return uniqueIdField;
    }

    @JsonProperty("uniqueIdField")
    public void setUniqueIdField(UniqueIdField uniqueIdField) {
        this.uniqueIdField = uniqueIdField;
    }

    @JsonProperty("globalIdFieldName")
    public String getGlobalIdFieldName() {
        return globalIdFieldName;
    }

    @JsonProperty("globalIdFieldName")
    public void setGlobalIdFieldName(String globalIdFieldName) {
        this.globalIdFieldName = globalIdFieldName;
    }

    @JsonProperty("geometryType")
    public String getGeometryType() {
        return geometryType;
    }

    @JsonProperty("geometryType")
    public void setGeometryType(String geometryType) {
        this.geometryType = geometryType;
    }

    @JsonProperty("spatialReference")
    public SpatialReference getSpatialReference() {
        return spatialReference;
    }

    @JsonProperty("spatialReference")
    public void setSpatialReference(SpatialReference spatialReference) {
        this.spatialReference = spatialReference;
    }

    @JsonProperty("fields")
    public List<Field> getFields() {
        return fields;
    }

    @JsonProperty("fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @JsonProperty("features")
    public List<Feature> getFeatures() {
        return features;
    }

    @JsonProperty("features")
    public void setFeatures(List<Feature> features) {
        this.features = features;
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
