package tcs.interviewtracker.DTOs;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ProjectDTO {
    private UUID uuid;
    private String name;
    private String description;
    private String numberOfAssociates;
    private String numberOfPositions;
    private String projectManagerName;
}
