package tcs.interviewtracker.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import tcs.interviewtracker.DTOs.ManagementDocumentationDTO;
import tcs.interviewtracker.persistence.ManagementDocumentation;
import tcs.interviewtracker.service.CandidateService;
import tcs.interviewtracker.service.ManagementDocumentationService;
import tcs.interviewtracker.service.ProjectService;
import tcs.interviewtracker.service.UserService;

@Mapper(componentModel = "spring", uses = {ManagementDocumentationService.class, ProjectService.class, CandidateService.class, UserService.class})
public interface ManagementDocumentationMapper {
    ManagementDocumentationMapper INSTANCE = Mappers.getMapper(ManagementDocumentationMapper.class);

    @Mapping(source = "projectUuId", target = "project")
    @Mapping(source ="candidateUuId", target = "candidate")
    @Mapping(source = "userUuid", target = "inteviewer1")
    @Mapping(source = "userUuid", target = "interviewer2")
    ManagementDocumentation MyEntityConverter(ManagementDocumentationDTO manageDTO);

    @Mapping(source = "projectId", target = "project")
    @Mapping(source ="candidateId", target = "candidate")
    @Mapping(source = "userUuid", target = "inteviewer1")
    @Mapping(source = "userUuid", target = "interviewer2")
    ManagementDocumentationDTO MyDtoConverter(ManagementDocumentation manageEntity);
}
