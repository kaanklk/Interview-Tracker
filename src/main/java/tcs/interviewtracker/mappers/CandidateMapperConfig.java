package tcs.interviewtracker.mappers;

import java.util.UUID;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Setter;
import tcs.interviewtracker.persistence.Person;
import tcs.interviewtracker.repository.PersonRepository;



@Configuration
@AllArgsConstructor
public class CandidateMapperConfig {

    private PersonRepository personRepositoy;

    @Bean("candidateMapper")
    public ModelMapper candidateMapper() {
        var mapper = new ModelMapper();
        mapper.addConverter(new UuidToPersonConverter(personRepositoy));
        mapper.addConverter(new PersonToUuidConverter());
        return mapper;
    }

    @AllArgsConstructor
    private class UuidToPersonConverter extends AbstractConverter<UUID, Person> {
        
        private PersonRepository personRepositoy;

        @Override
        protected Person convert(UUID source) {
            return personRepositoy.getReferenceByUuid(source);
        }
    }

    private class PersonToUuidConverter extends AbstractConverter<Person, UUID> {
        
        @Override
        protected UUID convert(Person source) {
            return source.getUuid();
        }
    }
}
