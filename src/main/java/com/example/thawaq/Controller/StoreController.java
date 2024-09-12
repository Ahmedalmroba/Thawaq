package com.example.thawaq.Controller;

import com.example.thawaq.Model.Store;
import com.example.thawaq.Service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/get")
    public ResponseEntity getStores()
    {
        return ResponseEntity.status(200).body(storeService.getStores());
    }
    @PostMapping("/add")
    public ResponseEntity addStore(@Valid@RequestBody Store store)
    {
        storeService.addStore(store);
        return ResponseEntity.status(200).body("Store added");/*new ApiResponse("Store added")*/
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateStore(@PathVariable Integer id,@Valid@RequestBody Store store)
    {
        storeService.updateStore(id, store);
        return ResponseEntity.status(200).body("Store updated");/*new ApiResponse("Store updated")*/
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStore(@PathVariable Integer id)
    {
        storeService.deleteStore(id);
        return ResponseEntity.status(200).body("Store deleted");/*new ApiResponse("Store deleted")*/
    }
}
