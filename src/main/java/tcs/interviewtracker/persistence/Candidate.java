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

    private UUID uuid;

    @ManyToOne
    @ToString.Exclude
    private Position position;

    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Person person;

    // @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(128)")
    private String status;

    @ManyToOne
    @ToString.Exclude
    private Project project;

    @Column(columnDefinition = "varchar(255)")
    @Nullable
    private String cvPath;
}
