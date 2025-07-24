package com.example.backend.service;

import com.example.backend.model.Note;
import com.example.backend.repository.NoteRepository;
import com.example.backend.request.NoteFilterRequest;
import com.example.backend.specification.NoteSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

//    public Optional<Note> findById(Long id) {
//        return noteRepository.findById(id);
//    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Заметка не найдена"));
    }

    public List<Note> readAllNotes(){
        return noteRepository.findAll();
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> searchNotesByName(String name) {
        if (name == null || name.isBlank()) {
            return Collections.emptyList();
        }
        return noteRepository.findByTitleContainingIgnoreCase(name);
    }

    public List<Note> filterNotes(NoteFilterRequest filter) {
        Specification<Note> spec = NoteSpecifications.withFilter(filter);
        return noteRepository.findAll(spec);
    }

    public Note updateNote(Note Note) {
        if (noteRepository.existsById(Note.getId())) {
            return noteRepository.save(Note);
        } else {
            throw new RuntimeException("Note not found with id: " + Note.getId());
        }
    }

    public void deleteNote(Long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Note not found with id: " + id);
        }
    }
}
