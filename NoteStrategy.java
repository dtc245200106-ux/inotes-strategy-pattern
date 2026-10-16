package com.inotes.strategy;

import com.inotes.model.Note;
import java.util.List;

/**
 * Interface Strategy - Định nghĩa hợp đồng chung
 * cho mọi chiến lược lưu trữ (DB, File, v.v.)
 */
public interface NoteStrategy {
    void save(Note note);
    void delete(int id);
    List<Note> findAll();
    List<Note> search(String keyword);
}