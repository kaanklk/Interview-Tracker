package tcs.interviewtracker.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TechnicalDocumentationMapperConfig {
    
    @Bean("technicalDocumentationMapper")
    public ModelMapper technicalDocumentationMapper(){
        return new ModelMapper();
    }
}
