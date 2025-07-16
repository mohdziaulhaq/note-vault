package com.notevault.services.impl;

import com.notevault.models.Note;
import com.notevault.repositories.NoteRepository;
import com.notevault.services.AuditLogService;
import com.notevault.services.NoteService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private AuditLogService auditLogService;

    @Override
    public Note createNoteForUser(String username, String title, String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setOwnerUsername(username);
        Note savedNote = noteRepository.save(note);
        auditLogService.logNoteCreation(username, savedNote);
        return savedNote;
    }

    @Override
    public Note updateNoteForUser(Long noteId, String username, String title, String content) {
        Note note = noteRepository.findById(noteId).orElseThrow(()->
                new RuntimeException("Note not found"));
        note.setTitle(title);
        note.setContent(content);
        auditLogService.logNoteUpdate(username, note);
        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        Note note = noteRepository.findById(noteId).orElseThrow(()-> new RuntimeException("Note not found"));

        auditLogService.logNoteDeletion(username, noteId);
        noteRepository.deleteById(noteId);
    }

    @Override
    public List<Note> getAllNotesForUser(String username) {

        List<Note> notes = noteRepository.findByOwnerUsername(username);
        return notes;
    }
}
