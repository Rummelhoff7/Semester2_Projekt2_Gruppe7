package org.example.semester2_projekt2_gruppe7.controller;

import jakarta.servlet.http.HttpServletRequest;
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
        System.out.println("GET - wishlist_id: " + wishlistId);// add it to the model
        return "createwishidea";
    }

    @PostMapping("/saveCreateWishidea")
    public String postCreateWishidea(@RequestParam ("wishlist_id")int wishlist_id,
                                     @RequestParam ("title") String title,
                                     @RequestParam ("description") String description) {

        //Here you can add java code to example add img from Video 2 16min
        System.out.println("POST - wishlist_id: " + wishlist_id);

        Wishidea wishidea = new Wishidea(wishlist_id, title, description);

        wishideaRepository.save(wishidea);
        return "redirect:/showwishidea?wishlist_id=" + wishlist_id;

    }

    @GetMapping("/getUpdateWishidea")
    public String updateWishidea(@RequestParam("id") int id, Model model) {
        Wishidea wishidea = wishideaRepository.getWishideabyID(id);
        model.addAttribute("wishidea", wishidea);
        return "updatewishidea";
    }



    @PostMapping("/saveUpdateWishidea")
    public String postUpdateWishidea(@RequestParam("id") int id,
                                     @RequestParam("title") String title,
                                     @RequestParam("description") String description,
                                     @RequestParam("wishlist_id") int wishlist_id) {

        // Process the request (e.g., save or update the wish idea)
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

        return "redirect:/showwishidea?wishlist_id=" + wishlist_id;
    }

}
