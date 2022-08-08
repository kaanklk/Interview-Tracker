package tcs.interviewtracker.persistence;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    private UUID uuid;

    @ManyToOne
    @ToString.Exclude
    private Project project;

    @OneToOne
    private Timeslot timeslot;

    @ManyToOne
    private InterviewType type;

    @ManyToOne
    @ToString.Exclude
    private User interviewerOne;

    @ManyToOne
    @ToString.Exclude
    private User interviewerTwo;

    @OneToOne
    @ToString.Exclude
    private Candidate candidate;

    private Boolean isCompleted = false;
}
