package PasswordManager.controller;

import PasswordManager.model.DeletedItem;
import PasswordManager.model.ItemsType;
import PasswordManager.model.Note;
import PasswordManager.model.Password;
import PasswordManager.repository.DeletedItemRepository;
import PasswordManager.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/passwords")
public class PasswordController {

    @Autowired
    PasswordRepository passwordRepository;

    @Autowired
    DeletedItemRepository deletedItemRepository;

    @GetMapping("/")
    public ResponseEntity<List<Password>> getAllLPasswords() {
        try {
            List<Password> passwords = new ArrayList<Password>();
            passwordRepository.findAll().forEach(passwords::add);

            if (passwords.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(passwords, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Password> createPassword(@RequestBody Password password) {
        try {
            Password _password = passwordRepository.save(new Password(password.password, false));
            return new ResponseEntity<>(_password, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePassword(@PathVariable("id") String id) {
        try {
            Optional<Password> passwordData = passwordRepository.findById(id);
            if (passwordData.isPresent()) {
                Password _password = passwordData.get();
                _password.setDeleted(true);
                passwordRepository.save(_password);
                deletedItemRepository.save(new DeletedItem(id, ItemsType.PASSWORD));
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
