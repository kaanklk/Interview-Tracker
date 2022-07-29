package tcs.interviewtracker.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class InterviewType {
    protected static final String TBL_NAME = "interview_type";
    private static final String FLD_TYPENAME = "type_name";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = FLD_TYPENAME)
    private String typeName;
}
