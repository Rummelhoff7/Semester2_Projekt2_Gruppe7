package org.example.semester2_projekt2_gruppe7.controller;

import org.example.semester2_projekt2_gruppe7.model.User;
import org.example.semester2_projekt2_gruppe7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/getCreatedUser")
    public String getCreatedUser(){
        return "createUser";
    }

    @PostMapping("createUser")
    public String createUser(@RequestParam ("name") String name,
                             @RequestParam ("password") String password){

        User user = new User (name, password);
        userRepository.saveUser(user); //video 2 20:00
        return "redirect:/userList";
    }

    @GetMapping("/getUpdateUser")
    public String updateUser(@RequestParam("id") int id, Model model) {
        User user = userRepository.getUserByID(id);
        model.addAttribute(user);
        return "updateUser";
    }

    @PostMapping("/saveUpdateUser")
    public String postUpdateUser (@RequestParam("id") int id,
                                  @RequestParam("name") String name,
                                  @RequestParam("password") String password){
        User user = new User (id, name, password);
        userRepository.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/showUser")
    public String showUser(@RequestParam("id") int id, Model model) {

        User user = userRepository.getUserByID(id);
        model.addAttribute(user);

        return "showUser";
    }

    // Hvis denne metode bliver udført, så bliver man sendt tilbage til forsiden
    @PostMapping("/deleteUser")
    public String deleteUser (@RequestParam("id") int id) {

        userRepository.deleteUser(id);

        return "redirect:/";
    }

}
