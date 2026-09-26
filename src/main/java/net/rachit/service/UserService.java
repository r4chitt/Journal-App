package net.rachit.service;


import net.rachit.entity.User;
import net.rachit.repository.UserRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepo UserRepo;
    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    public void SaveNewUser(User User)
    {
        try {
            User.setPassword(passwordEncoder.encode(User.getPassword()));
            User.setRoles(Arrays.asList("USER"));
            UserRepo.save(User);
            logger.info("Saved User");
        } catch (Exception e) {
            logger.error("Error occured for{}:",User.getUsername(),e);
        }
    }
    public void SaveAdmin(User User)
    {
        User.setPassword(passwordEncoder.encode(User.getPassword()));
        User.setRoles(Arrays.asList("USER","ADMIN"));
        UserRepo.save(User);
        logger.info("Saved Admin");
    }
    public void SaveUser(User user)
    {
        UserRepo.save(user);
    }
    public List<User> getAll(){
        return UserRepo.findAll();

    }
    public Optional<User> findById(ObjectId id){

        return UserRepo.findById(id);
    }
    public void deleteById(ObjectId id)
    {
        UserRepo.deleteById(id);
    }
    public User findbyUserName(String username)
    {
        return UserRepo.findByUsername(username);

    }
}
