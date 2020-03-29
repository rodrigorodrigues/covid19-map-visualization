
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
    "ORIGID",
    "CountyName",
    "PopulationCensus16",
    "IGEasting",
    "IGNorthing",
    "Lat",
    "Long",
    "CovidCaseroundUp",
    "CovidCases",
    "PopulationProportionCovidCases",
    "x",
    "y",
    "ObjectId",
    "UGI"
})
public class Attributes {

    @JsonProperty("ORIGID")
    private Integer oRIGID;
    @JsonProperty("CountyName")
    private String countyName;
    @JsonProperty("PopulationCensus16")
    private Integer populationCensus16;
    @JsonProperty("IGEasting")
    private Integer iGEasting;
    @JsonProperty("IGNorthing")
    private Integer iGNorthing;
    @JsonProperty("Lat")
    private Double lat;
    @JsonProperty("Long")
    private Double _long;
    @JsonProperty("CovidCaseroundUp")
    private String covidCaseroundUp;
    @JsonProperty("CovidCases")
    private Integer covidCases;
    @JsonProperty("PopulationProportionCovidCases")
    private Double populationProportionCovidCases;
    @JsonProperty("x")
    private Double x;
    @JsonProperty("y")
    private Double y;
    @JsonProperty("ObjectId")
    private Integer objectId;
    @JsonProperty("UGI")
    private String uGI;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ORIGID")
    public Integer getORIGID() {
        return oRIGID;
    }

    @JsonProperty("ORIGID")
    public void setORIGID(Integer oRIGID) {
        this.oRIGID = oRIGID;
    }

    @JsonProperty("CountyName")
    public String getCountyName() {
        return countyName;
    }

    @JsonProperty("CountyName")
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    @JsonProperty("PopulationCensus16")
    public Integer getPopulationCensus16() {
        return populationCensus16;
    }

    @JsonProperty("PopulationCensus16")
    public void setPopulationCensus16(Integer populationCensus16) {
        this.populationCensus16 = populationCensus16;
    }

    @JsonProperty("IGEasting")
    public Integer getIGEasting() {
        return iGEasting;
    }

    @JsonProperty("IGEasting")
    public void setIGEasting(Integer iGEasting) {
        this.iGEasting = iGEasting;
    }

    @JsonProperty("IGNorthing")
    public Integer getIGNorthing() {
        return iGNorthing;
    }

    @JsonProperty("IGNorthing")
    public void setIGNorthing(Integer iGNorthing) {
        this.iGNorthing = iGNorthing;
    }

    @JsonProperty("Lat")
    public Double getLat() {
        return lat;
    }

    @JsonProperty("Lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }

    @JsonProperty("Long")
    public Double getLong() {
        return _long;
    }

    @JsonProperty("Long")
    public void setLong(Double _long) {
        this._long = _long;
    }

    @JsonProperty("CovidCaseroundUp")
    public String getCovidCaseroundUp() {
        return covidCaseroundUp;
    }

    @JsonProperty("CovidCaseroundUp")
    public void setCovidCaseroundUp(String covidCaseroundUp) {
        this.covidCaseroundUp = covidCaseroundUp;
    }

    @JsonProperty("CovidCases")
    public Integer getCovidCases() {
        return covidCases;
    }

    @JsonProperty("CovidCases")
    public void setCovidCases(Integer covidCases) {
        this.covidCases = covidCases;
    }

    @JsonProperty("PopulationProportionCovidCases")
    public Double getPopulationProportionCovidCases() {
        return populationProportionCovidCases;
    }

    @JsonProperty("PopulationProportionCovidCases")
    public void setPopulationProportionCovidCases(Double populationProportionCovidCases) {
        this.populationProportionCovidCases = populationProportionCovidCases;
    }

    @JsonProperty("x")
    public Double getX() {
        return x;
    }

    @JsonProperty("x")
    public void setX(Double x) {
        this.x = x;
    }

    @JsonProperty("y")
    public Double getY() {
        return y;
    }

    @JsonProperty("y")
    public void setY(Double y) {
        this.y = y;
    }

    @JsonProperty("ObjectId")
    public Integer getObjectId() {
        return objectId;
    }

    @JsonProperty("ObjectId")
    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    @JsonProperty("UGI")
    public String getUGI() {
        return uGI;
    }

    @JsonProperty("UGI")
    public void setUGI(String uGI) {
        this.uGI = uGI;
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
