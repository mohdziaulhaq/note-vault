package com.notevault.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;
    private String username;
    private Long noteId;
    private String noteContent;
    private LocalDateTime timeStamp;

    public AuditLog() {
    }

    public Long getId() {
        return this.id;
    }

    public String getAction() {
        return this.action;
    }

    public String getUsername() {
        return this.username;
    }

    public Long getNoteId() {
        return this.noteId;
    }

    public String getNoteContent() {
        return this.noteContent;
    }

    public LocalDateTime getTimeStamp() {
        return this.timeStamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

}
