package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.data.File;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.services.dataServices.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
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
    public String uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, File file,Authentication authentication, Model model)  {

       String fileUploadSuccess=null;
       String fileUploadError=null;

        Long userId = userService.getUser(authentication.getName()).getUserid();

        String fileName=fileUpload.getOriginalFilename();

        if(!fileService.fileExist(fileName,userId)){

            try{
                file.setUserid(userId);
                file.setFileName(fileName);
                file.setFiledata(fileUpload.getBytes());
                file.setContenttype(fileUpload.getContentType());
                file.setFilesize(fileUpload.getSize());

                this.fileService.insertFile(file);
                List<File> files = this.fileService.filesUpload(file.getUserid());
                model.addAttribute("files",files);
                model.addAttribute("fileUploadSuccess","File Succesfully Upload");
            }
            catch (Exception ex){
                model.addAttribute("fileUploadError","File not upload. Try Again");
            }

        } else {
        }

        return "result";
    }


    @GetMapping("/deleteFile/{fileId}")
    public String deleteFile(@PathVariable("fileId") Long fileId) {
        this.fileService.deleteFile(fileId);
        return "redirect:/";
    }

    @GetMapping("/download")

    public ResponseEntity download(@RequestParam String fileName,Authentication authentication){

        Long userId = userService.getUser(authentication.getName()).getUserid();
        List<File> files = fileService.filesUpload(userId);
        File newFile=new File();

        for ( int i=0; i<files.size();i++){
            if(files.get(i).getFileName().equals(fileName)){
               newFile=files.get(i);
            }
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + newFile.getFileName() + "\"")
                .body(newFile);
    }



}
