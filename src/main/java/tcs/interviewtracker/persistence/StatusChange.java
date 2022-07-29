package tcs.interviewtracker.persistence;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StatusChange {

    @Id
    private Long id;

    @Column(name="uuid")
    private UUID uuid;

    @ManyToOne
    @NonNull
    private Candidate candidate;

}
