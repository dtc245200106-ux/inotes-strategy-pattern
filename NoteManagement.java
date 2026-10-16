package com.inotes.controller;

import com.inotes.model.Note;
import com.inotes.strategy.NoteDBStrategy;
import com.inotes.strategy.NoteFileStrategy;
import com.inotes.strategy.NoteStrategy;
import java.util.List;

public class NoteManagement {

    // Thuộc tính chiến lược - có thể thay đổi lúc runtime
    private NoteStrategy strategy;

    public NoteManagement() {
        // Mặc định dùng DB
        this.strategy = new NoteDBStrategy();
    }

    /**
     * Runtime Pluggable Storage - Chuyển đổi tầng lưu trữ
     */
    public void setStrategy(NoteStrategy strategy) {
        this.strategy = strategy;
    }

    public void useDatabase() {
        this.strategy = new NoteDBStrategy();
    }

    public void useFile() {
        this.strategy = new NoteFileStrategy();
    }

    // ============================================
    // NGHIỆP VỤ
    // ============================================

    public void addNote(String title, String content, int typeId) {
        Note note = new Note(title, content, typeId);
        strategy.save(note);
    }

    public void deleteNote(int id) {
        strategy.delete(id);
    }

    public List<Note> getAllNotes() {
        return strategy.findAll();
    }

    public List<Note> searchNotes(String keyword) {
        return strategy.search(keyword);
    }

    // ============================================
    // DEMO
    // ============================================
    public static void main(String[] args) {
        NoteManagement manager = new NoteManagement();

        System.out.println("=== SỬ DỤNG DATABASE ===");
        manager.useDatabase();
        manager.addNote("Họp nhóm dự án", "Thảo luận về thiết kế CSDL", 2);
        manager.getAllNotes().forEach(System.out::println);

        System.out.println("\n=== CHUYỂN SANG FILE ===");
        manager.useFile();
        manager.addNote("Mua sách lập trình", "Clean Code, Design Patterns", 3);
        manager.getAllNotes().forEach(System.out::println);

        System.out.println("\n=== TÌM KIẾM 'Design' ===");
        manager.searchNotes("Design").forEach(System.out::println);
    }
}