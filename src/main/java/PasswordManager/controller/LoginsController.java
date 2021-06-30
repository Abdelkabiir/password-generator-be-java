package PasswordManager.controller;


import PasswordManager.model.DeletedItem;
import PasswordManager.model.ItemsType;
import PasswordManager.model.Login;
import PasswordManager.model.Note;
import PasswordManager.repository.DeletedItemRepository;
import PasswordManager.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/logins")
public class LoginsController {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    DeletedItemRepository deletedItemRepository;

    @GetMapping("/")
    public ResponseEntity<List<Login>> getAllLogins() {
        try {
            List<Login> logins = new ArrayList<Login>();
            loginRepository.findAll().forEach(logins::add);

            if (logins.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(logins, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Login> createLogin(@RequestBody Login login) {
        try {
            Login _login = loginRepository.save(new Login(login.url, login.username, login.password, false));
            return new ResponseEntity<>(_login, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLogin(@PathVariable("id") String id) {
        Optional<Login> loginData = loginRepository.findById(id);
        if (loginData.isPresent()) {
            Login _login = loginData.get();
            _login.setDeleted(true);
            loginRepository.save(_login);
            deletedItemRepository.save(new DeletedItem(id, ItemsType.LOGIN));
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
