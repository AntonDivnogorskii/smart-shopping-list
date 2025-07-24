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
@CrossOrigin(origins = "http://localhost:8080")
public class NoteController {
    private final NoteService noteService;

    //отдаём все записи
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

    //фильтр по количеству и флагу
    @PostMapping("/filter_especial")
    public ResponseEntity<List<Note>> filterNotes(@RequestBody NoteFilterRequest filter) {
        List<Note> devices = noteService.filterNotes(filter);
        return ResponseEntity.ok(devices);
    }

    //поиск по имени
    @GetMapping("/search")
    public ResponseEntity<List<Note>> searchNotesByName(@RequestParam String name) {
        return ResponseEntity.ok(noteService.searchNotesByName(name));
    }

    // Добавление нового товара
    @PostMapping
    public ResponseEntity<?> addNote(@RequestBody Note note) {
        try {
            Note savedNote = noteService.saveNote(note);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding note: " + e.getMessage());
        }
    }

    //получение записи по id
    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable Long id) {
        try {
            Note note = noteService.getNoteById(id);
            return ResponseEntity.ok(note);
        } catch (Exception e) {
            return ResponseEntity.status(404)
                    .body("Заметка не найдена");
        }
    }

    // Обновление товара
    @PutMapping("/update_note/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id, @RequestBody Note note) {
        try {
            note.setId(id);
            Note updatedDevice = noteService.updateNote(note);
            return ResponseEntity.ok(updatedDevice);
        } catch (Exception e) {
            System.out.println("Error updating product: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating product: " + e.getMessage());
        }
    }

    // Удаление товара
    @DeleteMapping("/delete_note/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        try {
            System.out.println("=== ADMIN: DELETING PRODUCT " + id + " ===");
            noteService.deleteNote(id);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting product: " + e.getMessage());
        }
    }

    // Обновление только поля indicator
    @PatchMapping("/{id}/indicator")
    public ResponseEntity<?> updateIndicator(@PathVariable Long id, @RequestParam boolean value) {
        try {
            Note note = noteService.getNoteById(id);
            note.setIndicator(value);
            Note updated = noteService.updateNote(note);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating indicator: " + e.getMessage());
        }
    }

}
