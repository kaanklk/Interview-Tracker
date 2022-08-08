package tcs.interviewtracker.DTOs;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class InterviewDTO {
    private Long uuid;

    private Long projectId;

    private Long timeslotId;

    private String type;

    private Long interviewerOneId;

    private Long interviewerTwoId;

    private Long documentId;

    private Boolean isCompleted;
}
