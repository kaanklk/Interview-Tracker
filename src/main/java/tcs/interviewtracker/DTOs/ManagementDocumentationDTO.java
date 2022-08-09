package tcs.interviewtracker.DTOs;

import java.sql.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ManagementDocumentationDTO {

    private UUID uuid;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date dateOfInterview;

    private Boolean mobileToWork;

    private Boolean floorVisit;

    private String motivation;

    private String careerAspiration;

    private String fitment;

    private String opennessToDiversity;

    private String proactiveness;

    private String potentionalConflict;

    private String observations;

    private String otherComments;

    private String otherStrengths;

    private String otherWeaknesses;

    private Short relevantExperience;

    private Short totalExperience;

    private Boolean recommended;

    private String RGSID;

    private String directSupervisorName;

    private UUID candidateUuid;

    private UUID projectUuid;

    private String projectSwonNumber;

    private UUID interviewer1Uuid;

    private UUID interviewer2Uuid;

}

