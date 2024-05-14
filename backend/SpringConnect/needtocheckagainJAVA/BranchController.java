package com.casablanca.SpringConnect.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.casablanca.SpringConnect.Entity.Branch;
import com.casablanca.SpringConnect.Entity.Vehicle;
import com.casablanca.SpringConnect.Service.BranchService;

@RestController
public class BranchController {

    @Autowired
    private BranchService branch_serv;

    @CrossOrigin(origins = "http://localhost:8081") // Adjust origin if needed
    @GetMapping("/branches")
    public List<Branch> getBranches() {
        return branch_serv.getBranches();
    }

    @CrossOrigin(origins = "http://localhost:8081") // Adjust origin if needed
    @GetMapping("/branches/{branchId}")
    public Optional<Branch> getBranchById(@PathVariable int branchId) {
        return branch_serv.findbyID(branchId);
    }

    @CrossOrigin(origins = "http://localhost:8081") // Adjust origin if needed
    @GetMapping("/branches/search/{address}")
    public Branch getBranchesByAddress(@PathVariable String address) {
        return branch_serv.findbyBranchAddress(address);
    }

    @CrossOrigin(origins = "http://localhost:8081") // Adjust origin if needed
    @GetMapping("/branches/search/{address}/vehicles")
    public List<Vehicle> getAvailableVehiclesByBranchAddress(@PathVariable String address) {
        return branch_serv.getAvailableCar(address); // Assuming you want to keep the original method name
    }
}
