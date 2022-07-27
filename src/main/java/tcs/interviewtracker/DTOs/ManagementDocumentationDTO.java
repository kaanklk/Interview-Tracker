package tcs.interviewtracker.DTOs;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.Data;

@Data
public class ManagementDocumentationDTO {

    private UUID uuid;
    private Timestamp dateOfInterview;
    private Boolean mobileToWork;
    private Boolean floorVisit;
    private String motivation;
    private String careerAspiration;
    private String fitment;
    private String opennessToDiversity;
    private String proactiveness;
    private String potentionalConflict;
    private String teamFit;
    private String observations;
    private String otherComments;
    private String otherStrengths;
    private String otherWeaknesses;
    private Short relevantExperience;
    private Short totalExperience;
    private Boolean recommended;
    private String RGSID;
    private String directSupervisorName;
    private UUID candidateId;
    private UUID  projectId;
    private UUID interviewer1Id;
    private UUID interviewer2Id;

    public ManagementDocumentationDTO() {

    }

}
