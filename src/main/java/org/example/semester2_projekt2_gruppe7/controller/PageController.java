package org.example.semester2_projekt2_gruppe7.controller;

import org.example.semester2_projekt2_gruppe7.config.InitData;
import org.example.semester2_projekt2_gruppe7.model.Wishidea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class PageController {

    @Autowired
    InitData initData;

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

    @GetMapping("/wishidea")
    public String wishideaPage(Model model) {
        ArrayList<Wishidea> wishidealist = new ArrayList<>();

        wishidealist.addAll(initData.getWishidealist());

        model.addAttribute("wishidealist", wishidealist);


        return "wishidea";  // This returns the wishidea.html template
    }


}

