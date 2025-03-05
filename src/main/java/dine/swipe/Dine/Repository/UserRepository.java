package dine.swipe.Dine.Repository;

import dine.swipe.Dine.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User , UUID> {
        Optional<User> findByUserName(String userName);
}
