package com.rodrigoguzman.tt_supermarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigoguzman.tt_supermarket.exception.ResourceNotFoundException;
import com.rodrigoguzman.tt_supermarket.model.Branch;
import com.rodrigoguzman.tt_supermarket.repository.IBranchRepository;

@Service
public class BranchService implements IBranchService {
    @Autowired
    private IBranchRepository branchRepository;

    @Override
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    @Override
    public Branch getBranchById(Long id) {
        return branchRepository.findById(id).orElse(null);
    }

    @Override
    public void createBranch(Branch branch) {
        branchRepository.save(branch);
    }

    @Override
    public void updateBranch(Long id, Branch branch) {
        System.out.println("serching for branch with id: " + id);
        Branch existingBranch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));

        System.out.println("existing branch: " + existingBranch);
        if (branch.getName() != null && !branch.getName().isEmpty()) {
            existingBranch.setName(branch.getName());
        }
        if (branch.getLatitude() != null) {
            existingBranch.setLatitude(branch.getLatitude());
        }
        if (branch.getLongitud() != null) {
            existingBranch.setLongitud(branch.getLongitud());
        }

        branchRepository.save(existingBranch);
    }

    @Override
    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }
}