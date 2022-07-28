package tcs.interviewtracker.persistence;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name="timeslot")
public class Timeslot {

    @Id
    @NonNull
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long timeslotId;

    @Column(name="uuid")
    private UUID uuid;

    @Column(name="start")
    private Timestamp timeslotStart;

    @Column(name="end")
    private Timestamp timeslotEnd;

    @Column(name="type")
    private String timeslotType;

    @Column(name="isCompleted")
    private boolean isCompleted = false;

    public Timeslot() {

    }

    public Timeslot(Timestamp timeslotStart, Timestamp timeslotEnd, String timeslotType) {
        this.timeslotStart = timeslotStart;
        this.timeslotEnd = timeslotEnd;
        this.timeslotType = timeslotType;
    }

}