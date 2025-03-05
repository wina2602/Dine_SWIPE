package dine.swipe.Dine.Repository;

import dine.swipe.Dine.Models.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials,String> {

    Optional<UserCredentials> findByUserName(String userName);
}
