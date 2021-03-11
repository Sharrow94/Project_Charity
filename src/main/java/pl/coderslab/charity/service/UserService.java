package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Users;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    void add(Users user);
    void saveUser(Users user,String siteURL);
    void delete(Long id);
    List<Users> getAll();
    Users getById(Long id);
    Users findByEmail(String email);
    Users findByUsername(String username);
    void sendVerificationEmail(Users user,String siteURL);
    void verifyAcc(String verifyCode);

}
