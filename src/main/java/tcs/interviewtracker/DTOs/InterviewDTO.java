package tcs.interviewtracker.DTOs;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class InterviewDTO {
    private UUID uuid;

    private UUID candidateId;

    private UUID projectId;

    private UUID timeslotId;

    private String type;

    private UUID interviewerOneId;

    private UUID interviewerTwoId;

    private UUID documentationId;

    private Boolean isCompleted;
}
