package org.example.semester2_projekt2_gruppe7.controller;

import org.example.semester2_projekt2_gruppe7.model.Wishidea;
import org.example.semester2_projekt2_gruppe7.repository.WishideaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WishideaController {

    @Autowired
    WishideaRepository wishideaRepository;

    @GetMapping("/getCreateWishidea")
    public String CreateWishidea() {
        return "createwishidea";
    }

    @PostMapping("/saveCreateWishidea")
    public String postCreateWishidea(@RequestParam ("wishlist_id")int wishlist_id,
                                     @RequestParam ("user_id") int user_id,
                                     @RequestParam ("title") String title,
                                     @RequestParam ("description") String description) {

        //Here you can add java code to example add img from Video 2 16min

        Wishidea wishidea = new Wishidea(wishlist_id, user_id, title, description);

        wishideaRepository.save(wishidea);
        return "redirect:/wishidea";

    }

    @GetMapping("/getUpdateWishidea")
    public String updateWishidea(@RequestParam("id") int id, Model model) {
        Wishidea wishidea = wishideaRepository.getWishideabyID(id);
        model.addAttribute(wishidea);
        return "updatewishidea";
    }

    @PostMapping("/saveUpdateWishidea")
    public String postupdateWishidea(@RequestParam ("id") int id,
                                     @RequestParam ("wishlist_id")int wishlist_id,
                                     @RequestParam ("user_id") int user_id,
                                     @RequestParam ("title") String title,
                                     @RequestParam ("description") String description){
        //Here you can add java code to example add img from Video 2 16min

        Wishidea wishidea = new Wishidea(id, wishlist_id, user_id, title, description);
        wishideaRepository.update(wishidea);
        return "redirect:/wishidea";
    }

    @GetMapping("/showwishidea")
    public String showWishidea(@RequestParam ("id") int id, Model model) {

        Wishidea wishidea = wishideaRepository.getWishideabyID(id);
        model.addAttribute(wishidea);

        return "showwishidea";
    }

    @PostMapping("/deleteWishidea")
    public String deleteWishidea(@RequestParam ("id") int id){

        wishideaRepository.deleteWishidea(id);

        return "redirect:/wishidea";
    }

}
