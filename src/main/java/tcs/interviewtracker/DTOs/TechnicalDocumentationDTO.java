package tcs.interviewtracker.DTOs;

import java.sql.Date;
import java.util.UUID;

import org.apache.catalina.User;

import lombok.Data;
import tcs.interviewtracker.persistence.Candidate;

@Data
public class TechnicalDocumentationDTO {
 
    private UUID uuid;

    private UUID candidateUUID;

    private Date date;

    private Integer duration;

    private String understandingOfRole;

    private String understandingComment;

    private Integer technicalSkills1;

    private String techSkillComment1;

    private Integer technicalSkills2;

    private String techSkillComment2;

    private Integer technicalSkills3;

    private String techSkillComment3;

    private Integer technicalSkills4;

    private String techSkillComment4;

    private Integer totalExperience;

    private Integer roleExperience;

    private Boolean isReccomended;

    private String lastComments;

    private UUID interviewerOneUUID;

    private UUID interviewerTwoUUID;

    private int designationOne;

    private int designationTwo;

    public TechnicalDocumentationDTO() {
    }



}
