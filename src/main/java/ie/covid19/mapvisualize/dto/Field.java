
package ie.covid19.mapvisualize.dto;

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
    "type",
    "alias",
    "sqlType",
    "domain",
    "defaultValue",
    "length"
})
public class Field {

    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("alias")
    private String alias;
    @JsonProperty("sqlType")
    private String sqlType;
    @JsonProperty("domain")
    private Object domain;
    @JsonProperty("defaultValue")
    private Object defaultValue;
    @JsonProperty("length")
    private Integer length;
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

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("alias")
    public String getAlias() {
        return alias;
    }

    @JsonProperty("alias")
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @JsonProperty("sqlType")
    public String getSqlType() {
        return sqlType;
    }

    @JsonProperty("sqlType")
    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    @JsonProperty("domain")
    public Object getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(Object domain) {
        this.domain = domain;
    }

    @JsonProperty("defaultValue")
    public Object getDefaultValue() {
        return defaultValue;
    }

    @JsonProperty("defaultValue")
    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    @JsonProperty("length")
    public Integer getLength() {
        return length;
    }

    @JsonProperty("length")
    public void setLength(Integer length) {
        this.length = length;
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
