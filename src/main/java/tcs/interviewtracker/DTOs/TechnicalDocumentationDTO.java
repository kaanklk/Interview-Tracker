package tcs.interviewtracker.DTOs;

import java.sql.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TechnicalDocumentationDTO {
 
    private UUID uuid;

    private UUID candidateUuid;   

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
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
