package PasswordManager.controller;

import PasswordManager.model.User;
import PasswordManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {

        try {
            User userData = userRepository.findUserByUsername(user.getUsername()).get(0);
            userData.setLoggedIn(true);
            return new ResponseEntity<>(userRepository.save(userData), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<User> logout(@RequestBody User user) {
        User userData = userRepository.findUserByUsername(user.getUsername()).get(0);

        try {
            userData.setLoggedIn(false);
            return new ResponseEntity<>(userRepository.save(userData), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
