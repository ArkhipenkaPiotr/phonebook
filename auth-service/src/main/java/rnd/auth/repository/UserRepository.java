package rnd.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rnd.auth.entity.User;
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
    User findUserByUsernameAndPassword(String username, String password);
}