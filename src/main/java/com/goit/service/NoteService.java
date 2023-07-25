package com.goit.service;

import com.goit.model.Note;
import com.goit.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public Optional<Note> getNoteByUuid(String uuid) {
        return noteRepository.findByUuid(uuid);
    }

    public Note createNote(String name, String content, Note.AccessType accessType) {
        Note note = new Note();
        note.setUuid(UUID.randomUUID().toString());
        note.setName(name);
        note.setContent(content);
        note.setAccessType(accessType);
        return noteRepository.save(note);
    }

    public Optional<Note> updateNote(Long id, String name, String content, Note.AccessType accessType) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setName(name);
            note.setContent(content);
            note.setAccessType(accessType);
            return Optional.of(noteRepository.save(note));
        }
        return Optional.empty();
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    // You can add more methods depending on your requirements...
}