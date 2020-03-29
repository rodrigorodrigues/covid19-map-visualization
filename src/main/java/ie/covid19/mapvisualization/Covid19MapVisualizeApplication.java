package ie.covid19.mapvisualization;

import com.github.javafaker.Faker;
import ie.covid19.mapvisualization.dto.Attributes;
import ie.covid19.mapvisualization.dto.CovidCountyStatisticsDto;
import ie.covid19.mapvisualization.repository.CovidCountyRepository;
import ie.covid19.mapvisualization.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
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
@EnableNeo4jRepositories
@EnableConfigurationProperties(Covid19Properties.class)
public class Covid19MapVisualizeApplication {

    Faker faker = new Faker();

    Random random = new Random();

    @Autowired
    Covid19Properties covid19Properties;

    public static void main(String[] args) {
        SpringApplication.run(Covid19MapVisualizeApplication.class, args);
    }

    @Primary
    @Bean
    public RestTemplate restTemplate() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }

    @Bean
    CommandLineRunner demo(PersonRepository personRepository, CovidCountyRepository covidCountyRepository, RestTemplate restTemplate) {
        return args -> {
            List<Person> allPeople = getAllPeople(personRepository);

            ResponseEntity<CovidCountyStatisticsDto> entity = getCovidDataApi(restTemplate);

            Objects.requireNonNull(entity.getBody()).getFeatures()
                    .forEach(f -> {
                        Attributes attributes = f.getAttributes();

                        Integer covidCases = attributes.getCovidCases();
                        String countyName = attributes.getCountyName();

                        CovidCounty covidCountyByName = covidCountyRepository.findByName(countyName);
                        if (covidCountyByName != null) {
                            covidCases -= covidCountyByName.getTotalCases();
                        }

                        List<Person> people = createFakePeopleData(personRepository, allPeople, covidCases, covidCountyByName);

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

    private ResponseEntity<CovidCountyStatisticsDto> getCovidDataApi(RestTemplate restTemplate) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(covid19Properties.getUrl(), HttpMethod.GET, httpEntity, CovidCountyStatisticsDto.class);
    }

    private List<Person> getAllPeople(PersonRepository personRepository) {
        return new ArrayList<>(StreamSupport
                        .stream(personRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList()));
    }

    private List<Person> createFakePeopleData(PersonRepository personRepository, List<Person> allPeople, Integer covidCases, CovidCounty covidCountyByName) {
        List<Person> people = IntStream.range(0, covidCases)
            .boxed()
            .map(i -> {
                LocalDate dateOfBirth = faker.date().birthday()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                ContactNumber contactNumber = getFakeContactNumber();

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
        return people;
    }

    private ContactNumber getFakeContactNumber() {
        return ContactNumber.builder()
                            .number(faker.phoneNumber().cellPhone())
                            .type("mobile")
                            .build();
    }

}
