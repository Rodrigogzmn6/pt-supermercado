package com.rodrigoguzman.tt_supermarket.service;

import java.util.List;

import com.rodrigoguzman.tt_supermarket.model.Branch;

public interface IBranchService {
    List<Branch> getAllBranches();

    Branch getBranchById(Long id);

    void createBranch(Branch branch);

    void updateBranch(Long id, Branch branch);

    void deleteBranch(Long id);
}