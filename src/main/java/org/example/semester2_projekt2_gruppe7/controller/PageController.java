package org.example.semester2_projekt2_gruppe7.controller;

import org.example.semester2_projekt2_gruppe7.model.Wish;
import org.example.semester2_projekt2_gruppe7.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class PageController {


    @Autowired
    WishRepository wishRepository;

   @GetMapping("/")
    public String mainPage(Model model){
        ArrayList<Wish> wishes = new ArrayList<Wish>();
        wishes = wishRepository.getAllWish();
        model.addAttribute("wishes", wishes);

        return "index";
    }


}



