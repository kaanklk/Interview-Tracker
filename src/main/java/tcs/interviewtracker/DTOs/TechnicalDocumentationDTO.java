package tcs.interviewtracker.DTOs;

import java.sql.Date;
import java.util.UUID;

import org.apache.catalina.User;

import lombok.Data;
import tcs.interviewtracker.persistence.Candidate;

@Data
public class TechnicalDocumentationDTO {

    private UUID uuid;

    private UUID candidate;

    private Date date;

    private Integer duration;

    private String understandingOfRole;

    private String understandingComment;

    private Integer technicalSkills1;

    private Integer techSkillComment1;

    private Integer technicalSkills2;

    private Integer techSkillComment2;

    private Integer technicalSkills3;

    private Integer techSkillComment3;

    private Integer technicalSkills4;

    private Integer techSkillComment4;

    private Integer totalExperience;

    private Integer roleExperience;

    private Boolean isReccomended;

    private Integer lastComments;

    private UUID interviewerOne;

    private UUID interviewerTwo;

    private int designationOne;

    private int designationTwo;

    public TechnicalDocumentationDTO() {
    }



}
