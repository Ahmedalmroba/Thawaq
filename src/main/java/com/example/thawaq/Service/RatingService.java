package com.example.thawaq.Service;

import com.example.thawaq.Api.ApiException;
import com.example.thawaq.Model.Expert;
import com.example.thawaq.Model.Rating;
import com.example.thawaq.Model.Store;
import com.example.thawaq.Repository.ExpertRepository;
import com.example.thawaq.Repository.RatingRepository;
import com.example.thawaq.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class RatingService {
    private final RatingRepository ratingRepository;
    private final ExpertRepository expertRepository;
    private final StoreRepository storeRepository;

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public void addRatingToStore(Rating rating) {

        ratingRepository.save(rating);}


    public void updateRating(Rating rating,Integer id) {
        Rating r = ratingRepository.findRatingById(id);
        if(r == null) {
            throw new ApiException("Rating not found");}
        r.setService(rating.getService());
        r.setCleaning(rating.getCleaning());
        r.setQuality(rating.getQuality());
        r.setCost(rating.getCost());
        r.setComment(rating.getComment());
        r.setTitle(rating.getTitle());
        r.setAverageRating(rating.getAverageRating());
        ratingRepository.save(r);}



    public void deleteRating(Integer id) {
        Rating r = ratingRepository.findRatingById(id);
        if(r == null) {
            throw new ApiException("Rating not found");}
        ratingRepository.delete(r);
    }
   ///v2

    public double CalculateAverageRatingExpert(Integer expertId) {
        Expert expert = expertRepository.findExpertById(expertId);
        if (expert == null) {
            throw new ApiException("Expert not found");
        }

        List<Rating> ratings = ratingRepository.findRatingByExpert(expert);
        if (ratings.isEmpty()) {
            throw new ApiException("No ratings found for the given expert");
        }


        double total = 0;
        for (Rating rating : ratings) {
            total += rating.getAverageRating();
        }

        return total / ratings.size();
    }
        ///v2
    public double CalculateAverageStore(Integer storeId) {
        Store store =storeRepository.findStoreById(storeId);
        if (store == null) {
            throw new ApiException("store not found");
        }

        List<Rating> ratings = ratingRepository.findRatingByStore(store);
        if (ratings.isEmpty()) {
            throw new ApiException("No ratings found for the given store");
        }


        double total = 0;
        for (Rating rating : ratings) {
            total += rating.getAverageRating();
        }

        return total / ratings.size();
    }
}



