package application.models.registration;

import application.models.User;
import application.models.validation.ValidationResult;
import application.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

public class UserValidate implements IRegister {
    public UserValidate(User user, ValidationResult result) {
        this.user = user;
        this.result = result;
    }
    private User user;
    private ValidationResult result;

    protected boolean validateBirthInfo(int birthDay,int birthMonth,int birthYear) {
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.set(birthYear,birthMonth,birthDay);
        try {
            Date date = calendar.getTime();
            if(!date.toInstant().isBefore(LocalDateTime.now().minusYears(minimumAge).toInstant(ZoneOffset.MAX))) {
                result.getErrorMessages().add("User not old enough");
                return false;
            }
        } catch(Exception e) {
            result.getErrorMessages().add("Invalid date entered");
            return false;
        }
        return true;
    }

    protected boolean validateUser() {
        if(user.getFirstName().trim().length() < 1) {
            result.getErrorMessages().add("Invalid first name");
            return false;
        }
        else if(user.getLastName().trim().length() < 1) {
            result.getErrorMessages().add("Invalid first name");
            return false;
        }
        else if(!validateBirthInfo(user.getBirthDay(),user.getBirthMonth(),user.getBirthYear())) {
            return false;
        }

        return true;
    }

    public boolean validate() {
        return validateUser();
    }
}
