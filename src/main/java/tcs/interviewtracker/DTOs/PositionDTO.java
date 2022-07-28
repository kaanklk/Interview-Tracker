package tcs.interviewtracker.DTOs;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class PositionDTO {

    private UUID uuid;
    private String name;
    private UUID projectId;
    private Boolean open;
    private Integer hiredCount;
    private Integer totalCount;
}
