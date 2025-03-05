package dine.swipe.Dine.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
public class UserCredsDTO {

    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3 ,max = 20 ,message ="Username must be between 3 and 20")
    private String userName;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    public void setUserName(String userName){
        this.userName =  userName.trim();
    }

    public void setPassword(String password){
        this.password = password.trim();
    }
}
