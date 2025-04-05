package org.example.semester2_projekt2_gruppe7.config;

import org.example.semester2_projekt2_gruppe7.model.Wishidea;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InitData {

    private ArrayList<Wishidea> wishidealist = new ArrayList<>();

    public InitData() {
        wishidealist.add(new Wishidea(1,1,1,"lol","lol"));
        wishidealist.add(new Wishidea(2,1,1,"hat","flot"));
    }

    public ArrayList<Wishidea> getWishidealist() {
        return wishidealist;
    }
}
