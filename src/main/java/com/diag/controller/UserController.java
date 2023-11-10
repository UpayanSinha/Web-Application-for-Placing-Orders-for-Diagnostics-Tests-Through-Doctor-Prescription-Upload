package com.diag.controller;


import com.diag.dao.UserRepo;
import com.diag.entities.Form;
import com.diag.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    //methode for adding common data to responce
    @ModelAttribute
    public void addCommonData(Model model,Principal principal){
        String userName = principal.getName();
        System.out.println("USERNAME "+userName);

        //get the user using email
        User user = userRepo.getUserByUserEmail(userName);
        System.out.println("USER "+user);

        model.addAttribute("user", user);

    }

    //dashboard home
    @RequestMapping("/index")
    public String dashboard(Model model, Principal principal){

        model.addAttribute("title","User Dashboard");

        return "normal/user_dashboard";
    }

    //open add form handler

    @GetMapping("/add-form")
    public String openAddContactForm(Model model){

        model.addAttribute("title","Add Contact");
        model.addAttribute("form",new Form());
        return "normal/add_form";
        }

        //processing add details form

    @PostMapping("/process-form")
    public String processForm(@ModelAttribute Form form, @RequestParam("profileImage") MultipartFile file, Principal principal){
        try {
            String name = principal.getName();
            User user = this.userRepo.getUserByUserEmail(name);

            //processing and uploading file...
            if(file.isEmpty()){
                //if the file is empty the try this message
                System.out.println("File is empty");
            }else{
                //file the file to folder and update the name to form
                form.setImageUrl(file.getOriginalFilename());
                File saveFile=new ClassPathResource("static/img").getFile();
               Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
                Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Image is uploaded");
            }

            user.getForms().add(form);
            this.userRepo.save(user);

            System.out.println("DATA " + form);

            System.out.println("Added to data base");
        }catch (Exception ex){
            System.out.println("ERROR "+ex.getMessage());
            ex.printStackTrace();
        }

        return "normal/add_form";

    }


}
