package tcs.interviewtracker.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("positionMapper")
    public ModelMapper positionMapper() {
        return new ModelMapper();
    }

}
