package application.models.registration;

public interface IRegister {
    int minimumAge = 13;
    int maxUserNameSize = 36;
    int minUserNameSize = 3;
    int maxPasswordSize = 36;
    int minPasswordSize = 5;

    String passwordRegex = "[a-zA-Z0-9-_@?#$!%^&*()]{" + minPasswordSize + "," + maxPasswordSize + "}";
    String userNameRegex= "[a-zA-Z_@0-9.#$]{" + minUserNameSize + "," + maxUserNameSize+"}";
}
