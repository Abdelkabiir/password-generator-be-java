package PasswordManager.controller;

import PasswordManager.model.DeletedItem;
import PasswordManager.model.Login;
import PasswordManager.repository.DeletedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/deleted-items")
public class DeletedItemsController {

    @Autowired
    DeletedItemRepository deletedItemRepository;

    @GetMapping("/")
    ResponseEntity<List<DeletedItem>> getAllDeletedItems() {
        try {
            List<DeletedItem> deletedItems = new ArrayList<DeletedItem>();
            deletedItemRepository.findAll().forEach(deletedItems::add);

            if (deletedItems.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(deletedItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
