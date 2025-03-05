package dine.swipe.Dine.Service;

import dine.swipe.Dine.Models.User;
import dine.swipe.Dine.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUserById(UUID id){
        return userRepository.findById(id);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
