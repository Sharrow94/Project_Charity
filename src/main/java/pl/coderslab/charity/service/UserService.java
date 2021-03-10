package pl.coderslab.charity.service;

import pl.coderslab.charity.model.User;
import java.util.List;

public interface UserService {

    void add(User user);
    void saveUser(User user);
    void delete(Long id);
    List<User> getAll();
    User getById(Long id);
    User findByEmail(String email);
    User findByUsername(String username);

}
