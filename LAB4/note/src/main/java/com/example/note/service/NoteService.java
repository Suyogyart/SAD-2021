package com.example.note.service;

import com.example.note.model.Note;

import java.util.List;

public interface NoteService {

    List<Note> getAllNotes();
    Note save(Note note);
}
