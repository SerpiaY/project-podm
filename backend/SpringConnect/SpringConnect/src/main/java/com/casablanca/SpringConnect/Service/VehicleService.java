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
public class VehicleService {
    @Autowired
    private VehicleRepository vehicle_repo;
    @Autowired
    private BranchRepository branch_repo;

    public List<Vehicle> getVehicles(){
    	List<Vehicle> vehicleList = vehicle_repo.findAll();
		for (Vehicle vehicle : vehicleList){
			Branch exist_branch = branch_repo.getById(vehicle.getBranch_id());
			vehicle.setBranchAddress(exist_branch.getAddress());
		}return vehicleList;
	}
    public void createVehicle(Vehicle vehicle) {
    	vehicle_repo.save(vehicle);
    }
	public Optional<Vehicle> findByID(Integer vehicle_id){
		return vehicle_repo.findById(vehicle_id);
	}
    public List<Vehicle> getAvailableCar(boolean availabilityFlag) {
        return vehicle_repo.findByIsavailable(availabilityFlag);
    }
    public List<Vehicle> getByBranch(Integer branchID){
    	return vehicle_repo.findByBranchid(branchID);
    }
    public List<Vehicle> getAvailbyBranch(Integer branchID, boolean flag){
    	Branch exist_branch = branch_repo.getById(branchID);
    	List<Vehicle> vehicleList = vehicle_repo.findByBranchidAndIsavailable(branchID, flag);
    	for (Vehicle vehicle : vehicleList){
			vehicle.setBranchAddress(exist_branch.getAddress());
        }return vehicleList;
    }
}