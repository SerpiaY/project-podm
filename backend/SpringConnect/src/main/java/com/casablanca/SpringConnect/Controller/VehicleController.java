package com.casablanca.SpringConnect.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casablanca.SpringConnect.Entity.Branch;
import com.casablanca.SpringConnect.Entity.Vehicle;
import com.casablanca.SpringConnect.Service.BranchService;
import com.casablanca.SpringConnect.Service.VehicleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

@RestController
public class VehicleController {
	int iDCount = 3;
	@Autowired 
	BranchService branchService;
	
    @Autowired
    private VehicleService vehicle_srv;

    @CrossOrigin(origins = "http://localhost:8081") 
    @GetMapping("/view-vehicles")
    public List<Vehicle> getVehicles() {
        return vehicle_srv.getVehicles();
    }
    
    @CrossOrigin(origins = "http://localhost:8081") 
    @GetMapping("/view-avail1")
    public List<Vehicle> availableVehicle1() {
        List<Vehicle> branch1 = vehicle_srv.getAvailbyBranch(1, true);
        return branch1;
    }
    @CrossOrigin(origins = "http://localhost:8081") 
    @GetMapping("/view-avail2")
    public List<Vehicle> availableVehicle2() {
        List<Vehicle> branch2 = vehicle_srv.getAvailbyBranch(2, true);
        return branch2;
    }
    @CrossOrigin(origins = "http://localhost:8081") 
    @GetMapping("/view-avail3")
    public List<Vehicle> availableVehicle3() {
        List<Vehicle> branch3 = vehicle_srv.getAvailbyBranch(3, true);
        return branch3;
    }
  //create
  	@CrossOrigin(origins = "http://localhost:8081")
  	@PostMapping("/create-car")
  	public Vehicle createVehicle(@RequestBody ObjectNode objectNode) throws Exception {
  		Gson gson = new Gson();
  		String jsonString = objectNode.toString();
  		Vehicle vehicle = gson.fromJson(jsonString, Vehicle.class);
  		vehicle.setVehicle_id(iDCount);
  		iDCount += 1;
  		vehicle.setIs_available(true);
  		Optional<Branch> OpBranch = branchService.findbyID(vehicle.getBranch_id());
  		Branch tempBranch = OpBranch.get();
  		tempBranch.setAvailableCar(tempBranch.getAvailableCar() + 1);
  		System.out.print(vehicle.getVehicle_id());
  		vehicle_srv.createVehicle(vehicle);
  		return vehicle;
  	}
}