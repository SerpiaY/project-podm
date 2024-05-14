package com.casablanca.SpringConnect.Service;
import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casablanca.SpringConnect.Entity.Branch;
import com.casablanca.SpringConnect.Entity.Vehicle;
import com.casablanca.SpringConnect.Repository.BranchRepository;
import com.casablanca.SpringConnect.Repository.VehicleRepository;
//import org.springframework.web.bind.annotation.CrossOrigin;
@Service
public class BranchService {
    @Autowired
    private BranchRepository branch_repo;

    public Optional<Branch> findbyID(Integer ID) {
    	return branch_repo.findById(ID);
    }
    public void deleteAllBranch() {
    	branch_repo.deleteAll();
    }
    public void deleteById(Integer Id) {
    	branch_repo.deleteById(Id);
    }
}
