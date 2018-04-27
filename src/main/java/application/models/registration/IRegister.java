package application.models.registration;

public interface IRegister {
    int minimumAge = 13;
    int maxUserNameSize = 36;
    int minUserNameSize = 3;
    String passwordRegex = "^[a-z0-9-_?.@.!]{"+ minUserNameSize + "," + maxUserNameSize + "}$";
    String userNameRegex= "[a-zA-Z_@0-9.#$]{3,32}";
}
