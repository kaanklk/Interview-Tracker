package tcs.interviewtracker.mappers;

import java.util.UUID;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Setter;
import tcs.interviewtracker.DTOs.CandidateDTO;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.persistence.Person;
import tcs.interviewtracker.repository.PersonRepository;



@Configuration
@AllArgsConstructor
public class CandidateMapperConfig {

    private PersonRepository personRepositoy;

    @Bean("candidateMapper")
    public ModelMapper candidateMapper() {
        var mapper = new ModelMapper();
        var typeMap = mapper.createTypeMap(Candidate.class, CandidateDTO.class);
        typeMap.addMapping(src -> src.getPerson().getFirstName(), (dest, value) -> dest.setFirstName((null != value)? ((Person)value).getFirstName() : null));
        typeMap.addMapping(src -> src.getPerson().getLastName(), (dest, value) -> dest.setLastName((null != value)? ((Person)value).getLastName() : null));
        typeMap.addMapping(src -> src.getPerson().getMiddleName(), (dest, value) -> dest.setMiddleName((null != value)? ((Person)value).getMiddleName() : null));
        typeMap.addMapping(src -> src.getPerson().getEmail(), (dest, value) -> dest.setEmail((null != value)? ((Person)value).getEmail() : null));
        typeMap.addMapping(src -> src.getPerson().getPhone(), (dest, value) -> dest.setPhone((null != value)? ((Person)value).getPhone() : null));
        //mapper.addConverter(new UuidToPersonConverter(personRepositoy));
        //mapper.addConverter(new PersonToUuidConverter());
        //mapper.typeMap(Candidate.class, CandidateDTO.class)
        //.addMapping(src -> {return (src != null && null != src.getPerson()) ? src.getPerson().getFirstName() : null; }, CandidateDTO::setFirstName);
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
