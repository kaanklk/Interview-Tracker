package tcs.interviewtracker.persistence;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="uuid")
    private UUID uuid;

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
    private String information;
}
