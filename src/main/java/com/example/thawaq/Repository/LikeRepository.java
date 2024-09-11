package com.example.thawaq.Repository;

import com.example.thawaq.Model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
    Like findLikeById(Integer id);
}
