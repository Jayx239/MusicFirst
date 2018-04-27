package application.models.registration;

import application.models.Member;
import application.models.MemberRole;
import application.models.User;
import application.models.validation.Validate;
import application.models.validation.ValidationResult;
import application.repositories.MemberRepository;
import application.repositories.UserRepository;

public class MemberValidate extends Validate implements IRegister {

    public MemberValidate(String userName, MemberRole role, ValidationResult result, MemberRepository memberRepository) {
        this.userName = userName;
        this.role = role;
        this.result = result;
        this.memberRepository = memberRepository;
    }

    private String userName;
    private MemberRole role;
    private ValidationResult result;
    private MemberRepository memberRepository;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }

    public ValidationResult getResult() {
        return result;
    }

    public void setResult(ValidationResult result) {
        this.result = result;
    }

    protected boolean isValidUserName() {
        // TODO: Add xss protection
        if(userName.trim().length() < minUserNameSize) {
            result.getErrorMessages().add("Username must not be blank");
            return false;
        }
        else if(userName.length() > maxUserNameSize) {
            result.getErrorMessages().add("Username too long");
            return false;
        }
        else if(!userName.matches(userNameRegex)) {
            result.getErrorMessages().add("Password failed regex requirements");
            return false;
        }

        return true;

    }

    protected boolean validateUserName() {
        if(!isValidUserName())
            return false;

        Iterable<Member> memberMatches = memberRepository.getAllByMembershipName(userName);
        if(memberMatches != null && memberMatches.iterator() != null && memberMatches.iterator().hasNext()) {
            result.getErrorMessages().add("User Name already in use");
            return false;
        }

        return true;

    }

    protected boolean validateRole() {
        return true;
    }

    public boolean validate() {
        return validateUserName();
    }

}
