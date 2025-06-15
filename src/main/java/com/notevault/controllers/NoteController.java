package com.notevault.controllers;

import com.notevault.dto.NoteRequest;
import com.notevault.models.Note;
import com.notevault.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public Note createNote(@RequestBody NoteRequest noteRequest, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        System.out.println("USER DETAILS: "+username);
        return noteService.createNoteForUser(username, noteRequest.getTitle(),noteRequest.getContent());
    }

    @GetMapping
    public List<Note> getNotesForUser(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("ENTERED GET ENDPOINT");
        String username = userDetails.getUsername();
        System.out.println("USER DETAILS: "+username);
        List<Note> notes = noteService.getAllNotesForUser(username);
        return notes;
    }

    @PutMapping("/{noteId}")
    public Note updateNote (@PathVariable Long noteId, @RequestBody NoteRequest noteRequest, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return noteService.updateNoteForUser(noteId, username, noteRequest.getTitle(), noteRequest.getContent());
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote (@PathVariable Long noteId, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        noteService.deleteNoteForUser(noteId, username);
    }

}
