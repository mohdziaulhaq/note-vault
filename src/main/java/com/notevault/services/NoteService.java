package com.notevault.services;

import com.notevault.models.Note;
import java.util.List;

public interface NoteService {
    Note createNoteForUser(String username, String title, String content);
    Note updateNoteForUser(Long noteId, String username, String title, String content);
    void deleteNoteForUser(Long noteId, String username);
    List<Note> getAllNotesForUser(String username);

}
