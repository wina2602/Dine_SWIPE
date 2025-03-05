package dine.swipe.Dine.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "user_credentials")
public class UserCredentials {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String salt;

    @Column(nullable = false)
    private String hashed_password;
}
