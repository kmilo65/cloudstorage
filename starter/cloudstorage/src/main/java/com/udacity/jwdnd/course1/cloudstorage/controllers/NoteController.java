package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.data.Note;
import com.udacity.jwdnd.course1.cloudstorage.data.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.services.dataServices.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class NoteController {


    protected static final String SUCCESS_NOTE = "redirect:/result?note";
    protected static final String   REDIRECT_DELETE = "redirect:/result?delete";
    protected static final String REDIRECT_HOME="redirect:/";


    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;


    @PostMapping("/notes")
    public String addUpdateNote(@ModelAttribute("Note")  Note note, Authentication authentication, Model model) {

        String username = authentication.getName();
        Long userId = userService.getUser(username).getUserid();
        note.setUserId(userId);

        if(note.getNoteId()==null || note.getNoteId()==0) {
            this.noteService.addNote(note);
            model.addAttribute("notes", this.noteService.getNotes(userId));

        } else{

            this.noteService.updateNote(note);
        }
            return  SUCCESS_NOTE;
    }

    @GetMapping("/deleteNote/{noteId}")
    public String deleteNote(@PathVariable("noteId") Long noteId) {
        noteService.deleteNote(noteId);
        return REDIRECT_DELETE;
    }

     @GetMapping("/editNote/{noteId}")
        public String showUpdateForm(Note note,  Model model) {
           note=noteService.getNote(note);
           model.addAttribute("note",note );

         return REDIRECT_HOME; /**"redirect:/";**/
    }




}
