package tcs.interviewtracker.DTOs;

import java.util.UUID;

import lombok.Data;

@Data
public class ProjectDTO {

    private UUID uuid;
    private String name;
    private String description;
    private String deadline;
}
