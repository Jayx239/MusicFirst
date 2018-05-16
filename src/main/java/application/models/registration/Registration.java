package application.models.registration;

import application.models.Credential;
import application.models.SecurityRole;
import application.models.member.Member;
import application.models.User;
import application.repository.*;

public class Registration implements IRegister {

    public Registration(MemberRepository memberRepository, UserRepository userRepository, CredentialRepository credentialRepository, SecurityRoleRepository securityRoleRepository, MemberRoleRepository memberRoleRepository) {
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
        this.credentialRepository = credentialRepository;
        this.securityRoleRepository = securityRoleRepository;
        this.memberRoleRepository = memberRoleRepository;
    }

    private UserRepository userRepository;
    private MemberRepository memberRepository;
    private CredentialRepository credentialRepository;
    private SecurityRoleRepository securityRoleRepository;
    private MemberRoleRepository memberRoleRepository;

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

        Credential credential = new Credential();
        credential.trySetPassword(password);
        credentialRepository.save(credential);

        Member member = new Member();
        member.setUser(user);
        member.setUserName(userName);
        member.setCredential(credential);
        member.getSecurityRoles().add(securityRoleRepository.findByRoleCode("STANDARD"));
        member.getMemberRoles().add(memberRoleRepository.findByRoleCode("USER"));
        memberRepository.save(member);

        result.setMember(member);
        return result;

    }
}
