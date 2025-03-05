package dine.swipe.Dine.Controller;

import dine.swipe.Dine.DTO.UserCredsDTO;
import dine.swipe.Dine.Models.User;
import dine.swipe.Dine.Models.UserCredentials;
import dine.swipe.Dine.Service.UserService;
import dine.swipe.Dine.Utils.PasswordUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user){
        try{
            User savedUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request body");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id){
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> userList = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @PostMapping("/saveCredentials")
    public ResponseEntity<String> saveUserCredentials( @Valid @RequestBody UserCredsDTO userCredsDTO , BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));

            return ResponseEntity.badRequest().body(errorMessage);
        }
        String hashPwd = PasswordUtils.hashPassword(userCredsDTO.getPassword());
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUserName(userCredsDTO.getUserName());
        userCredentials.setHashedPassword(hashPwd);
        userService.saveCreds(userCredentials);

        return ResponseEntity.ok("credentials saved");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody UserCredsDTO userCredsDTO ,BindingResult bindingResult ){
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));

            return ResponseEntity.badRequest().body(errorMessage);
        }
        String userName = userCredsDTO.getUserName();

        if(userService.findByUserName(userName).isPresent()){
            Optional<UserCredentials>userCreds = userService.getCreds(userName);
            if(userCreds.isPresent()){
                String hashPwd = userCreds.map(UserCredentials::getHashedPassword).orElse(null);
                if(PasswordUtils.verifyPassword(userCredsDTO.getPassword(),hashPwd)){
                   return ResponseEntity.ok("Authentication sucessfull");
                }else{
                   return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid password");
                }
            }else{
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User credentials not found");
            }

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("username not found");
    }
}
