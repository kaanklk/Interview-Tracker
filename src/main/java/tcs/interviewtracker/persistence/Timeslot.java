package tcs.interviewtracker.persistence;

import java.sql.Timestamp;
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
    @Column(name="timeslot_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long timeslotId;

    @Column(name="start")
    private Timestamp timeslotStart;

    @Column(name="end")
    private Timestamp timeslotEnd;

    @Column(name="type")
    private String timeslotType;

    public Timeslot() {
    }

    public Timeslot(Timestamp timeslotStart, Timestamp timeslotEnd, String timeslotType) {
        this.timeslotStart = timeslotStart;
        this.timeslotEnd = timeslotEnd;
        this.timeslotType = timeslotType;
    }

}
