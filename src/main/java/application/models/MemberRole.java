package application.models;

import javax.persistence.*;

@Entity
@Table(name="memberrole")
public class MemberRole {

    public MemberRole() {

    }

    public MemberRole(String roleCode) {
        this.roleCode = roleCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    private String roleCode;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

}
