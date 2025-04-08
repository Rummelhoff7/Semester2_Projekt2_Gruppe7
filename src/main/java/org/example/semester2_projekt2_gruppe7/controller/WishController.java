package org.example.semester2_projekt2_gruppe7.controller;

import org.example.semester2_projekt2_gruppe7.model.Wish;
import org.example.semester2_projekt2_gruppe7.repository.WishRepository;
import org.example.semester2_projekt2_gruppe7.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class WishController {

    @Autowired
    WishRepository wishRepo;

    @Autowired
    WishService wishService;

    @GetMapping("/getCreateWish")
    public String createWish(){
        return "createWish";

    }
    @PostMapping("/saveCreateWish")
    public String postCreateWish(@RequestParam("id") int id,
                                 @RequestParam("user id")int user_id,
                                 @RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam ("Image") String img,
                                 @RequestParam("price")double price) {
        String image = wishService.getImage(name, description);

        Wish wish = new Wish(id, user_id, name, description, img, price);
        wishRepo.save(wish);
        return "redirect:/";
    }

    @GetMapping("/getUpdateWish")
    public String updateWish(@RequestParam("id") int id,int user_id, double price){
        Wish wish = wishRepo.getWishById(id);
       // model.addAttribute(wish);
        return "updateWish";
    }

    @PostMapping("/saveUpdateWish")
    public String postUpdateWish(@RequestParam("id") int id,
                                 @RequestParam("user id")int user_id,
                                 @RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam ("Image") String img,
                                 @RequestParam("price") double price)
    {

        String image = wishService.getImage(name, description);
        Wish wish = new Wish(id, user_id, name, description, img, price);
        wishRepo.update(wish);
        return "redirect:/";
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

        return "redirect:/";
    }
}
