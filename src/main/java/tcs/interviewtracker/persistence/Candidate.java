package tcs.interviewtracker.persistence;

import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
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
