package application.models.registration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderSingleton {
    private BCryptPasswordEncoderSingleton() {

    }

    private static BCryptPasswordEncoder passwordEncoder;
    public static int strength = 6;

    public static BCryptPasswordEncoder getInstance() {
        if(passwordEncoder == null)
            passwordEncoder = new BCryptPasswordEncoder(strength);
        return passwordEncoder;
    }
}
