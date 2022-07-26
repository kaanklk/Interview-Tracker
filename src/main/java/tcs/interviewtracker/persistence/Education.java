package tcs.interviewtracker.persistence;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.Data;

@Entity
@Data
@Table(name = "education")
public class Education {
    
    @Id
    private int id;

    @ManyToOne
    private Candidate candidate;

    @NonNull
    private Timestamp start;

    @NonNull
    private Timestamp end;

    @NonNull
    private String institution;

    @NonNull
    private String information;
}
