package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Users;
import java.util.List;

public interface UserService {

    void add(Users user);
    void saveUser(Users user);
    void delete(Long id);
    List<Users> getAll();
    Users getById(Long id);
    Users findByEmail(String email);
    Users findByUsername(String username);

}
