package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.data.File;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.services.dataServices.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping
public class FileUploadController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;


    //file uploads is handled in controller as multipartFile object;
    @PostMapping("/fileUpload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication authentication, Model model) throws IOException {

        String username = authentication.getName();
        Long userId = userService.getUser(username).getUserid();
        String fileName=fileUpload.getOriginalFilename();
        File file=new File();
        file.setFileName(fileName);
        file.setFiledata(fileUpload.getBytes());
        file.setContenttype(fileUpload.getContentType());
        file.setFilesize(fileUpload.getSize());
        this.fileService.insertFile(file);
        List<File> files = this.fileService.filesUpload(userId);
        model.addAttribute("files",files);

        return "redirect:/";
    }


    @GetMapping("/deleteFile/{fileId}")
    public String deleteFile(@PathVariable("fileId") Long fileId) {
        fileService.deleteFile(fileId);
        return "redirect:/";
    }

}
