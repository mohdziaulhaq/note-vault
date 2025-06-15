package com.notevault.models;

import jakarta.persistence.*;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    private String title;

    private String ownerUsername;

    public Note() {
    }

    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public String getTitle() {
        return this.title;
    }

    public String getOwnerUsername() {
        return this.ownerUsername;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Note)) return false;
        final Note other = (Note) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$ownerUsername = this.getOwnerUsername();
        final Object other$ownerUsername = other.getOwnerUsername();
        if (this$ownerUsername == null ? other$ownerUsername != null : !this$ownerUsername.equals(other$ownerUsername))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Note;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $ownerUsername = this.getOwnerUsername();
        result = result * PRIME + ($ownerUsername == null ? 43 : $ownerUsername.hashCode());
        return result;
    }

    public String toString() {
        return "Note(id=" + this.getId() + ", content=" + this.getContent() + ", title=" + this.getTitle() + ", ownerUsername=" + this.getOwnerUsername() + ")";
    }
}
