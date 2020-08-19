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
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private UserService userService;


    @PostMapping("/credentials")
    public String addCredential(@ModelAttribute("Credential") Credential credential, Authentication authentication, Model model) {
        var user=new User();
        user=userService.getUser(authentication.getName());
        credential.setUserId(user.getUserid());

        if(credential.getCredentialId()==null ){

            this.credentialService.addCredential(credential);
            model.addAttribute("credentials",this.credentialService.getCredentials(user.getUserid()));
        } else  {

            this.credentialService.updateCredential(credential);
        }

        return "redirect:/";
    }

    @GetMapping("/deleteCredential/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Long credentialId) {
        credentialService.deleteCredential(credentialId);
        return "redirect:/";
    }

}
