package com.example.easynotes.controller;

import com.example.easynotes.dto.NoteDto;
import com.example.easynotes.service.NoteService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NoteController {
    NoteService noteService;

    @GetMapping("/notes")
    public List<NoteDto> getAllNotes() {
        return noteService.getAllNotes();
    }

    @PostMapping("/note")
    public ResponseEntity createNote(
            @Valid // validates @NotBlank in dto, will return 400 BAD REQUEST if content or title is blank
            @RequestBody NoteDto noteDto) {
        return ok(noteService.createNote(noteDto));
    }

    @GetMapping("/note/{id}")
    public NoteDto getSingleNote(@PathVariable(value = "id") Long id) {
        return noteService.getNoteById(id);
    }

    @PutMapping("/note/{id}")
    public ResponseEntity updateNote(@PathVariable(value = "id") Long id,
                                     @Valid
                                     @RequestBody NoteDto noteDto) {
        return ok(noteService.updateNote(id, noteDto));
    }

    @DeleteMapping("/note/{id}}")
    public ResponseEntity deleteNote(@PathVariable(value = "id") Long id) {
        noteService.deleteNote(id);

        return ResponseEntity.ok().build();
    }
}
