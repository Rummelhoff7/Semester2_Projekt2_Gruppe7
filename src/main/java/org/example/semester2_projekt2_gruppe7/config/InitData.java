package org.example.semester2_projekt2_gruppe7.config;

import org.example.semester2_projekt2_gruppe7.model.Wish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InitData {


    private ArrayList<Wish> wishes = new ArrayList<>();

    public InitData(){
        wishes.add(new Wish(1, 11,"Pikachu","En gul pokemonbamse", "pikachu.jpg" ));
        wishes.add(new Wish(2, 12,"Chelsea","En fed fodboldtrøje", "chelsea.jpg" ));
        wishes.add(new Wish(3, 13,"Sommerhus","Et lækkert sommerhus", "sommerhus.jpg" ));
        wishes.add(new Wish(4, 14,"Bog","En spændende bog", "bog.jpg" ));
        wishes.add(new Wish(5, 15,"Ur","En dyrt ur", "ur" ));





    }

   // public ArrayList<Wish> getWishes() {return wishes;}
}
