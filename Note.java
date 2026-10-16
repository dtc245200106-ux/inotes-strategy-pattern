package com.inotes.model;

public class Note {
    private int id;
    private String title;
    private String content;
    private int typeId;

    // Constructors
    public Note() {}

    public Note(String title, String content, int typeId) {
        this.title = title;
        this.content = content;
        this.typeId = typeId;
    }

    public Note(int id, String title, String content, int typeId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.typeId = typeId;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getTypeId() { return typeId; }
    public void setTypeId(int typeId) { this.typeId = typeId; }

    public String toFileLine() {
        return id + "|" + title + "|" + content + "|" + typeId;
    }

    public static Note fromFileLine(String line) {
        String[] parts = line.split("\\|", -1);
        if (parts.length < 4) return null;
        return new Note(
            Integer.parseInt(parts[0]),
            parts[1],
            parts[2],
            Integer.parseInt(parts[3])
        );
    }

    @Override
    public String toString() {
        return "Note{id=" + id + ", title='" + title + "', typeId=" + typeId + "}";
    }
}