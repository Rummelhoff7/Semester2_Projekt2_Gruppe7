package org.example.semester2_projekt2_gruppe7.controller;

import org.example.semester2_projekt2_gruppe7.model.User;
import org.example.semester2_projekt2_gruppe7.model.Wish;
import org.example.semester2_projekt2_gruppe7.model.WishList;
import org.example.semester2_projekt2_gruppe7.repository.UserRepository;
import org.example.semester2_projekt2_gruppe7.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class WishListController {

    @Autowired
    WishListRepository wishListRepo;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/wishlist")
    public String mainPage(Model model) {
        ArrayList<WishList> wishlisting = wishListRepo.getAllWishList();
        model.addAttribute("wishlisting", wishlisting);

        return "wishlist";

    }

    @GetMapping("/getCreateWishList")
    public String createWishList() {
        return "createWishList";
    }


    @PostMapping("/saveCreateWishList")
    public String postCreateWishList(
            @RequestParam("user_id") int user_id,
            @RequestParam("name") String name,
            @RequestParam("img") String img)
    {

       //String img = Service.getImg();

        WishList wishList = new WishList(user_id, name, img);
        wishListRepo.save(wishList);
        return "redirect:/wishlist";
    }


    @GetMapping("/getUpdateWishList")
    public String updateWishList(@RequestParam("id") int id, Model model) {
        WishList wishList = wishListRepo.getWishListById(id);
        model.addAttribute(wishList);
        return "updateWishList";
    }


    @PostMapping("/saveUpdateWishList")
    public String postUpdateWishList(
            @RequestParam("id") int id,
            @RequestParam("user_id") int user_id,
            @RequestParam("name") String name,
            @RequestParam("img") String img){

      //  String img = WishlistService.getImg(brand, colour);
       WishList wishList = new WishList(id, user_id, name, img);
        wishListRepo.update(wishList);
        return "redirect:/wishlist";

    }
    @GetMapping("/showWishList")
    public String showWishList(@RequestParam("id") int id, Model model) {
        WishList wishlist = wishListRepo.getWishListById(id);
        model.addAttribute(wishlist);

        return "showWishList";
    }

    @GetMapping("/showWishListbyUser_id")
    public String showWishListbyUser_id(@RequestParam("id") int id, Model model){

        User user = userRepository.getUserByID(id);
        model.addAttribute("user", user);

        ArrayList<WishList> wishListing = wishListRepo.getWishListbyUser_id(id);
        model.addAttribute("wishListing", wishListing);

        return "friendsWishList";

    }

    @PostMapping("/deleteWishList")
    public String deleteWishList(@RequestParam("id") int id) {

        wishListRepo.delete(id);

        return "redirect:/wishlist";
    }

}
