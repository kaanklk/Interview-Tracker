package tcs.interviewtracker.persistence;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.lang.Nullable;
import lombok.Data;

@Entity
@Data
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name="uuid")
    private UUID uuid;

    @ManyToOne
    private Position position;

    @ManyToOne
    private Person person;

    @Column(columnDefinition = "varchar(128)")
    private CandidateStatus status;

    @Column(name = "projectId")
    private Long projectId;

    @Column(columnDefinition = "varchar(256)")
    @Nullable
    private String cvPath;

    @OneToMany(mappedBy = "candidate")
    private Set<WorkExperience> workExperiences;

    @OneToMany(mappedBy = "candidate")
    private Set<Education> educations;

    @OneToMany(mappedBy = "candidate")
    private Set<Language> langugages;

}
