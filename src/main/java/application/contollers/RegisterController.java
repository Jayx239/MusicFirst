package application.contollers;

import application.models.User;
import application.models.registration.Registration;
import application.models.validation.ValidationResult;
import application.repositories.MemberRepository;
import application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private Registration registration;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/submit")
    public @ResponseBody
    String submit ( @RequestParam String firstName,
                    @RequestParam String middleName,
                    @RequestParam String lastName,
                    @RequestParam String email,
                    @RequestParam Integer birthMonth,
                    @RequestParam Integer birthDay,
                    @RequestParam Integer birthYear,
                    @RequestParam Integer active,
                    @RequestParam String userName) {
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setMiddleName(middleName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setBirthMonth(birthMonth);
        newUser.setBirthDay(birthDay);
        newUser.setBirthYear(birthYear);
        newUser.setActive(active);

        registration = new Registration(memberRepository,userRepository);
        ValidationResult result = registration.registerUser(newUser,userName);

        if(result.isSuccessful())
            return "Success";
        else
            return "Failure";
    }


}
