package org.example.semester2_projekt2_gruppe7.controller;

import org.example.semester2_projekt2_gruppe7.config.InitData;
import org.example.semester2_projekt2_gruppe7.model.Wishidea;
import org.example.semester2_projekt2_gruppe7.repository.WishideaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class PageController {

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

}

