package rnd.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rnd.auth.entity.User;
public interface UserRepository extends JpaRepository<User,Long> {
    User findOneByUsername(String username);
}