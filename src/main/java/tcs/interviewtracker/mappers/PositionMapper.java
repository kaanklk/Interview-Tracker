package tcs.interviewtracker.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import tcs.interviewtracker.DTOs.PositionDTO;
import tcs.interviewtracker.persistence.Position;

@Mapper
public interface PositionMapper {

    PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);

    PositionDTO positionToPositionDto(Position position);
}
