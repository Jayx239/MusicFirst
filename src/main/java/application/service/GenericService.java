package application.service;

import org.springframework.security.core.userdetails.User;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface GenericService {
    User findByUsername(String username);

}
