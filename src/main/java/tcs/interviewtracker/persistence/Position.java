package tcs.interviewtracker.persistence;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(columnDefinition = "varchar(128)")
    private String positionName;

    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @Column(name = "total_count")
    @Nullable
    private Integer totalCount;

    @Column
    @Nullable
    private Integer hiredCount;

    @Column
    @Nullable
    private Boolean open;

    @ManyToMany
    @JoinTable(name = "position_has_interviewers", joinColumns = @JoinColumn(name = "position_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<User> interviewers;

    String ievolveRole;
    String ProjectSupportRole;
    String manager;
    ContractType contractType;
    String workingHours;
    String workingPlace;

    Date dateOfJoining;
    String purposeOfPosition;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "position_tasks", joinColumns = @JoinColumn(name = "position_id"))
    Set<Task> tasks;

    String degrees;
    String itSkills;
    String requiredExperience;

    ArrayList<String> advantages;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "position_langugagerequirements", joinColumns = @JoinColumn(name = "position_id"))
    Set<LanguageRequirement> languageRequirements;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "position_behavioralcompetencies", joinColumns = @JoinColumn(name = "position_id"))
    Set<BehavioralCompetency> behavioralCompetencies;

    String otherRequirements;
    String developmentAndCarreerOpportunities;
    String workingEnvironment;

}
