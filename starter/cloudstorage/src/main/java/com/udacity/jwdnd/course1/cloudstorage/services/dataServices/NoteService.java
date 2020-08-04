package com.udacity.jwdnd.course1.cloudstorage.services.dataServices;

import com.udacity.jwdnd.course1.cloudstorage.data.Note;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteMapper noteMapper;

    //public NoteService(NoteMapper noteMapper) { this.noteMapper = noteMapper; }


    public void addNote(Note note){
        this.noteMapper.insertNote(note);
    }

    public Note getNoteById(Long noteid){
        return this.noteMapper.getNote(noteid);
    }

    public List<Note> getNotes(){
        return this.noteMapper.getAllNotes();
    }

    public void deleteNote(Long noteid){ this.noteMapper.deleteNote(noteid); }

}
