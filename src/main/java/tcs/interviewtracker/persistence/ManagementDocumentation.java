package tcs.interviewtracker.persistence;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
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

    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @Column(nullable = false)
    private Project project;

    @OneToOne
    @JoinColumn(name = "interviewer_id1", referencedColumnName = "id")
    @Column(nullable = false)
    private User interviewer1;

    @OneToOne
    @JoinColumn(name = "interviewer_id2", referencedColumnName = "id")
    @Column(nullable = false)
    private User interviewer2;


    public ManagementDocumentation() {
    }

    public ManagementDocumentation(Timestamp dateOfInterview, boolean mobileToWork, boolean floor_visit,
            String motivation, String careerAspiration, String fitment, String opennessToDiversity,
            String proactiveness, String potentionalConflict, String teamFit, String observations, String othrComments,
            String otherStrengths, String otherWeaknesses, short relevantExperience, short totalExperience,
            boolean recommended, String directSupervisorName, String rGSID, Candidate candidate, Project project,
            User interviewer1, User interviewer2) {
        this.dateOfInterview = dateOfInterview;
        this.mobileToWork = mobileToWork;
        this.floor_visit = floor_visit;
        this.motivation = motivation;
        this.careerAspiration = careerAspiration;
        this.fitment = fitment;
        this.opennessToDiversity = opennessToDiversity;
        this.proactiveness = proactiveness;
        this.potentionalConflict = potentionalConflict;
        this.teamFit = teamFit;
        this.observations = observations;
        this.othrComments = othrComments;
        this.otherStrengths = otherStrengths;
        this.otherWeaknesses = otherWeaknesses;
        this.relevantExperience = relevantExperience;
        this.totalExperience = totalExperience;
        this.recommended = recommended;
        this.directSupervisorName = directSupervisorName;
        RGSID = rGSID;
        this.candidate = candidate;
        this.project = project;
        this.interviewer1 = interviewer1;
        this.interviewer2 = interviewer2;
    }


    public Long getId() {
        return id;
    }

    public Timestamp getDateOfInterview() {
        return dateOfInterview;
    }

    public void setDateOfInterview(Timestamp dateOfInterview) {
        this.dateOfInterview = dateOfInterview;
    }

    public boolean isMobileToWork() {
        return mobileToWork;
    }

    public void setMobileToWork(boolean mobileToWork) {
        this.mobileToWork = mobileToWork;
    }

    public boolean isFloor_visit() {
        return floor_visit;
    }

    public void setFloor_visit(boolean floor_visit) {
        this.floor_visit = floor_visit;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getCareerAspiration() {
        return careerAspiration;
    }

    public void setCareerAspiration(String careerAspiration) {
        this.careerAspiration = careerAspiration;
    }

    public String getFitment() {
        return fitment;
    }

    public void setFitment(String fitment) {
        this.fitment = fitment;
    }

    public String getOpennessToDiversity() {
        return opennessToDiversity;
    }

    public void setOpennessToDiversity(String opennessToDiversity) {
        this.opennessToDiversity = opennessToDiversity;
    }

    public String getProactiveness() {
        return proactiveness;
    }

    public void setProactiveness(String proactiveness) {
        this.proactiveness = proactiveness;
    }

    public String getPotentionalConflict() {
        return potentionalConflict;
    }

    public void setPotentionalConflict(String potentionalConflict) {
        this.potentionalConflict = potentionalConflict;
    }

    public String getTeamFit() {
        return teamFit;
    }

    public void setTeamFit(String teamFit) {
        this.teamFit = teamFit;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getOthrComments() {
        return othrComments;
    }

    public void setOthrComments(String othrComments) {
        this.othrComments = othrComments;
    }

    public String getOtherStrengths() {
        return otherStrengths;
    }

    public void setOtherStrengths(String otherStrengths) {
        this.otherStrengths = otherStrengths;
    }

    public String getOtherWeaknesses() {
        return otherWeaknesses;
    }

    public void setOtherWeaknesses(String otherWeaknesses) {
        this.otherWeaknesses = otherWeaknesses;
    }

    public short getRelevantExperience() {
        return relevantExperience;
    }

    public void setRelevantExperience(short relevantExperience) {
        this.relevantExperience = relevantExperience;
    }

    public short getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(short totalExperience) {
        this.totalExperience = totalExperience;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public String getDirectSupervisorName() {
        return directSupervisorName;
    }

    public void setDirectSupervisorName(String directSupervisorName) {
        this.directSupervisorName = directSupervisorName;
    }

    public String getRGSID() {
        return RGSID;
    }

    public void setRGSID(String rGSID) {
        RGSID = rGSID;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getInterviewer1() {
        return interviewer1;
    }

    public void setInterviewer1(User interviewer1) {
        this.interviewer1 = interviewer1;
    }

    public User getInterviewer2() {
        return interviewer2;
    }

    public void setInterviewer2(User interviewer2) {
        this.interviewer2 = interviewer2;
    }



}
