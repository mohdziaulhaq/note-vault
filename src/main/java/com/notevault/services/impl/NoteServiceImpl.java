package com.notevault.services.impl;

import com.notevault.models.Note;
import com.notevault.repositories.NoteRepository;
import com.notevault.services.NoteService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note createNoteForUser(String username, String title, String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setOwnerUsername(username);
        return noteRepository.save(note);
    }

    @Override
    public Note updateNoteForUser(Long noteId, String username, String title, String content) {
        Note note = noteRepository.findById(noteId).orElseThrow(()->
                new RuntimeException("Note not found"));
        note.setTitle(title);
        note.setContent(content);
        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        noteRepository.deleteById(noteId);
    }

    @Override
    public List<Note> getAllNotesForUser(String username) {

        List<Note> notes = noteRepository.findByOwnerUsername(username);
        return notes;
    }
}
