package tcs.interviewtracker.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import tcs.interviewtracker.DTOs.TechnicalDocumentationDTO;
import tcs.interviewtracker.persistence.TechnicalDocumentation;
import tcs.interviewtracker.service.CandidateService;
import tcs.interviewtracker.service.TechnicalDocumentationService;
import tcs.interviewtracker.service.UserService;

@Mapper(componentModel = "spring",
        uses = {CandidateService.class, UserService.class, TechnicalDocumentationService.class})
public interface TechnicalDocumentationMapper {
    TechnicalDocumentationMapper INSTANCE = Mappers.getMapper(TechnicalDocumentationMapper.class);


    @Mapping(source = "candidateUUID", target = "candidate")
    @Mapping(source = "interviewerOneUUID", target = "interviewerOne")
    @Mapping(source = "interviewerTwoUUID", target = "interviewerTwo") 
    TechnicalDocumentation convertToEntity(TechnicalDocumentationDTO technicalDocumentationDTO);

    @Mapping( source = "candidate",target = "candidateUUID" )
    @Mapping(source = "interviewerOne", target = "interviewerOneUUID")
    @Mapping(source = "interviewerTwo", target = "interviewerTwoUUID") 
    TechnicalDocumentationDTO convertToDTO(TechnicalDocumentation technicalDocumentation);
}
