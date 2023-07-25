package com.goit.service;

import com.goit.model.Note;
import com.goit.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class NoteShareController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/note/share/{noteId}")
    public String viewSharedNote(@PathVariable Long noteId, Model model) {
        Optional<Note> optionalNote = noteService.getNoteById(noteId);

        if (!optionalNote.isPresent() || optionalNote.get().getAccessType() != Note.AccessType.PUBLIC) {
            return "errorPage"; 
        }

        model.addAttribute("note", optionalNote.get());
        return "sharedNoteView";
    }
}
