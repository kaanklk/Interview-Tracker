package tcs.interviewtracker.persistence;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="timeslot")
public class Timeslot {

    @Id
    @Column(name="timeslot_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int timeslotId;

    @Column(name="start")
    private Timestamp timeslotStart;

    @Column(name="end")
    private Timestamp timeslotEnd;

    @Column(name="type")
    private String timeslotType;

    public Timeslot() {
    }

    public Timeslot(int timeslotId, Timestamp timeslotStart, Timestamp timeslotEnd, String timeslotType) {
        this.timeslotId = timeslotId;
        this.timeslotStart = timeslotStart;
        this.timeslotEnd = timeslotEnd;
        this.timeslotType = timeslotType;
    }

    public int getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(int timeslotId) {
        this.timeslotId = timeslotId;
    }

    public Timestamp getTimeslotStart() {
        return timeslotStart;
    }

    public void setTimeslotStart(Timestamp timeslotStart) {
        this.timeslotStart = timeslotStart;
    }

    public Timestamp getTimeslotEnd() {
        return timeslotEnd;
    }

    public void setTimeslotEnd(Timestamp timeslotEnd) {
        this.timeslotEnd = timeslotEnd;
    }

    public String getTimeslotType() {
        return timeslotType;
    }

    public void setTimeslotType(String timeslotType) {
        this.timeslotType = timeslotType;
    }



    @Override
    public String toString() {
        return "Timeslot [timeslotEnd=" + timeslotEnd + ", timeslotId=" + timeslotId + ", timeslotStart="
                + timeslotStart + ", timeslotType=" + timeslotType + "]";
    }

}
