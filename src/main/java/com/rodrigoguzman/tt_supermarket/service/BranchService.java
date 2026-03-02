package com.rodrigoguzman.tt_supermarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigoguzman.tt_supermarket.dto.BranchDTO;
import com.rodrigoguzman.tt_supermarket.exception.ResourceNotFoundException;
import com.rodrigoguzman.tt_supermarket.mapper.Mapper;
import com.rodrigoguzman.tt_supermarket.model.Branch;
import com.rodrigoguzman.tt_supermarket.repository.IBranchRepository;

@Service
public class BranchService implements IBranchService {
    @Autowired
    private IBranchRepository branchRepository;

    @Override
    public List<BranchDTO> getAllBranches() {
        return branchRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public BranchDTO getBranchById(Long id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));

        return Mapper.toDTO(branch);
    }

    @Override
    public BranchDTO createBranch(Branch branch) {
        return Mapper.toDTO(branchRepository.save(branch));
    }

    @Override
    public BranchDTO updateBranch(Long id, Branch branch) {
        Branch existingBranch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));

        if (branch.getName() != null && !branch.getName().isEmpty()) {
            existingBranch.setName(branch.getName());
        }
        if (branch.getLatitude() != null) {
            existingBranch.setLatitude(branch.getLatitude());
        }
        if (branch.getLongitud() != null) {
            existingBranch.setLongitud(branch.getLongitud());
        }

        return Mapper.toDTO(branchRepository.save(existingBranch));
    }

    @Override
    public void deleteBranch(Long id) {
        branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));

        branchRepository.deleteById(id);
    }
}