package org.example.semester2_projekt2_gruppe7.controller;

import org.example.semester2_projekt2_gruppe7.model.Wishidea;
import org.example.semester2_projekt2_gruppe7.repository.WishideaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class WishideaController {

    @Autowired
    WishideaRepository wishideaRepository;

    @GetMapping("/wishidea")
    public String wishideaPage(Model model) {
        ArrayList<Wishidea> wishidealist;
        wishidealist = wishideaRepository.getAllWishidea();
        model.addAttribute("wishidealist", wishidealist);


        return "wishidea";  // This returns the wishidea.html template
    }

    @GetMapping("/getcreatewishidea")
    public String CreateWishidea(@RequestParam("wishlist_id") int wishlistId, Model model) {
        model.addAttribute("wishlist_id", wishlistId);
        return "createwishidea";
    }

    @PostMapping("/saveCreateWishidea")
    public String postCreateWishidea(@RequestParam ("wishlist_id")int wishlist_id,
                                     @RequestParam ("title") String title,
                                     @RequestParam ("description") String description)
    {


        Wishidea wishidea = new Wishidea(wishlist_id, title, description);

        wishideaRepository.save(wishidea);
        return "redirect:/showFriendsWishbyWishList_id?id=" + wishlist_id;

    }


    @PostMapping("/saveUpdateWishidea")
    public String postUpdateWishidea(@RequestParam("id") int id,
                                     @RequestParam("wishlist_id") int wishlist_id,
                                     @RequestParam("title") String title,
                                     @RequestParam("description") String description
                                     ) {

        Wishidea wishidea = new Wishidea(id, title, description);
        wishideaRepository.update(wishidea);

        return "redirect:/showwishidea?wishlist_id=" + wishlist_id;
    }



    @GetMapping("/showwishidea")
    public String showWishidea(@RequestParam ("wishlist_id") int wishlist_id, Model model) {

        ArrayList<Wishidea> wishidealist = wishideaRepository.getWishideaby_wishlist_id(wishlist_id);
        model.addAttribute("wishidealist", wishidealist);

        return "showwishidea";
    }

    @PostMapping("/deleteWishidea")
    public String deleteWishidea(@RequestParam("id") int id,
                                 @RequestParam("wishlist_id") int wishlist_id){

        wishideaRepository.deleteWishidea(id);

        return "redirect:/wishPage?WishList_id=" + wishlist_id;
    }

}
