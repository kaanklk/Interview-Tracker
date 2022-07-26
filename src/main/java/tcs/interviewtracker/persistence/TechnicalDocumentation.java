package tcs.interviewtracker.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "TechnicalDocumentation")
public class TechnicalDocumentation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy  = GenerationType.AUTO)
    private int id;

    @OneToOne(mappedBy = "candidate")
    @Column(name = "candidate")
    private Candidate candidate;

    @Column(name = "date")
    private Date date;

    @Column(name = "duration")
    private int duration;

    @Column(name = "understanding_of_role")
    private String understandingOfRole;

    @Column(name = "understanding_comment")
    private String understandingComment;

    @Column(name = "technical_skills1")
    private int technicalSkills1;

    @Column(name = "tech_skill_comment1")
    private int techSkillComment1;

    @Column(name = "technical_skills2")
    private int technicalSkills2;

    @Column(name = "tech_skill_comment2")
    private int techSkillComment2;

    @Column(name = "technical_skills3")
    private int technicalSkills3;

    @Column(name = "tech_skill_comment3")
    private int techSkillComment3;

    @Column(name = "technical_skills4")
    private int technicalSkills4;

    @Column(name = "tech_skill_comment4")
    private int techSkillComment4;

    @Column(name = "total_experience")
    private int totalExperience;

    @Column(name = "role_experience")
    private int roleExperience;

    @Column(name = "is_reccomended")
    private boolean isReccomended;

    @Column(name = "last_comments")
    private int lastComments;

    @Column(name = "interviewer_one")
    @ManyToOne
    private User interviewerOne;

    @Column(name = "interviewer_two")
    @ManyToOne
    private User interviewerTwo;

    @Column(name ="designation_one")
    private int designationOne;

    @Column(name ="designation_two")
    private int designationTwo;
    



    
    

    




}
