package application.contollers;

import application.models.Credential;
import application.models.logon.LogonCredentialRequest;
import application.models.member.Member;
import application.repositories.CredentialRepository;
import application.repositories.MemberRepository;
import application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/logon")
public class LogonController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    @PostMapping(path="/clientSubmit")
    public @ResponseBody
    String submit(@RequestBody LogonCredentialRequest request) {
        String userName = request.getUserName();
        String password = request.getPassword();
        return logonUser(userName,password);
    }

    @PostMapping(path="/submit")
    public @ResponseBody
    String submit(@RequestParam String userName, @RequestParam String password) {
        return logonUser(userName,password);
    }

    private @ResponseBody
    String logonUser(String userName, String password) {
        Member member = memberRepository.getMemberByMembershipName(userName);
        if(member == null)
            return "/logon";

        Credential credential = credentialRepository.findCredentialByMember(member);
        if(credential == null) {
            System.out.println("Error finding user credentials for userName: " + member.getMembershipName());
            return "/logon";
        }


        if(credential.matches(password)) {
            return "/dashboard";
        }
        else {
            return "/logon";
        }
    }

    @GetMapping(path="/logon")
    public @ResponseBody
    String logon() {
        return "logon";
    }
}
