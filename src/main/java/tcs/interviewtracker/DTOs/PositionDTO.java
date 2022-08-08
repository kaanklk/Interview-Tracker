package tcs.interviewtracker.DTOs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import tcs.interviewtracker.persistence.BehavioralCompetency;
import tcs.interviewtracker.persistence.ContractType;
import tcs.interviewtracker.persistence.LanguageRequirement;
import tcs.interviewtracker.persistence.Task;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class PositionDTO {

    private UUID uuid;
    private String positionName;
    private UUID projectUuid;
    private Boolean open;
    private Integer hiredCount;
    private Integer totalCount;

    private String ievolveRole;
    private String ProjectSupportRole;
    private String manager;
    private ContractType contractType;
    private String workingHours;
    private String workingPlace;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfJoining;
    private String purposeOfPosition;
    private ArrayList<Task> tasks;
    private String degrees;
    private String itSkills;
    private ArrayList<String> requiredExperience;
    private Set<LanguageRequirement> languageRequirements;
    private String advantages;
    private Set<BehavioralCompetency> behavioralCompetencies;
    private String otherRequirements;
    private String developmentAndCarreerOpportunities;
    private String workingEnvironment;

}
