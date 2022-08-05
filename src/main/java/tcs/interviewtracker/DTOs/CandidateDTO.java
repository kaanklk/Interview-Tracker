package tcs.interviewtracker.DTOs;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CandidateDTO {

    private UUID uuid;

    private UUID positionId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phone;

    private List<LanguageDTO> languages;

    private List<WorkExperienceDTO> workExperiences;

    private List<EducationDTO> educations;

    private String status;

    private UUID cvId;

    private UUID technicalInterviewerId;

    private UUID technicalInterviewerId2;

    private UUID managementInterviewerId;

    private UUID managementInterviewerId2;

    private UUID technicalDocumentationId;

    private UUID managementDocumentationId;

    private List<TimeslotDTO> possibleTimeslots;

}
