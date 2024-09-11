package com.example.thawaq.Service;

import com.example.thawaq.Api.ApIException;
import com.example.thawaq.Model.Like;
import com.example.thawaq.Model.Menu;
import com.example.thawaq.Repository.LikeRepository;
import com.example.thawaq.Repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeService  {
    private final LikeRepository likeRepository;
    private final MenuRepository menuRepository;

    public void AddLike(Like like,Integer MenuId) {
        Menu menu = menuRepository.findMenuById(MenuId);
        if (menu==null){
            throw new ApIException("can not add menu");
        }
        like.setMenu(menu);
        likeRepository.save(like);
    }

    public void DeleteLike(Integer MenuId) {
        Like like = likeRepository.findLikeById(MenuId);
        if (like==null){
            throw new ApIException("can not delete like");
        }
        likeRepository.delete(like);

    }


}
