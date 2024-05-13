package com.casablanca.SpringConnect.Controller;
import java.util.Date; 
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casablanca.SpringConnect.Entity.Branch;
import com.casablanca.SpringConnect.Entity.Payment;
import com.casablanca.SpringConnect.Entity.Rent;
import com.casablanca.SpringConnect.Entity.Vehicle;
import com.casablanca.SpringConnect.Service.BranchService;
import com.casablanca.SpringConnect.Service.PaymentService;
import com.casablanca.SpringConnect.Service.RentService;
import com.casablanca.SpringConnect.Service.VehicleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

@RestController
public class RentController {
	int iDCount = 0;
	public static final String hostOrigins = "http://localhost:8081";
	@Autowired
	private RentService rentService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired 
	private BranchService branchService;
	@PostMapping("/create")
	public void createRent(@RequestBody ObjectNode objectNode) {
		Gson gson = new Gson();
		String jsonString = objectNode.toString();
		Rent rent = gson.fromJson(jsonString, Rent.class);
		
		rent.setRent_id(iDCount);
		
		System.out.print(rent.getRent_id());
		rent.setDate_returned(DateUtils.addDays(rent.getDate_rented(), rent.getTrip_duration()));
		Optional<Vehicle> rentedVehicleOP = vehicleService.findByID(rent.getVehicle_id());
		Vehicle vehicle = rentedVehicleOP.get();
		Optional<Branch> tempBranchOP = branchService.findbyID(vehicle.getBranch_id());
		Branch tempBranch = tempBranchOP.get();
		tempBranch.setAvailableCar(tempBranch.getAvailableCar()-1);
		rentService.createRent(rent);
		Payment temp_pay = new Payment();
		temp_pay.setPaymentID(iDCount+1);
		temp_pay.setRentID(rent.getRent_id());
		temp_pay.setPayment_amount(rent.getTrip_duration()*vehicle.getCost_per_day());
		temp_pay.setPayment_date(rent.getDate_returned());
		paymentService.createPayment(temp_pay);
		System.out.print(rent.toString());
		iDCount += 1;
	}
	
	@CrossOrigin(origins = hostOrigins)
	@GetMapping("/viewall")
	public List<Rent> viewAllRent() {
		List <Rent> listRent = rentService.getAllRents();
		return listRent;
	}
	@PostMapping("/editrent")
	public void editRent(@RequestBody ObjectNode objectNode) throws Exception {
		Gson gson = new Gson();
		String jsonString = objectNode.toString();
		Rent rent = gson.fromJson(jsonString, Rent.class);
		if(rent != null) {
		  rentService.update(rent.getRent_id(), rent);
		 }else {
			 throw new IllegalArgumentException();
		 }
	}
	@PostMapping("/deletebyID")
	public void deleteById(@RequestBody ObjectNode objectNode) {
		Gson gson = new Gson();
		String jsonString = objectNode.toString();
		Rent rent = gson.fromJson(jsonString, Rent.class);
		Optional<Payment> payment = paymentService.findByRent(rent.getRent_id());
		Payment temp_payment = payment.get();
		paymentService.deleteByID(temp_payment.getPaymentID());
		rentService.deleteRent(rent.getRent_id());
	}
	@CrossOrigin(origins = hostOrigins)
	@PostMapping("/delete-all-rent")
	public void deleteAllRents() {
		rentService.deleteAll();
		paymentService.deleteAll();
	}
}
