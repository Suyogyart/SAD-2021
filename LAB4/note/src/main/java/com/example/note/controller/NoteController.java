package com.example.note.controller;

import com.example.note.model.Note;
import com.example.note.service.NoteService;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;
    private final Parser parser = Parser.builder().build();
    private final HtmlRenderer renderer = HtmlRenderer.builder().build();

    private void getAllNotes(Model model) {
        List<Note> notes = noteService.getAllNotes();
        model.addAttribute("notes", notes);
    }

    private void saveNote(String description, Model model) {
        if (description != null && !description.trim().isEmpty()) {
            Note note = new Note();
            Node document = parser.parse(description.trim());
            String html = renderer.render(document);
            note.setDescription(html);

            noteService.save(note);

            // After publish you need to clean up the text area
            model.addAttribute("description", "");
        }
    }

}
