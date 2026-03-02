package com.rodrigoguzman.tt_supermarket.service;

import java.util.List;

import com.rodrigoguzman.tt_supermarket.dto.BranchDTO;
import com.rodrigoguzman.tt_supermarket.model.Branch;

public interface IBranchService {
    List<BranchDTO> getAllBranches();

    BranchDTO getBranchById(Long id);

    BranchDTO createBranch(Branch branch);

    BranchDTO updateBranch(Long id, Branch branch);

    void deleteBranch(Long id);
}