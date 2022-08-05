package tcs.interviewtracker.DTOs;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ProjectDTO {
    private UUID uuid;
    private String name;
    private UUID projectManagerId;
    private String description;
    private List<UUID> recruiterId;
    private List<UUID> sourcerId;
    private List<UUID> interviewerId;
}
