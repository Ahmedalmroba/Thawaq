package com.example.thawaq.Controller;

import com.example.thawaq.DTO.AddressDTO;
import com.example.thawaq.Service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;


    @GetMapping("/get")
    public ResponseEntity getAddresses()
    {
        return ResponseEntity.status(200).body(addressService.getAddresses());
    }
    @PostMapping("/add")
    public ResponseEntity addAddress(@Valid @RequestBody AddressDTO addressDTO)
    {
        addressService.addAddress(addressDTO);
        return ResponseEntity.status(200).body("address added");
    }
    @PutMapping("/update")
    public ResponseEntity updateAddress(@Valid@RequestBody AddressDTO addressDTO)
    {
        addressService.updateAddress(addressDTO);
        return ResponseEntity.status(200).body("address updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAddress(@PathVariable Integer id)
    {
        addressService.deleteAddress(id);
        return ResponseEntity.status(200).body("address deleted");
    }
}
