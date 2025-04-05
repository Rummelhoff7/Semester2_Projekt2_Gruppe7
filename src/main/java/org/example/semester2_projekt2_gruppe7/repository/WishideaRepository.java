package org.example.semester2_projekt2_gruppe7.repository;

import org.example.semester2_projekt2_gruppe7.config.InitData;
import org.example.semester2_projekt2_gruppe7.model.Wishidea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class WishideaRepository {

    @Autowired
    InitData initData;

    public Wishidea getWishideabyID(int id){
        for (Wishidea wishidea: initData.getWishidealist()){
            if(wishidea.getId() == id){
                return wishidea;
            }

        }
        return null;
    }

    public void deleteWishidea(int id){
        Wishidea wishidea = getWishideabyID(id);
        initData.getWishidealist().remove(wishidea);
    }

    public void save(Wishidea wishidea){
        ArrayList<Wishidea> wishideaList = initData.getWishidealist();
        int newID;

        if(wishideaList.isEmpty()){
            newID = 1;
        } else {
            newID = wishideaList.getLast().getId() + 1;
        }

        wishidea.setId(newID);
        wishideaList.add(wishidea);
    }

    public void update(Wishidea updatedWishidea){
        ArrayList<Wishidea> wishideaList = initData.getWishidealist();
        for(int i = 0; i<wishideaList.size(); i++){
            if(wishideaList.get(i).getId() == updatedWishidea.getId()){
                wishideaList.set(i, updatedWishidea);
            }
        }
    }

}
