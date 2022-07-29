package tcs.interviewtracker.persistence;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Interview.TBL_NAME)
@Data
@NoArgsConstructor
public class Interview {

    protected static final String TBL_NAME = "interview";

    private static final String FLD_PROJECTID = "projectId";
    private static final String FLD_TIMESLOTID = "timeslotId";
    private static final String FLD_TYPEID = "typeId";
    private static final String FLD_INTERVIEWERONEID = "interviewerOneId";
    private static final String FLD_INTERVIEWERTWOID = "interviewerTwoId";
    private static final String FLD_DOCUMENTID = "documentId";
    private static final String FLD_ISCOMPLETED = "isCompleted";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = FLD_PROJECTID)
    private Long projectId;

    @Column(name = FLD_TIMESLOTID)
    private Long timeslotId;

    @Column(name = FLD_TYPEID)
    private Long typeId;

    @Column(name = FLD_INTERVIEWERONEID)
    private Long interviewerOneId;

    @Column(name = FLD_INTERVIEWERTWOID)
    private Long interviewerTwoId;

    @Column(name = FLD_DOCUMENTID)
    private Long documentId;

    @Column(name = FLD_ISCOMPLETED)
    private Boolean isCompleted;
}
