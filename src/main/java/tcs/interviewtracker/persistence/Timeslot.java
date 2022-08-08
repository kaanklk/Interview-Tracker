package tcs.interviewtracker.persistence;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Timeslot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    private UUID uuid;

    private Timestamp startTime;

    private Timestamp endTime;

    @Column(columnDefinition = "varchar(128)")
    private String type;

    private Boolean isCompleted = false;
}