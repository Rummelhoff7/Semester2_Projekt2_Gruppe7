package org.example.semester2_projekt2_gruppe7.controller;

import org.example.semester2_projekt2_gruppe7.model.Wish;
import org.example.semester2_projekt2_gruppe7.repository.WishRepository;
import org.example.semester2_projekt2_gruppe7.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WishController {

    @Autowired
    WishRepository wishRepo;

    @Autowired
    WishService wishService;

    @GetMapping("/getCreateWish")
    public String createWish(){
        return "createWish";

        // user_id skal ændres
    }
    @PostMapping("/saveCreateWish")
    public String postCreateWish(
                                 @RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("price")double price) {

        String img = wishService.getImage(name, description);

        Wish wish = new Wish(name, description, img, price);
        wishRepo.save(wish);
        return "wishPage";
    }

    // OBS Skal kigges på
    @GetMapping("/getUpdateWish")
    public String updateWish(@RequestParam("id") int id, Model model){
        Wish wish = wishRepo.getWishById(id);
       model.addAttribute(wish);
        return "updateWish";
    }

    @PostMapping("/saveUpdateWish")
    public String postUpdateWish(@RequestParam("id") int id,
                                 @RequestParam("wishlist_id")int wishlist_id,
                                 @RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam ("Image") String img,
                                 @RequestParam("price") double price)
    {

        String image = wishService.getImage(name, description);
        Wish wish = new Wish(id, wishlist_id, name, description, img, price);
        wishRepo.update(wish);
        return "wishPage";
    }

    @GetMapping("/showWish")
    public String showWish(@RequestParam("id") int id, Model model){

        Wish wish = wishRepo.getWishById(id);
        model.addAttribute(wish);

        return "showWish";
    }

    @PostMapping("/deleteWish")
    public String deleteWish(@RequestParam("id") int id){

        wishRepo.deleteWish(id);

        return "wishPage";
    }
}
