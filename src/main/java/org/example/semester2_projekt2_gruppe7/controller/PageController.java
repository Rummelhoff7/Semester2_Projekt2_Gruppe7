package org.example.semester2_projekt2_gruppe7.controller;

import org.example.semester2_projekt2_gruppe7.model.User;
import org.example.semester2_projekt2_gruppe7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;


@Controller
public class PageController {

    @Autowired
    UserRepository userRepository;


    // Retunér forsiden, hvis nogen prøver at komme ind på localhost
    @GetMapping("/")
    public String mainPage(Model model) {
        ArrayList<User> userList = userRepository.getAllUsers();
        model.addAttribute("userList", userList);

        return "index";
    }


    @GetMapping("/userPage")
    public String userPage(Model model) {
        ArrayList<User> userList = userRepository.getAllUsers();
        model.addAttribute("userList", userList);
        return "userPage";  // This corresponds to userPage.html
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }




}