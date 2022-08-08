package tcs.interviewtracker.DTOs;

import java.sql.Date;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import tcs.interviewtracker.properties.TechnicalSkills;

@Data
public class TechnicalDocumentationDTO {
 
    private UUID uuid;

    private UUID candidateUuid;   

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date date;

    private Integer duration;

    private String understandingOfRole;

    private String understandingComment;
  
    private List<TechnicalSkills> skills;

    private Integer totalExperience;

    private Integer roleExperience;

    private Boolean isReccomended;

    private String lastComments;

    private UUID interviewerOneUUID;

    private UUID interviewerTwoUUID;

    private int designationOne;

    private int designationTwo;

    public TechnicalDocumentationDTO() {
    skills = new ArrayList<TechnicalSkills>();
    }

}
