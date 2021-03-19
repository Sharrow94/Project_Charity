package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.Users;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
    Users findByUsername(String username);
    Users findByVerifyCode(String verifyCode);
    @Query(value = "select users.id, email, first_name, last_name, password, username, enabled, verify_code from users join users_roles ur on users.id = ur.users_id where roles_id=2", nativeQuery = true)
    List<Users>findAllByRoleAdmin();
    @Query(value = "select users.id, email, first_name, last_name, password, username, enabled, verify_code from users join users_roles ur on users.id = ur.users_id where roles_id=1", nativeQuery = true)
    List<Users>findAllByRoleUser();
}
