package application.models;

import application.models.member.Member;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

@Entity
@Table(name="credential")
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long credentialId;

    @OneToOne
    @JoinColumn(name="member_memberId")
    private Member member;

    private byte[] passwordHash;

    private byte[] passwordSalt;

    public long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(long credentialId) {
        this.credentialId = credentialId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public boolean trySetPassword(String password)  {

        byte[] salt = generateSalt(32);
        byte[] saltedPassword = passwordSalt(password,salt);

        byte[] hash = generateHash(saltedPassword);
        if(hash == null) {
            return false;
        } else {
            this.passwordSalt = salt;
            this.passwordHash = hash;
            return true;
        }
    }

    private byte[] passwordSalt(String password, byte[] salt) {
        byte[] passwordByte = password.getBytes();
        byte[] saltedPassword = new byte[salt.length + passwordByte.length];

        System.arraycopy(passwordByte,0,saltedPassword,0,passwordByte.length);
        System.arraycopy(salt,0,saltedPassword,0,salt.length);
        return saltedPassword;
    }

    private byte[] generateSalt(int size) {
        Random random = new SecureRandom();
        byte[] salt = new byte[size];
        random.nextBytes(salt);
        return salt;
    }

    private byte[] generateHash(byte[] saltedPassword) {

        byte[] hash = null;
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            hash = sha256.digest(saltedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Error generating hash");
        } finally {
            return hash;
        }
    }

    public boolean matches(String password) {
        byte[] saltedPassword = passwordSalt(password,this.passwordSalt);
        byte[] passwordHash = generateHash(saltedPassword);

        return Arrays.equals(passwordHash,this.passwordHash);
    }
}
