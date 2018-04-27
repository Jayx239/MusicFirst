package application.contollers;

import application.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import application.repositories.UserRepository;

import java.util.Optional;


@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/retrieve")
    public @ResponseBody Optional<User> retrieve(@RequestParam Long userId) {
        return userRepository.findById(userId);
    }

    @GetMapping(path="/retrieveByName")
    public @ResponseBody Iterable<User> getUserByName(@RequestParam String firstName, @RequestParam String middleName, @RequestParam String lastName) {
        return userRepository.findUsersByFirstNameOrMiddleNameOrLastName(firstName,middleName,lastName);
    }

}
