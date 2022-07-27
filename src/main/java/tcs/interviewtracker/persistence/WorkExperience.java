package tcs.interviewtracker.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.Data;
import java.sql.Date;

@Entity
@Data
@Table(name = "work_experience")
public class WorkExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id", nullable = false)
    @NonNull
    private Candidate candidate;

    @NonNull
    private Date startDate;

    @NonNull
    private Date endDate;

    @NonNull
    private String institution;

    @NonNull
    private String summary;

    @NonNull
    private String name;
}
