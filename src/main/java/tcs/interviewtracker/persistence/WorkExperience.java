package tcs.interviewtracker.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.Data;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "work_experience")
public class WorkExperience {

    @Id
    private int id;

    @ManyToOne
    @NonNull
    private Candidate candidate;

    @NonNull
    private Timestamp start;

    @NonNull
    private Timestamp end;

    @NonNull
    private String institution;

    @NonNull
    private String summary;

    @NonNull
    private String name;
}
