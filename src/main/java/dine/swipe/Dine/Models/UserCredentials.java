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
    private String userName;

    @Column(nullable = false)
    private String hashedPassword;
}
