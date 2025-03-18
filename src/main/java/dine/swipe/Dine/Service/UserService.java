package dine.swipe.Dine.Service;

import dine.swipe.Dine.Models.User;
import dine.swipe.Dine.Models.UserCredentials;
import dine.swipe.Dine.Repository.UserCredentialsRepository;
import dine.swipe.Dine.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @CachePut(value="users",key ="'username_' +#result.id" )
    public User createUser(User user){
        return userRepository.save(user);
    }

    @Cacheable(value = "users" ,key = "'username_' + #id")
    public Optional<User> getUserById(UUID id){
        return userRepository.findById(id);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void saveCreds(UserCredentials userCredentials){
         userCredentialsRepository.save(userCredentials);
    }

    public Optional<UserCredentials> getCreds(String userName){
        return userCredentialsRepository.findByUserName(userName);
    }

    public  Optional<User> findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
