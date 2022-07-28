package tcs.interviewtracker.persistence;

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

    @ManyToOne
    @NonNull
    private Candidate candidate;

}
