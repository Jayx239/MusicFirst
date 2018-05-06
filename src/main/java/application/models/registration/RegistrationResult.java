package application.models.registration;

import application.models.member.Member;
import application.models.validation.ValidationResult;

public class RegistrationResult extends ValidationResult {

    public RegistrationResult(boolean isSuccessful) {
        super(isSuccessful);
        member = null;
    }

    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }


}
