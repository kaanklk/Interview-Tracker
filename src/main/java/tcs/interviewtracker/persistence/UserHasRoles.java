package tcs.interviewtracker.persistence;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;

import lombok.NoArgsConstructor;

@Entity
@Table(name = UserHasRoles.TBL_NAME)
@NoArgsConstructor
public class UserHasRoles {

    protected static final String TBL_NAME = "user_roles";
    protected static final String FLD_USERID = "userId";
    protected static final String FLD_ROLEID = "roleId";
    protected static final String FLD_PROJECTID = "projectId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="uuid")
    private UUID uuid;

    @Column(name = FLD_USERID)
    private Long userId;

    @Column(name = FLD_ROLEID)
    private Long roleId;

    @Column(name = FLD_PROJECTID)
    private Long projectId;
}
