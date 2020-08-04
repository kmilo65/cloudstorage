package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.data.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.dataServices.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class NoteController {

    @Autowired
    private NoteService noteService;

    //public NoteController(NoteService noteService) {this.noteService = noteService; }

    @GetMapping("/notes")
    public String getHomeNotes(Model model) {
        var notes= noteService.getNotes();
        model.addAttribute("notes",notes );
        return "home";
    }

    @PostMapping("/notes")
    public String addNote(@ModelAttribute("Note")  Note note, Model model) {
        note.setUserid(Long.valueOf(2));
        this.noteService.addNote(note);
        model.addAttribute("notes",this.noteService.getNotes());
        return "redirect:/home/notes";
    }

    @GetMapping("/delete/{noteid}")
    public String deleteNote(@PathVariable("noteid") Long noteid) {
        //Note note = noteService.getNoteById(noteid);
        //        .orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + noteid));
        noteService.deleteNote(noteid);
        return "home";
    }

  //  @GetMapping("/edit/{id}")
  //  public String showUpdateForm(@PathVariable("noteid") Integer noteid, Model model) {
  //      Note note = noteService.getNoteById(noteid);
  //      model.addAttribute("note", note);
  //      return "home/note";
  //  }




}
