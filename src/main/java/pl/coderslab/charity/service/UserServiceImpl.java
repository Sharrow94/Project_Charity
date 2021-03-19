package pl.coderslab.charity.service;

import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.Users;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final MailService mailService;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.mailService = mailService;
    }

    @Override
    public void add(Users user) {
        user.whenCreatedAcc();
        userRepository.save(user);
    }

    @SneakyThrows
    @Override
    public void saveUser(Users user, String siteURL) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Role userRole = roleRepository.findByName("ROLE_USER");
            user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
            userRepository.save(user);
            mailService.sendVerificationEmail(user,siteURL);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Users> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Users getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void verifyAcc(String verifyCode) {
        Users user=userRepository.findByVerifyCode(verifyCode);
        user.setEnabled(true);
        user.setVerifyCode(null);
        userRepository.save(user);
    }

    @Override
    public List<Users>findAllByRoleAdmin() {
        return userRepository.findAllByRoleAdmin();
    }

    @Override
    public List<Users> findAllByRoleUser() {
        return userRepository.findAllByRoleUser();
    }

    @Override
    public void makeAdmin(Long id) {
        Users user=userRepository.findById(id).get();
        Set<Role>userRoles=user.getRoles();
        if (userRoles.contains(roleRepository.findByName("ROLE_ADMIN"))){
            userRoles.remove(roleRepository.findByName("ROLE_ADMIN"));
            userRoles.add(roleRepository.findByName("ROLE_USER"));
        }else{
            userRoles.add(roleRepository.findByName("ROLE_ADMIN"));
            userRoles.remove(roleRepository.findByName("ROLE_USER"));
        }
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    @Override
    public void blockUser(Long id) {
        Users user = userRepository.findById(id).get();
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
    }

    @Override
    public Users findByVerifyCode(String verifyCode) {
        return userRepository.findByVerifyCode(verifyCode);
    }

    @Override
    public void changePassword(Users user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        user.setVerifyCode(null);
        userRepository.save(user);
    }

    @Override
    public void editUser(Users user) {
        userRepository.save(user);
    }

}
