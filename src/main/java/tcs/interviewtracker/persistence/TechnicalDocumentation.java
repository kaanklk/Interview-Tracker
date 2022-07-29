package tcs.interviewtracker.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GenerationType;

import java.sql.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "technical_documentation")
public class TechnicalDocumentation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "UUID")
    private UUID uuid;
    
    @OneToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id", nullable = false)
    private Candidate candidate;

    @Column(name = "date")
    private Date date;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "understanding_of_role", nullable = true)
    private String understandingOfRole;

    @Column(name = "understanding_comment", nullable = true)
    private String understandingComment;

    @Column(name = "technical_skills1", nullable = true)
    private Integer technicalSkills1;

    @Column(name = "tech_skill_comment1", nullable = true)
    private String techSkillComment1;

    @Column(name = "technical_skills2", nullable = true)
    private Integer technicalSkills2;

    @Column(name = "tech_skill_comment2", nullable = true       )
    private String techSkillComment2;

    @Column(name = "technical_skills3", nullable = true)
    private Integer technicalSkills3;

    @Column(name = "tech_skill_comment3", nullable = true)
    private String techSkillComment3;

    @Column(name = "technical_skills4", nullable = true)
    private Integer technicalSkills4;

    @Column(name = "tech_skill_comment4", nullable = true)
    private String techSkillComment4;

    @Column(name = "total_experience", nullable = true)
    private Integer totalExperience;

    @Column(name = "role_experience", nullable = true)
    private Integer roleExperience;

    @Column(name = "is_reccomended", nullable = true)
    private Boolean isReccomended;

    @Column(name = "last_comments")
    private String lastComments;

    @JoinColumn(name = "interviewer_one", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private User interviewerOne;

    @JoinColumn(name = "interviewerTwo", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private User interviewerTwo;

    @Column(name = "designation_one", nullable = true)
    private int designationOne;

    @Column(name = "designation_two", nullable = true)
    private int designationTwo;

    public TechnicalDocumentation() {
    }

    public TechnicalDocumentation(Candidate candidate, Date date, Integer duration, String understandingOfRole,
            String understandingComment, Integer technicalSkills1, String techSkillComment1, Integer technicalSkills2,
            String techSkillComment2, Integer technicalSkills3, String techSkillComment3, Integer technicalSkills4,
            String techSkillComment4, Integer totalExperience, Integer roleExperience, Boolean isReccomended,
            String lastComments, User interviewerOne, User interviewerTwo, int designationOne, int designationTwo) {
        this.candidate = candidate;
        this.date = date;
        this.duration = duration;
        this.understandingOfRole = understandingOfRole;
        this.understandingComment = understandingComment;
        this.technicalSkills1 = technicalSkills1;
        this.techSkillComment1 = techSkillComment1;
        this.technicalSkills2 = technicalSkills2;
        this.techSkillComment2 = techSkillComment2;
        this.technicalSkills3 = technicalSkills3;
        this.techSkillComment3 = techSkillComment3;
        this.technicalSkills4 = technicalSkills4;
        this.techSkillComment4 = techSkillComment4;
        this.totalExperience = totalExperience;
        this.roleExperience = roleExperience;
        this.isReccomended = isReccomended;
        this.lastComments = lastComments;
        this.interviewerOne = interviewerOne;
        this.interviewerTwo = interviewerTwo;
        this.designationOne = designationOne;
        this.designationTwo = designationTwo;
    }

   

}
