package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.data.Credential;
import com.udacity.jwdnd.course1.cloudstorage.data.Note;
import com.udacity.jwdnd.course1.cloudstorage.data.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.services.dataServices.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.dataServices.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private UserService userService;


    //public NoteController(NoteService noteService) {this.noteService = noteService; }

    @GetMapping("/")
    public String getHomeCredentials(Model model) {
        var credentials=credentialService.getCredentials();
        model.addAttribute("credentials", credentials );
        return "home";
    }


    @PostMapping("/credential")
    public String addCredential(@ModelAttribute("Credential") Credential credential, Authentication authentication, Model model) {
        var user=new User();
        user=userService.getUser(authentication.getName());
        credential.setUserId(user.getUserid());
        this.credentialService.addCredential(credential);
        credential.setUsername("");
        model.addAttribute("credentials",this.credentialService.getCredentials());
        return "redirect:/home";
    }

}
