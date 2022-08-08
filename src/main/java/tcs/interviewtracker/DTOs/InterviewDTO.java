package tcs.interviewtracker.DTOs;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class InterviewDTO {
    private UUID uuid;

    private UUID projectId;

    private UUID timeslotId;

    private String type;

    private UUID interviewerOneId;

    private UUID interviewerTwoId;

    private UUID documentId;

    private Boolean isCompleted;
}
