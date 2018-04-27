package application.models;

import javax.persistence.*;

@Entity
@Table(name="credential")
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long credentialId;

    @OneToOne
    @JoinColumn(name="user_userid")
    private User user;

    private String passwordHash;

    private String passwordSalt;

    public long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(long credentialId) {
        this.credentialId = credentialId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }


}
