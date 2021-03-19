package pl.coderslab.charity.service;

import lombok.SneakyThrows;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Users;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Async
    @SneakyThrows
    public void sendNotificationAboutGift(Users user) throws MailException {
        String toAddress = user.getEmail();
        String senderName = "CharityApp01";
        String subject = "Notification";
        String content = "Dear [[name]],<br>"
                + "A new gift has been reported<br>"
                + "CharityApp01.";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        content = content.replace("[[name]]", user.getFirstName());
        helper.setText(content, true);
        mailSender.send(message);
    }

    @Async
    @SneakyThrows
    public void sendVerificationEmail(Users user, String siteURL){
        String toAddress = user.getEmail();
        String senderName = "CharityApp01";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "CharityApp01.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        content = content.replace("[[name]]", user.getFirstName());
        String verifyURL = siteURL + "/verify/" + user.getVerifyCode();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);
        mailSender.send(message);
    }

    @Async
    @SneakyThrows
    public void sendTokenToResetPass(Users user, String siteURL) {
        String toAddress = user.getEmail();
        String senderName = "CharityApp01";
        String subject = "Reset your password";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to reset your password:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">RESET PASSWORD</a></h3>"
                + "Thank you,<br>"
                + "CharityApp01.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        content = content.replace("[[name]]", user.getFirstName());
        String changePassURL = siteURL + "/resetPassword/step2/" + user.getVerifyCode();
        content = content.replace("[[URL]]", changePassURL);
        helper.setText(content, true);
        mailSender.send(message);
    }
}
