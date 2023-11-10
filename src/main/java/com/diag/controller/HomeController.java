package com.diag.controller;


import com.diag.dao.UserRepo;
import com.diag.entities.User;
import com.diag.helper.Message;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private UserRepo userRepo;


        @RequestMapping("/")
                public String home(Model model)
        {
            model.addAttribute("title","Home - We have all our labs ready for Diagnostic tests");
            return "home";
        }

    @RequestMapping("/about")
    public String about(Model model)
    {
        model.addAttribute("title","about - insert name");
        return "about";
    }

    @RequestMapping("/signup")
    public String signup(Model model)
    {
        model.addAttribute("title","Register - Diagnostics Test Account");
        model.addAttribute("user",new User());
        return "signup";
    }

    //handler for registering user

    @RequestMapping(value = "/do_register",method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user")User user, @RequestParam(value = "agreement",defaultValue = "false")boolean agreement, Model model, HttpSession session){

            try {
                user.setRole("ROLE_USER");
                user.setEnabled(true);
                user.setPassword(passwordEncoder.encode(user.getPassword()));

                System.out.println("Agreement "+ agreement);
                System.out.println("User "+user);
                User result=this.userRepo.save(user);
                model.addAttribute("user",new User());
                session.setAttribute("message", new Message("Successfully Registered !!","alert-success"));
                return "signup";

            }catch (Exception ex){
                ex.printStackTrace();
                model.addAttribute("user",user);
                session.setAttribute("message", new Message("Someting went Wrong"+ ex.getMessage(),"alert-danger"));
                return "signup";
            }

    }

    //handler for custom login

    @GetMapping("/signin")
    public String customLogin(Model model){
            model.addAttribute("title","Login Page");
            return"login";
    }





}
