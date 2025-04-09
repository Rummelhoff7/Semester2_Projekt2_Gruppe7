package org.example.semester2_projekt2_gruppe7.controller;

import org.example.semester2_projekt2_gruppe7.model.WishList;
import org.example.semester2_projekt2_gruppe7.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class PageController {


    @Autowired
    WishListRepository wishListRepo;

    @GetMapping("/wishlist")
    public String wishlistPage() {
        return "wishlist";
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        ArrayList<WishList> wishlisting = new ArrayList<>();
        wishlisting = wishListRepo.getAllWishList();
        model.addAttribute("wishlisting", wishlisting);

        return "wishlist";

    }
}
