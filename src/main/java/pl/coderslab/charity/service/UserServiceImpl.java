package pl.coderslab.charity.service;

import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.Users;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JavaMailSender mailSender;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.mailSender = mailSender;
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
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
            userRepository.save(user);
            sendVerificationEmail(user,siteURL);

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

    @SneakyThrows
    @Override
    public void sendVerificationEmail(Users user, String siteURL){
            String toAddress = user.getEmail();
            String fromAddress = "charity.app@interia.pl";
            String senderName = "CharityApp01";
            String subject = "Please verify your registration";
            String content = "Dear [[name]],<br>"
                    + "Please click the link below to verify your registration:<br>"
                    + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                    + "Thank you,<br>"
                    + "CharityApp01.";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);

            content = content.replace("[[name]]", user.getFirstName());
            String verifyURL = siteURL + "/verify/" + user.getVerifyCode();

            content = content.replace("[[URL]]", verifyURL);

            helper.setText(content, true);
            mailSender.send(message);
    }

    @Override
    public void verifyAcc(String verifyCode) {
        Users user=userRepository.findByVerifyCode(verifyCode);
        user.setEnabled(true);
        userRepository.save(user);
    }
}
