package com.example.backend.controller;

import com.example.backend.model.Note;
import com.example.backend.request.NoteFilterRequest;
import com.example.backend.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public String index() {
        return "redirect:/index.html";
    }

    @GetMapping
    public ResponseEntity<?> getAllNotes() {
        try {
            List<Note> notes = noteService.readAllNotes();
            return ResponseEntity.ok(notes);
        } catch (Exception e) {
            System.out.println("Error getting notes: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error to get notes" + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> getAllNotesPost() {
        try {
            List<Note> notes = noteService.readAllNotes();
            return ResponseEntity.ok(notes);
        } catch (Exception e) {
            System.out.println("Error getting notes: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error to get notes" + e.getMessage());
        }
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Note>> filterNotes(@RequestBody NoteFilterRequest filter) {
        List<Note> devices = noteService.filterNotes(filter);
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Note>> searchDevicesByName(@RequestParam String name) {
        return ResponseEntity.ok(noteService.searchNotesByName(name));
    }
}
