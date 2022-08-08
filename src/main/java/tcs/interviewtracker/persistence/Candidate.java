package tcs.interviewtracker.persistence;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.Nullable;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @ManyToOne
    @ToString.Exclude
    private Position position;

    @ManyToOne
    @JoinColumn(name = "person_id", columnDefinition = "int4")
    @ToString.Exclude
    private Person person;

    // @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar(128)")
    private String status;

    @ManyToOne
    @ToString.Exclude
    private Project project;

    @Column(columnDefinition = "varchar(255)")
    @Nullable
    private String cvPath;

    /*
     * @OneToMany(mappedBy = "candidate")
     * private Set<WorkExperience> workExperiences;
     *
     * @OneToMany(mappedBy = "candidate")
     * private Set<Education> educations;
     *
     * @OneToMany(mappedBy = "candidate")
     * private Set<Language> langugages;
     */

}
