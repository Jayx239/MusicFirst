package application.models;

import application.models.member.Member;
import application.models.registration.BCryptPasswordEncoderSingleton;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Random;

@Entity
@Table(name="credential")
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long credentialId;

    private String passwordHash;

    private String passwordSalt;

    public long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(long credentialId) {
        this.credentialId = credentialId;
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

    public String getBcrpyPasswordHash() {
        return "{bcrypt}" + passwordHash;
    }

    public boolean trySetPassword(String password)  {

        String salt = generateSalt(32);
        String saltedPassword = password + salt;
        String hash = generateHash(password);

        if(hash == null) {
            return false;
        } else {
            this.passwordSalt = salt;
            this.passwordHash = hash;
            return true;
        }
    }

    private String generateSalt(int size) {
        Random random = new SecureRandom();

        byte[] salt = new byte[size];
        random.nextBytes(salt);

        String saltString = new String(salt, Charset.forName("UTF-8"));
        return saltString;
    }

    private String generateHash(String saltedPassword) {
        return BCryptPasswordEncoderSingleton.getInstance().encode(saltedPassword);
    }

    public boolean matches(String password) {
        String saltedPassword = password;

        return BCryptPasswordEncoderSingleton.getInstance().matches(saltedPassword,passwordHash);
    }
}
