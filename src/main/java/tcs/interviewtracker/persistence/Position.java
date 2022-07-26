package tcs.interviewtracker.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "varchar(128)")
    private String positionName;

    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;



    public Position(String positionName, Project project, Candidate candidate) {
        this.positionName = positionName;
        this.project = project;
        this.candidate = candidate;
    }

    public Position() {
    }

    public Long getId() {
        return id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Project getProject() {
        return project;
    }

    public void setProjetc(Project project) {
        this.project = project;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @OneToOne(mappedBy = "position")
    private Candidate candidate;





}
