package tcs.interviewtracker.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class CandidateMapperConfig {

    @Bean("candidateMapper")
    public ModelMapper candidateMapper() {
        var mapper = new ModelMapper();
        //TODO set converters
        return mapper;
    }



}
