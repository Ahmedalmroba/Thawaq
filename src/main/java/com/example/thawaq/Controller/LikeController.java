package com.example.thawaq.Controller;

import com.example.thawaq.Model.Like;
import com.example.thawaq.Service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/like")
@AllArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/add/{MenuId}")
    public ResponseEntity addLike(@RequestBody Like like,@PathVariable Integer MenuId) {
        likeService.AddLike(like,MenuId);
        return ResponseEntity.status(200).body("add like successfully");
    }






    @DeleteMapping("/delete/{MenuId}")
    public ResponseEntity deleteLike(@PathVariable Integer MenuId) {
        likeService.DeleteLike(MenuId);
        return ResponseEntity.status(200).body("delete like successfully");
    }
}
