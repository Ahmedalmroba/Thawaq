package com.example.thawaq.Service;

import com.example.thawaq.Api.ApiException;
import com.example.thawaq.Model.Expert;
import com.example.thawaq.Model.Rating;
import com.example.thawaq.Model.Store;
import com.example.thawaq.Model.User;
import com.example.thawaq.Repository.ExpertRepository;
import com.example.thawaq.Repository.RatingRepository;
import com.example.thawaq.Repository.StoreRepository;
import com.example.thawaq.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class RatingService {
    private final RatingRepository ratingRepository;
    private final ExpertRepository expertRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    //Add rating from the user to the store
    public void addRatingFromUserToStore(Rating rating,Integer userId, Integer storeId) {
        User u = userRepository.findUserById(userId);
        Store s = storeRepository.findStoreById(storeId);
        if(u==null) {
            throw new ApiException("User not found");}
        if(s==null) {
            throw new ApiException("Store not found");}
        rating.setStore(s);
        rating.setClient(rating.getClient());
        userRepository.save(u);
        ratingRepository.save(rating);}

    //Add rating from expert to the store
    public void addRatingFromExpertToStore(Rating rating,Integer expertId, Integer storeId) {
        Expert e =expertRepository.findExpertById(expertId);
        Store s = storeRepository.findStoreById(storeId);
        if(e==null) {
            throw new ApiException("User not found");}
        if(s==null) {
            throw new ApiException("Store not found");}
        rating.setStore(s);
        rating.setExpert(e);
        expertRepository.save(e);
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
    ///v2
    public double CalculateAverageQualityStore(Integer storeId) {
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
            total += rating.getQuality();
        }

        return total / ratings.size();
    }
    ////v3
    public double CalculateAverageCostStore(Integer storeId) {
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
            total += rating.getCost();
        }
        return total / ratings.size();


}}
