package com.inotes.strategy;

import com.inotes.model.Note;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NoteFileStrategy implements NoteStrategy {

    private static final String FILE_PATH = "notes.txt";
    private int autoId = 1;

    public NoteFileStrategy() {
        // Tìm ID lớn nhất để tránh trùng
        for (Note n : findAll()) {
            if (n.getId() >= autoId) autoId = n.getId() + 1;
        }
    }

    @Override
    public void save(Note note) {
        List<Note> list = findAll();
        if (note.getId() == 0) {
            note.setId(autoId++);
            list.add(note);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == note.getId()) {
                    list.set(i, note);
                    break;
                }
            }
        }
        writeToFile(list);
    }

    @Override
    public void delete(int id) {
        List<Note> list = findAll();
        list.removeIf(n -> n.getId() == id);
        writeToFile(list);
    }

    @Override
    public List<Note> findAll() {
        List<Note> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Note n = Note.fromFileLine(line);
                if (n != null) list.add(n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Note> search(String keyword) {
        List<Note> result = new ArrayList<>();
        String kw = keyword.toLowerCase();
        for (Note n : findAll()) {
            if (n.getTitle().toLowerCase().contains(kw)
                    || n.getContent().toLowerCase().contains(kw)) {
                result.add(n);
            }
        }
        return result;
    }

    private void writeToFile(List<Note> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Note n : list) {
                bw.write(n.toFileLine());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}