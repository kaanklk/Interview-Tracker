package tcs.interviewtracker.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import tcs.interviewtracker.DTOs.StatusChangeDTO;
import tcs.interviewtracker.persistence.StatusChange;
import tcs.interviewtracker.repository.StatusChangeRepository;

@Mapper(componentModel = "spring", 
        uses = {StatusChangeRepository.class})
public interface StatusChangeMapper {
    
    StatusChangeMapper INSTANCE = Mappers.getMapper(StatusChangeMapper.class);

    public StatusChange toEntity(StatusChangeDTO statusChangeDTO);

    public StatusChangeDTO toDTO(StatusChange statusChange);

}

