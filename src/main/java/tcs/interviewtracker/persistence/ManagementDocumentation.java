package tcs.interviewtracker.persistence;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "management_documentation")
public class ManagementDocumentation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @Column(columnDefinition = "varchar(250)")
    private String RGSID;

    @Column(columnDefinition = "varchar(256)")
    private String directSupervisorName;

    @OneToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id", nullable = false)
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private Project project;

    private String projectSwonNumber;

    @ManyToOne
    @JoinColumn(name = "interviewer_id1", referencedColumnName = "id", nullable = false)
    private User interviewer1;

    @ManyToOne
    @JoinColumn(name = "interviewer_id2", referencedColumnName = "id", nullable = false)
    private User interviewer2;

}
