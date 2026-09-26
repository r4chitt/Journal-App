package net.rachit.controller;

import net.rachit.entity.User;
import net.rachit.service.UserDetailServiceImpl;
import net.rachit.service.UserService;
import net.rachit.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService UserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtutil;

    @Autowired
    private UserDetailServiceImpl userDetailsService;


        @PostMapping("/sign_up")
        public ResponseEntity<?> signup(@RequestBody User User)
        {
            try {
                UserService.SaveNewUser(User);
                return new ResponseEntity<>(User, HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User User)
    {
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(User.getUsername(),User.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(User.getUsername());
            String jwt = jwtutil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>( jwt,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    }

