package com.example.note.service;

import com.example.note.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {

    List<Note> getAllNotes();
    Note save(Note note);
}
