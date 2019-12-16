package com.example.easynotes.converter;

import com.example.easynotes.dto.NoteDto;
import com.example.easynotes.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteConverter {

    public List<NoteDto> toDtos(List<Note> notes) {
        return notes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Note toEntity(NoteDto dto) {
        if (dto == null)
            return null;

        return Note.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public NoteDto toDto(Note entity) {
        if (entity == null) {
            return null;
        }

        return NoteDto.builder()
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();
    }
}
