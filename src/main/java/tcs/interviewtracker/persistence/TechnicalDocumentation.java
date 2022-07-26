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
@Table(name = "technical_documentation")
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

    
    public TechnicalDocumentation() {
    }

    public TechnicalDocumentation(int id, Candidate candidate, Date date, int duration, String understandingOfRole,
            String understandingComment, int technicalSkills1, int techSkillComment1, int technicalSkills2,
            int techSkillComment2, int technicalSkills3, int techSkillComment3, int technicalSkills4,
            int techSkillComment4, int totalExperience, int roleExperience, boolean isReccomended, int lastComments,
            User interviewerOne, User interviewerTwo, int designationOne, int designationTwo) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUnderstandingOfRole() {
        return understandingOfRole;
    }

    public void setUnderstandingOfRole(String understandingOfRole) {
        this.understandingOfRole = understandingOfRole;
    }

    public String getUnderstandingComment() {
        return understandingComment;
    }

    public void setUnderstandingComment(String understandingComment) {
        this.understandingComment = understandingComment;
    }

    public int getTechnicalSkills1() {
        return technicalSkills1;
    }

    public void setTechnicalSkills1(int technicalSkills1) {
        this.technicalSkills1 = technicalSkills1;
    }

    public int getTechSkillComment1() {
        return techSkillComment1;
    }

    public void setTechSkillComment1(int techSkillComment1) {
        this.techSkillComment1 = techSkillComment1;
    }

    public int getTechnicalSkills2() {
        return technicalSkills2;
    }

    public void setTechnicalSkills2(int technicalSkills2) {
        this.technicalSkills2 = technicalSkills2;
    }

    public int getTechSkillComment2() {
        return techSkillComment2;
    }

    public void setTechSkillComment2(int techSkillComment2) {
        this.techSkillComment2 = techSkillComment2;
    }

    public int getTechnicalSkills3() {
        return technicalSkills3;
    }

    public void setTechnicalSkills3(int technicalSkills3) {
        this.technicalSkills3 = technicalSkills3;
    }

    public int getTechSkillComment3() {
        return techSkillComment3;
    }

    public void setTechSkillComment3(int techSkillComment3) {
        this.techSkillComment3 = techSkillComment3;
    }

    public int getTechnicalSkills4() {
        return technicalSkills4;
    }

    public void setTechnicalSkills4(int technicalSkills4) {
        this.technicalSkills4 = technicalSkills4;
    }

    public int getTechSkillComment4() {
        return techSkillComment4;
    }

    public void setTechSkillComment4(int techSkillComment4) {
        this.techSkillComment4 = techSkillComment4;
    }

    public int getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }

    public int getRoleExperience() {
        return roleExperience;
    }

    public void setRoleExperience(int roleExperience) {
        this.roleExperience = roleExperience;
    }

    public boolean isReccomended() {
        return isReccomended;
    }

    public void setReccomended(boolean isReccomended) {
        this.isReccomended = isReccomended;
    }

    public int getLastComments() {
        return lastComments;
    }

    public void setLastComments(int lastComments) {
        this.lastComments = lastComments;
    }

    public User getInterviewerOne() {
        return interviewerOne;
    }

    public void setInterviewerOne(User interviewerOne) {
        this.interviewerOne = interviewerOne;
    }

    public User getInterviewerTwo() {
        return interviewerTwo;
    }

    public void setInterviewerTwo(User interviewerTwo) {
        this.interviewerTwo = interviewerTwo;
    }

    public int getDesignationOne() {
        return designationOne;
    }

    public void setDesignationOne(int designationOne) {
        this.designationOne = designationOne;
    }

    public int getDesignationTwo() {
        return designationTwo;
    }

    public void setDesignationTwo(int designationTwo) {
        this.designationTwo = designationTwo;
    }
    



    
    

    




}
