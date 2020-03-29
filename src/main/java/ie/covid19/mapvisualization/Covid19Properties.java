package ie.covid19.mapvisualization;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ie.covid19.mapvisualize")
@Data
public class Covid19Properties {
    private String url = "https://services1.arcgis.com/eNO7HHeQ3rUcBllm/arcgis/rest/services/CovidCountyStatisticsHPSCIreland/FeatureServer/0/query?where=1=1&outFields=*&outSR=4326&f=json";
}
