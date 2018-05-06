package application.models.registration;

import application.models.validation.Validate;
import application.models.validation.ValidationResult;

public class CredentialValidate extends Validate implements IRegister {

    public CredentialValidate(String password, ValidationResult result) {
        this.password = password;
        this.result = result;
    }

    String password;
    ValidationResult result;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean validate() {
        return isValidPassword();
    }

    protected boolean isValidPassword() {
        if(password.length() < minPasswordSize) {
            result.getErrorMessages().add("Password not long enough");
            return false;
        }
        else if(password.length() > maxPasswordSize) {
            result.getErrorMessages().add("Password too long");
            return false;
        }
        else if(!password.matches(passwordRegex)) {
            result.getErrorMessages().add("Password failed regex requirements");
            return false;
        }

        return true;

    }
}
