package com.example.note.service;


import com.example.note.model.Note;
import com.example.note.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepo noteRepo;

    @Override
    public List<Note> getAllNotes() {
        List<Note> notes = noteRepo.findAll();
        Collections.reverse(notes);
        return notes;
    }

    @Override
    public Note save(Note note) {
        return noteRepo.save(note);
    }
}
