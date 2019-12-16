package com.example.easynotes.service;

import com.example.easynotes.converter.NoteConverter;
import com.example.easynotes.dto.NoteDto;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class NoteService {
    NoteRepository noteRepository;
    NoteConverter noteConverter;

    public List<NoteDto> getAllNotes() {
        List<Note> notes = noteRepository.findAll();

        return noteConverter.toDtos(notes);
    }

    public Note createNote(@Valid @RequestBody NoteDto note) {
        return noteRepository.save(noteConverter.toEntity(note));
    }

    public NoteDto getNoteById(long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        return noteConverter.toDto(note);
    }

    public Note updateNote(Long id, NoteDto noteDto) {
        Note note = noteConverter.toEntity(noteDto);
        note.setId(id);

        return noteRepository.save(note);
    }

    public void deleteNote(long id) {
        noteRepository.deleteById(id);
    }
}
