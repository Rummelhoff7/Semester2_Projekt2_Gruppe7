package org.example.semester2_projekt2_gruppe7.service;

import org.springframework.stereotype.Service;

@Service
public class WishService {

    public String getImage(String name, String description) {

        String img = null;
        if(name.equals("pikachu")){
            img = "pikachu.jpg";
        }
        if(description.contains("pokemon")){
            img = "pikachu.jpg";
        }
        if(description.contains("fodbold")){
            img = "chelsea.jpg";
        }
        if(description.contains("hus")){
            img = "sommerhus.jpg";
        }
        if(description.contains("bog")){
            img = "bog.jpg";
        }
        if(description.contains("ur")){
            img = "ur.jpg";
        }
        return img;
    }
}
