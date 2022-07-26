package tcs.interviewtracker.persistence;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="timeslot")
public class Timeslot {

    @Id
    @Column(name="timeslot_id")
    @NotNull
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

    @Override
    public String toString() {
        return "Timeslot [timeslotEnd=" + timeslotEnd + ", timeslotId=" + timeslotId + ", timeslotStart="
                + timeslotStart + ", timeslotType=" + timeslotType + "]";
    }

}
