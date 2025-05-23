package org.example.semester2_projekt2_gruppe7.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.semester2_projekt2_gruppe7.model.User;
import org.example.semester2_projekt2_gruppe7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

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
                             @RequestParam ("password") String password,
                             Model model){

        User user = new User (name, password);
        boolean success = userRepository.saveUser(user);

        // Retunere false i saveUser(), dvs. at der allerede findes et brugernavn.
        if(!success){
            model.addAttribute("error", "Brugernavn findes allerede, skriv venligst en nyt brugernavn.");
            return "createUser";
        }
        return "redirect:/wishlist?user_ID=" + user.getId();
    }

    @GetMapping("/getUpdateUser")
    public String updateUser(@RequestParam("id") int id, Model model) {
        User user = userRepository.getUserByID(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("/saveUpdateUser")
    public String postUpdateUser (@RequestParam("id") int id,
                                  @RequestParam("name") String name,
                                  @RequestParam("password") String password){
        User user = new User (id, name, password);
        userRepository.updateUser(user);
        return "redirect:/wishlist";
    }

    @GetMapping("/showUser")
    public String showUser(@RequestParam("id") int id, Model model) {

        User user = userRepository.getUserByID(id);
        model.addAttribute("user", user);

        return "showUser";
    }

    // Hvis denne metode bliver udført, så bliver man sendt tilbage til forsiden
    @PostMapping("/deleteUser")
    public String deleteUser (@RequestParam("id") int id) {

        userRepository.deleteUser(id);

        return "redirect:/";
    }


    @GetMapping("/userPage")
    public String userPage(HttpServletRequest request, Model model) {
        ArrayList<User> userList = userRepository.getAllUsers();
        model.addAttribute("userList", userList);

        String referer = request.getHeader("Referer");
        model.addAttribute("refererUrl", referer);

        return "userPage";
    }


    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        int userID = userRepository.authenticateUser(username, password);
        if (userID != -1) {
            return "redirect:/wishlist?user_ID=" + userID;
        } else {

            return "redirect:/loginError";
        }
    }

    @GetMapping("/loginError")
    public String loginError() {
        return "loginError";
    }
    @PostMapping("/loginError")
    public String loginError(@RequestParam String username, @RequestParam String password) {
        int userID = userRepository.authenticateUser(username, password);
        if (userID != -1) {
            return "redirect:/wishlist?wishListID=" + userID;
        } else {
            return "redirect:/loginError";
        }
    }
}
