package tcs.interviewtracker.mappers;

import java.util.UUID;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.service.ProjectService;

@Configuration
public class PositionMapperConfig {

    @Autowired
    private ProjectService projectService;

    @Bean("positionMapper")
    public ModelMapper positionMapper() {
        var mapper = new ModelMapper();
        mapper.addConverter(uuidToProjectConverter);

        return new ModelMapper();
    }

    // @AllArgsConstructor
    Converter<UUID, Project> uuidToProjectConverter = new AbstractConverter<UUID, Project>() {

        @Override
        protected Project convert(UUID uuid) {
            return projectService.getByUuid(uuid);
        }
    };
}
