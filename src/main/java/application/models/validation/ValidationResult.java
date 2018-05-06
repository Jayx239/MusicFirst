package application.models.validation;

import java.util.List;
import java.util.Vector;

public class ValidationResult {

    public ValidationResult(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
        errorMessages = new Vector<String>();
        successMessages = new Vector<String>();
        infoMessages = new Vector<String>();
    }

    protected boolean isSuccessful;
    protected List<String> errorMessages;
    protected List<String> successMessages;
    protected List<String> infoMessages;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<String> getSuccessMessages() {
        return successMessages;
    }

    public void setSuccessMessages(List<String> successMessages) {
        this.successMessages = successMessages;
    }

    public List<String> getInfoMessages() {
        return infoMessages;
    }

    public void setInfoMessages(List<String> infoMessages) {
        this.infoMessages = infoMessages;
    }


}
