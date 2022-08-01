package tcs.interviewtracker.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import tcs.interviewtracker.DTOs.CandidateDTO;
import tcs.interviewtracker.persistence.Candidate;
import tcs.interviewtracker.service.CandidateService;
import tcs.interviewtracker.service.PersonService;
import tcs.interviewtracker.service.PositionService;

@Mapper(componentModel = "spring", uses = { CandidateService.class, PersonService.class, PositionService.class })
public interface CandidateMapper {
    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    @Mapping(source = "positionId", target = "position")
    @Mapping(source = "firstName", target = "person.firstName")
    @Mapping(source = "middleName", target = "person.middleName")
    @Mapping(source = "lastName", target = "person.lastName")
    Candidate toEntity(CandidateDTO candidateDTO);

    @Mapping(source = "position", target = "positionId")
    CandidateDTO toDTO(Candidate candidate);

}
