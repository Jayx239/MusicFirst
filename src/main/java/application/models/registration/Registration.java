package application.models.registration;

import application.models.Credential;
import application.models.member.Member;
import application.models.User;
import application.repositories.CredentialRepository;
import application.repositories.MemberRepository;
import application.repositories.UserRepository;

public class Registration implements IRegister {

    public Registration(MemberRepository memberRepository, UserRepository userRepository, CredentialRepository credentialRepository) {
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
        this.credentialRepository = credentialRepository;
    }

    private UserRepository userRepository;
    private MemberRepository memberRepository;
    private CredentialRepository credentialRepository;
    private RegistrationResult result;

    public RegistrationResult registerUser(User user, String userName, String password) {
        result = new RegistrationResult(true);
        UserValidate userValidate = new UserValidate(user,result);
        MemberValidate memberValidate = new MemberValidate(userName,null,result,memberRepository);
        CredentialValidate credentialValidate = new CredentialValidate(password, result);

        if(!userValidate.validate()) {
            result.setSuccessful(false);
            return result;
        }

        if(!memberValidate.validate()) {
            result.setSuccessful(false);
            return result;
        }

        if(!credentialValidate.validate()) {
            result.setSuccessful(false);
            return result;
        }

        userRepository.save(user);

        Member member = new Member();
        member.setUser(user);
        member.setMembershipName(userName);
        memberRepository.save(member);

        Credential credential = new Credential();
        credential.trySetPassword(password);
        credential.setMember(member);
        credentialRepository.save(credential);

        result.setMember(member);
        return result;

    }
}
