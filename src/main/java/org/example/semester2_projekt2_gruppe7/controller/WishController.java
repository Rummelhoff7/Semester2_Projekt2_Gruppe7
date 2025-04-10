package org.example.semester2_projekt2_gruppe7.controller;

import org.example.semester2_projekt2_gruppe7.model.User;
import org.example.semester2_projekt2_gruppe7.model.Wish;
import org.example.semester2_projekt2_gruppe7.repository.WishRepository;
import org.example.semester2_projekt2_gruppe7.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class WishController {

    @Autowired
    WishRepository wishRepo;

    @Autowired
    WishService wishService;


    @GetMapping("/wishPage")
    public String userPage(Model model) {
        ArrayList<Wish> wishes = wishRepo.getAllWish();
        model.addAttribute("wishes", wishes);
        return "wishPage";  // This corresponds to userPage.html
    }

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
        return "redirect:/wishPage";
    }

    // OBS Skal kigges på
    @GetMapping("/getUpdateWish")
    public String updateWish(@RequestParam("id") int id, Model model){
        Wish wish = wishRepo.getWishById(id);
       model.addAttribute("wish", wish);
        return "updateWish";
    }

    @PostMapping("/saveUpdateWish")
    public String postUpdateWish(@RequestParam("id") int id,
                                 @RequestParam("wishlist_id")int wishlist_id,
                                 @RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam ("img") String img,
                                 @RequestParam("price") double price)
    {

        if (img == null || img.isEmpty()) {
            img = wishRepo.getWishById(id).getImg();

        }
        Wish wish = new Wish(id, wishlist_id, name, description, img, price);
        wishRepo.update(wish);
        return "redirect:/wishPage";
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

        return "redirect:/wishPage";
    }


}
