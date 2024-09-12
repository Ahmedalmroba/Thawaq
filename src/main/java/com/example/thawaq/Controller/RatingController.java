package com.example.thawaq.Controller;

import com.example.thawaq.Api.ApiResponse;
import com.example.thawaq.Model.Rating;
import com.example.thawaq.Service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @GetMapping("/get-all")
    public ResponseEntity getAllRatings() {
        return ResponseEntity.status(200).body(ratingService.getAllRatings());
    }

    @PostMapping("/add-rating")
    public ResponseEntity addRatingToStore(@Valid @RequestBody Rating rating) {
        ratingService.addRatingToStore(rating);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added rating to store!"));
    }

    @PutMapping("/update-rating/{id}")
    public ResponseEntity updateRatingToStore(@PathVariable Integer id, @Valid @RequestBody Rating rating) {
        ratingService.updateRating(rating, id);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated rating!"));
    }


    @DeleteMapping("/delete-rating/{id}")
    public ResponseEntity deleteRating(@PathVariable Integer id) {
        ratingService.deleteRating(id);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted rating!"));
    }

    ///v2
    @GetMapping("/average-rating-expert/{expertId}")
    public ResponseEntity getAverageRatingExpert(@PathVariable Integer expertId) {
         ratingService.CalculateAverageRatingExpert(expertId);
        return ResponseEntity.status(200).body(new ApiResponse("successfully average rating!"));
    }
    ///v2
    @GetMapping("/average-rating-store/{storeId}")
    public ResponseEntity getAverageRatingStore(@PathVariable Integer storeId) {
        ratingService.CalculateAverageStore(storeId);
        return ResponseEntity.status(200).body(new ApiResponse("successfully average rating!"));
    }


}
