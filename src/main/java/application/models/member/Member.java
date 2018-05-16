package application.models.member;

import application.models.Credential;
import application.models.MemberRole;
import application.models.SecurityRole;
import application.models.User;

import javax.persistence.*;
import java.util.List;
import java.util.Vector;

@Entity
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long memberId;

    @OneToOne
    @JoinColumn(name="user_userid")
    protected User user;

    @OneToOne
    @JoinColumn(name="credential_credentialid")
    protected Credential credential;

    @ManyToMany
    @JoinColumn(name="memberrole_rolecode")
    private List<MemberRole> memberRoles = new Vector<MemberRole>();

    @ManyToMany
    @JoinColumn(name="securityrole_rolecode")
    private List<SecurityRole> securityRoles = new Vector<SecurityRole>();

    protected String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<MemberRole> getMemberRoles() {
        return memberRoles;
    }

    public void setMemberRoles(List<MemberRole> memberRoles) {
        this.memberRoles = memberRoles;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SecurityRole> getSecurityRoles() {
        return securityRoles;
    }

    public void setSecurityRoles(List<SecurityRole> securityRoles) {
        this.securityRoles = securityRoles;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

}
