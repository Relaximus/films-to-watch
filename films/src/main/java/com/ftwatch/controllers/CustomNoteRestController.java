package com.ftwatch.controllers;

import com.ftwatch.model.Film;
import com.ftwatch.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomNoteRestController {
    @Autowired
    NoteService noteService;

    @GetMapping(value="/notes/{noteId}/searchFilms")
    public ResponseEntity<List<Film>> searchFilms(@PathVariable("noteId") Long noteId) {
        return ResponseEntity.ok(noteService.findFilmsForNoteAndSave(noteId));
    }
}
