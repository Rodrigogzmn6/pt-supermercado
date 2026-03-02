package com.rodrigoguzman.tt_supermarket.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigoguzman.tt_supermarket.dto.BranchDTO;
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

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@RequestBody Branch branch) {
        return ResponseEntity.created(null).body(branchService.createBranch(branch));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<BranchDTO>> createBranchBulk(@RequestBody List<Branch> branches) {
        List<BranchDTO> createdBranches = new ArrayList<BranchDTO>();

        branches.forEach(branch -> {
            createdBranches.add(branchService.createBranch(branch));
        });

        return ResponseEntity.created(null).body(createdBranches);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody Branch branch) {
        return ResponseEntity.created(null).body(branchService.updateBranch(id, branch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);

        return ResponseEntity.noContent().build();
    }
}
