package tcs.interviewtracker.DTOs;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class CandidateDTO {

    private UUID uuid;

    private UUID positionId;

    private UUID projectId;

    private String dateOfBirth;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phone;

    private List<LanguageDTO> languages;

    private List<WorkExperienceDTO> workExperiences;

    private List<EducationDTO> educations;

    private String status;

    private String cvPath;

    private UUID technicalInterviewerId;

    private UUID technicalInterviewerId2;

    private UUID managementInterviewerId;

    private UUID managementInterviewerId2;

    private UUID technicalDocumentationId;

    private UUID managementDocumentationId;

    private List<TimeslotDTO> possibleTimeslots;

}
