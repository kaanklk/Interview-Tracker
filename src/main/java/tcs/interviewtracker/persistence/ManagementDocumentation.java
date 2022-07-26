package tcs.interviewtracker.persistence;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "management_documentation")
public class ManagementDocumentation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @Column(columnDefinition = "varchar(250)")
    private String RGSID;

    @Column(columnDefinition = "varchar(256)")
    private String directSupervisorName;

    @OneToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    @Column(nullable = false)
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @Column(nullable = false)
    private Project project;

    @OneToMany
    @JoinColumn(name = "interviewer_id1", referencedColumnName = "id")
    @Column(nullable = false)
    private User interviewer1;

    @OneToMany
    @JoinColumn(name = "interviewer_id2", referencedColumnName = "id")
    @Column(nullable = false)
    private User interviewer2;


}
