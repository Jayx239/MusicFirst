package application.models.registration;

import application.models.Member;
import application.models.User;
import application.repositories.MemberRepository;
import application.repositories.UserRepository;

public class Registration implements IRegister {

    public Registration(MemberRepository memberRepository, UserRepository userRepository) {
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;
    private MemberRepository memberRepository;
    private RegistrationResult result;

    public RegistrationResult registerUser(User user, String userName) {
        result = new RegistrationResult(true);
        UserValidate userValidate = new UserValidate(user,result);
        MemberValidate memberValidate = new MemberValidate(userName,null,result,memberRepository);

        if(!userValidate.validate()) {
            result.setSuccessful(false);
            return result;
        }

        if(!memberValidate.validate()) {
            result.setSuccessful(false);
            return result;
        }

        Member member = new Member();
        member.setUser(user);
        member.setMembershipName(userName);

        userRepository.save(user);
        memberRepository.save(member);
        result.setMember(member);

        return result;

    }
}
