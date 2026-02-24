package com.rodrigoguzman.tt_supermarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigoguzman.tt_supermarket.model.Branch;
import com.rodrigoguzman.tt_supermarket.service.IBranchService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/branches")
public class BranchController {
    @Autowired
    IBranchService branchService;

    @GetMapping("")
    public ResponseEntity<List<Branch>> getAllBranches() {
        try {
            List<Branch> branches = branchService.getAllBranches();

            if (branches.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

            return ResponseEntity.status(HttpStatus.OK).body(branches);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
        try {
            Branch branch = branchService.getBranchById(id);

            if (branch == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.status(HttpStatus.OK).body(branch);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("")
    public ResponseEntity<String> createBranch(@RequestBody Branch branch) {
        try {
            branchService.createBranch(branch);
            return ResponseEntity.status(HttpStatus.CREATED).body("Branch created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<String> createBranchBulk(@RequestBody List<Branch> branches) {
        branches.forEach(branch -> {
            try {
                branchService.createBranch(branch);
            } catch (Exception e) {
                System.err.println("Error creating branch: " + e.getMessage());
            }
        });
        return ResponseEntity.status(HttpStatus.CREATED).body("Branch created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBranch(@PathVariable Long id, @RequestBody Branch branch) {
        System.out.println("Received request to update branch with id: " + id);
        try {
            branchService.updateBranch(id, branch);
            return ResponseEntity.status(HttpStatus.OK).body("Branch updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating branch: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBranch(@PathVariable Long id) {
        try {
            branchService.deleteBranch(id);
            return ResponseEntity.status(HttpStatus.OK).body("Branch deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting branch: " + e.getMessage());
        }
    }
}
