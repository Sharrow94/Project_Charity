package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Users;
import java.util.List;

public interface UserService {

    void add(Users user);
    void saveUser(Users user,String siteURL);
    void delete(Long id);
    List<Users> getAll();
    Users getById(Long id);
    Users findByEmail(String email);
    Users findByUsername(String username);
    void verifyAcc(String verifyCode);
    List<Users> findAllByRoleAdmin();
    List<Users>findAllByRoleUser();
    void makeAdmin(Long id);
    void blockUser(Long id);
    Users findByVerifyCode(String verifyCode);
    void changePassword(Users user,String password);
    void editUser(Users user);

}
