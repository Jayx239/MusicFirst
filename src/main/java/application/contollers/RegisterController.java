package application.contollers;

import application.models.User;
import application.models.registration.Registration;
import application.models.registration.RegistrationRequest;
import application.models.validation.ValidationResult;
import application.repositories.CredentialRepository;
import application.repositories.MemberRepository;
import application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private Registration registration;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CredentialRepository credentialRepository;


    @PostMapping(path="/clientSubmit")
    public @ResponseBody
    String submit (@RequestBody RegistrationRequest registrationRequest) {
        return registerUser(registrationRequest.getFirstName(),
                registrationRequest.getMiddleName(),
                registrationRequest.getLastName(),
                registrationRequest.getEmail(),
                registrationRequest.getBirthMonth(),
                registrationRequest.getBirthDay(),
                registrationRequest.getBirthYear(),
                registrationRequest.getActive(),
                registrationRequest.getUserName(),
                registrationRequest.getPassword());
    }

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
                    @RequestParam String userName,
                    @RequestParam String password) {
        return registerUser(firstName,middleName,lastName,email,birthMonth,birthDay,birthYear,active,userName,password);
    }

    public @ResponseBody String registerUser(String firstName, String middleName,
                                             String lastName, String email,
                                             int birthMonth, int birthDay, int birthYear,
                                             int active, String userName, String password) {
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setMiddleName(middleName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setBirthMonth(birthMonth);
        newUser.setBirthDay(birthDay);
        newUser.setBirthYear(birthYear);
        newUser.setActive(active);

        registration = new Registration(memberRepository,userRepository, credentialRepository);
        ValidationResult result = registration.registerUser(newUser, userName, password);

        if(result.isSuccessful())
            return "Success";
        else
            return "Failure";
    }


    @GetMapping("/register")
    public @ResponseBody String register() {
        return "register";
    }


}
