package tcs.interviewtracker.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TechnicalDocumentation {

    @Id
    private int id;
}
