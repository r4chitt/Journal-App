package net.rachit.controller;

import net.rachit.entity.User;
import net.rachit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService UserService;
   @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = UserService.getAll();
        if (!all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user)
    {
        UserService.SaveAdmin(user);
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }
}
