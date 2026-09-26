package net.rachit.controller;


import lombok.NonNull;
import net.rachit.apiResponse.WeatherResponse;
import net.rachit.entity.User;

import net.rachit.repository.UserRepo;
import net.rachit.service.UserService;
import net.rachit.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService UserService;

    @Autowired
    private UserRepo UserRepo;



    @Autowired
    private WeatherService weatherService;




//    @GetMapping("/id/{ObjectId}")
//    public ResponseEntity<?> findbyId(@PathVariable ObjectId id) {
//        return new ResponseEntity<>(UserService.findById(id), HttpStatus.ACCEPTED);
//
//    }
//    @DeleteMapping("/id/{ObjectId}")
//    public ResponseEntity<?> DeletebyId(@PathVariable ObjectId id)
//    {
//        UserService.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.ACCEPTED);
//    }
     @PutMapping()
    public ResponseEntity<?> UpdateUser(@RequestBody @NonNull User User)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User Userindb = UserService.findbyUserName(username);
            Userindb.setUsername(User.getUsername());
            Userindb.setPassword(User.getPassword());
            UserService.SaveUser(Userindb);

            return new ResponseEntity<>(Userindb, HttpStatus.ACCEPTED);


    }

    @DeleteMapping
    public ResponseEntity<?> DeleteByUsername()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserRepo.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse response = weatherService.getWeather("Mumbai");
        String greeting = " ";
        if(response!= null)
        {
            greeting = " Weather feels like"+ response.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("HI"+ authentication.getName()+greeting,HttpStatus.OK);
    }


}
