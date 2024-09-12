package com.example.thawaq.Controller;

import com.example.thawaq.Model.Branch;
import com.example.thawaq.Service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/branch")
public class BranchController {

    private final BranchService branchService;

    @GetMapping("/get")
    public ResponseEntity getBranches()
    {
        return ResponseEntity.status(200).body(branchService.getBranches());
    }
    @PostMapping("/add/{storeID}")
    public ResponseEntity addBranch(@PathVariable Integer storeID, @Valid@RequestBody Branch branch)
    {
        branchService.addBranch(storeID, branch);
        return ResponseEntity.status(200).body("Branch added");/*new ApiResponse("Branch added")*/
    }
    @PutMapping("/update/{branchID}")
    public ResponseEntity updateBranch(@PathVariable Integer branchID,@Valid@RequestBody Branch branch)
    {
        branchService.updateBranch(branchID, branch);
        return ResponseEntity.status(200).body("Branch updated");/*new ApiResponse("Branch updated")*/
    }
    @DeleteMapping("/delete/{branchID}")
    public ResponseEntity deleteBranch(@PathVariable Integer branchID)
    {
        branchService.deleteBranch(branchID);
        return ResponseEntity.status(200).body("Branch deleted");/*new ApiResponse("Branch deleted")*/
    }
}
