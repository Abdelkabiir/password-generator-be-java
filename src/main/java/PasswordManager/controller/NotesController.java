package PasswordManager.controller;

import PasswordManager.model.*;
import PasswordManager.repository.DeletedItemRepository;
import PasswordManager.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/notes")
public class NotesController {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    DeletedItemRepository deletedItemRepository;

    @GetMapping("/")
    public ResponseEntity<List<Note>> getAllNotes() {
        try {
            List<Note> notes = new ArrayList<Note>();
            noteRepository.findAll().forEach(notes::add);

            if (notes.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(notes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        try {
            Note _note = noteRepository.save(new Note(note.title, note.body, note.date, false));
            return new ResponseEntity<>(_note, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNote(@PathVariable("id") String id) {
        Optional<Note> noteData = noteRepository.findById(id);
        if (noteData.isPresent()) {
            Note _note = noteData.get();
            _note.setDeleted(true);
            noteRepository.save(_note);
            deletedItemRepository.save(new DeletedItem(id, ItemsType.NOTE));
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
