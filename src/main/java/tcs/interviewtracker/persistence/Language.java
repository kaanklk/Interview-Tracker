package tcs.interviewtracker.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.Data;

@Entity
@Data
@Table(name = "language")
public class Language {
    
    @Id
    private int id;

    @ManyToOne
    private Candidate candidate;
    
    @NonNull
    private String language;

    @NonNull
    private String level;

}
