package tcs.interviewtracker.persistence;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import lombok.Data;

@Entity
@Data
@Table(name = "candidate")
public class Candidate {
    
    @Id
    @NonNull
    private int id;

    @ManyToOne
    private Position position;

    @ManyToOne
    private Person person;

    @Column(columnDefinition = "varchar(128)")
    private String status;

    @Column(columnDefinition = "varchar(256)")
    @Nullable
    private String cvPath;
}