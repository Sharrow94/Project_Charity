package pl.coderslab.charity.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    private String username;
    private String verifyCode;
    private boolean enabled=false;

    public void setUsername() {
        this.username = this.email;
    }

    public void setVerifyCode(){
        this.verifyCode= RandomString.make(64);
    }

    public void  whenCreatedAcc(){
        this.setUsername();
        this.setVerifyCode();
    }
}
