package tcs.interviewtracker.mappers;

import java.util.UUID;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import tcs.interviewtracker.persistence.Project;
import tcs.interviewtracker.service.ProjectService;

@Configurable
public class PositionConverter extends AbstractConverter<UUID, Project> {

    @Autowired
    ProjectService projectService;

    @Override
    protected Project convert(UUID uuid) {
        return projectService.getByUuid(uuid);

    }

}
