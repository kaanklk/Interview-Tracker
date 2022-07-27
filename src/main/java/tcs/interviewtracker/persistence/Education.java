package tcs.interviewtracker.persistence;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.Data;

@Entity
@Data
@Table(name = "education")
public class Education {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id", nullable = false)
    @NonNull
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
