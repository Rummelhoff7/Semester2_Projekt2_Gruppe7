package org.example.semester2_projekt2_gruppe7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

}

