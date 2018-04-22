package application.repositories;

import application.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findUsersByFirstNameOrMiddleNameOrLastName(String firstName, String middleName, String lastName);
}
