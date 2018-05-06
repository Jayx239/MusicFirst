package application.models.member;

import application.models.MemberRole;
import application.models.User;

import javax.persistence.*;

@Entity
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long memberId;

    @OneToOne
    @JoinColumn(name="user_userid")
    protected User user;

    @ManyToOne
    @JoinColumn(name="memberrole_rolecode")
    protected MemberRole role;

    protected String membershipName;

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
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

}
