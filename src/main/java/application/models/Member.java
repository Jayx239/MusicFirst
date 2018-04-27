package application.models;

import javax.persistence.*;

@Entity
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long memberId;

    @OneToOne
    @JoinColumn(name="user_userid")
    private User user;

    @ManyToOne
    @JoinColumn(name="memberrole_rolecode")
    private MemberRole role;

    private String membershipName;

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
