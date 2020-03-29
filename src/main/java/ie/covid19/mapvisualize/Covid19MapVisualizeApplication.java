package ie.covid19.mapvisualize;

import com.github.javafaker.Faker;
import ie.covid19.mapvisualize.dto.Attributes;
import ie.covid19.mapvisualize.dto.CovidCountyStatisticsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

@Slf4j
@SpringBootApplication
@EnableNeo4jRepositories(considerNestedRepositories = true)
public class Covid19MapVisualizeApplication {

    Faker faker = new Faker();

    String url = "https://services1.arcgis.com/eNO7HHeQ3rUcBllm/arcgis/rest/services/CovidCountyStatisticsHPSCIreland/FeatureServer/0/query?where=1=1&outFields=*&outSR=4326&f=json";

    Random random = new Random();


    public static void main(String[] args) {
        SpringApplication.run(Covid19MapVisualizeApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(PersonRepository personRepository, CovidCountyRepository covidCountyRepository) {
        return args -> {
            List<Person> allPeople = new ArrayList<>(StreamSupport
                    .stream(personRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList()));
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            messageConverters.add(converter);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setMessageConverters(messageConverters);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity(httpHeaders);

            ResponseEntity<CovidCountyStatisticsDto> entity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, CovidCountyStatisticsDto.class);

            Objects.requireNonNull(entity.getBody()).getFeatures()
                    .forEach(f -> {
                        Attributes attributes = f.getAttributes();

                        Integer covidCases = attributes.getCovidCases();
                        String countyName = attributes.getCountyName();

                        CovidCounty covidCountyByName = covidCountyRepository.findByName(countyName);
                        if (covidCountyByName != null) {
                            covidCases -= covidCountyByName.getTotalCases();
                        }

                        List<Person> people = IntStream.range(0, covidCases)
                            .boxed()
                            .map(i -> {
                                LocalDate dateOfBirth = faker.date().birthday()
                                        .toInstant()
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate();

                                ContactNumber contactNumber = ContactNumber.builder()
                                        .number(faker.phoneNumber().cellPhone())
                                        .type("mobile")
                                        .build();

                                Person person = Person.builder()
                                        .name(faker.name().fullName())
                                        .dateOfBirth(dateOfBirth)
                                        .email(faker.internet().emailAddress())
                                        .requireCocooning(dateOfBirth.getYear() > 80)
                                        .contactNumbers(new HashSet(Arrays.asList(contactNumber)))
                                        .build();

                                if (!allPeople.isEmpty()) {
                                    int index = IntStream.range(0, random.nextInt(5)).sum();
                                    if (index + 1 < allPeople.size()) {
                                        IntStream.range(0, index)
                                                .forEach(j -> person.addCloseContact(allPeople.get(random.nextInt(index))));
                                    }
                                }

                                allPeople.add(person);
                                return person;
                            }).map(personRepository::save)
                            .collect(Collectors.toList());

                        if (covidCountyByName != null) {
                            people.addAll(covidCountyByName.getPeople());
                        }

                        CovidCounty covidCounty = CovidCounty.builder()
                                .name(attributes.getCountyName())
                                .population(attributes.getPopulationCensus16())
                                .totalCases(attributes.getCovidCases())
                                .latitude(attributes.getLat())
                                .longitude(attributes.getLong())
                                .people(new HashSet<>(people))
                                .build();

                        covidCountyRepository.save(covidCounty);
                    });
        };
    }

    public interface PersonRepository extends CrudRepository<Person, Long> {

        Person findByName(String name);
    }

    public interface CovidCountyRepository extends CrudRepository<CovidCounty, String> {
        CovidCounty findByName(String name);
    }
}
