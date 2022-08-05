package tcs.interviewtracker.persistence;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "timeslot")
@AllArgsConstructor
@NoArgsConstructor
public class Timeslot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "start")
    private Timestamp timeslotStart;

    @Column(name = "endTime")
    private Timestamp timeslotEnd;

    @Column(name = "type")
    private String timeslotType;

    @Column(name = "isCompleted")
    private boolean isCompleted = false;

    @Column(name = "projectId")
    private Long projectId;

}