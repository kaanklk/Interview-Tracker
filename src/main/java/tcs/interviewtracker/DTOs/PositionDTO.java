package tcs.interviewtracker.DTOs;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class PositionDTO {

    private UUID uuid;
    private String positionName;
    private UUID projectUuid;
    private Boolean open;
    private Integer hiredCount;
    private Integer totalCount;
}
