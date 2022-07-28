package tcs.interviewtracker.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

    @JoinColumn(name = FLD_USERID)
    @ManyToOne
    private User user;

    @JoinColumn(name = FLD_ROLEID)
    @ManyToOne
    private Role role;

    @JoinColumn(name = FLD_PROJECTID)
    @ManyToOne
    private Project project;
}