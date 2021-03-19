package pl.coderslab.charity.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.RandomString;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(unique = true)
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
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
