package org.example.semester2_projekt2_gruppe7.controller;

import org.example.semester2_projekt2_gruppe7.model.User;
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
    public String mainPage(@RequestParam("user_ID") int id, Model model) {
        User user = userRepository.getUserByID(id);
        model.addAttribute("user", user);

        ArrayList<WishList> wishListing = wishListRepo.getWishListbyUser_id(id);
        model.addAttribute("wishListing", wishListing);

        return "wishlist";
    }

    @GetMapping("/createWishList")
    public String showCreateWishPage(@RequestParam("user_id") int userId,
                                     @RequestParam(value = "referer", required = false) String referer,
                                     Model model) {
        model.addAttribute("refererUrl", referer);
        model.addAttribute("user_id", userId);
        return "createWishList";
    }


    @GetMapping("/getCreateWishList")
    public String createWishList(@RequestParam("user_id")int user_id) {
        return "redirect:/createWishList?user_id=" + user_id;
    }


    @PostMapping("/saveCreateWishList")
    public String postCreateWishList(
            @RequestParam("user_id") int user_id,
            @RequestParam("name") String name,
            @RequestParam("img") String img)
    {

        WishList wishList = new WishList(user_id, name, img);
        wishListRepo.save(wishList);
        return "redirect:/wishlist?user_ID=" + user_id;
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
        return "redirect:/wishlist?user_ID=" + user_id;

    }

    @GetMapping("/showFriends_WishListbyUser_id")
    public String showFriends_WishListbyUser_id(@RequestParam("id") int id, Model model){

        User user = userRepository.getUserByID(id);
        model.addAttribute("user", user);

        ArrayList<WishList> wishListing = wishListRepo.getWishListbyUser_id(id);
        model.addAttribute("wishListing", wishListing);

        return "friendsWishList";

    }

    @PostMapping("/deleteWishList")
    public String deleteWishList(@RequestParam("id") int id,
                                 @RequestParam("user_id") int user_id) {

        System.out.println("deleteWishList");
        System.out.println("user_id: " + user_id);
        System.out.println("id: " + id);

        wishListRepo.delete(id);

        return "redirect:/wishlist?user_ID=" + user_id;
    }

}
