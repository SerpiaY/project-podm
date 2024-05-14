package com.casablanca.SpringConnect.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casablanca.SpringConnect.Entity.Branch;
import com.casablanca.SpringConnect.Entity.Payment;
import com.casablanca.SpringConnect.Entity.Rent;
import com.casablanca.SpringConnect.Entity.User;
import com.casablanca.SpringConnect.Entity.Vehicle;
import com.casablanca.SpringConnect.Service.BranchService;
import com.casablanca.SpringConnect.Service.PaymentService;
import com.casablanca.SpringConnect.Service.RentService;
import com.casablanca.SpringConnect.Service.VehicleService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

@RestController
public class PaymentController {
	public static final String hostOrigins = "http://localhost:8081";
	@Autowired
	private RentService rentService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired 
	private BranchService branchService;
	
	@CrossOrigin(origins = hostOrigins)
	@GetMapping("/view-payment")
	public List<Payment> getAllPayment(){
		return paymentService.getPayments();
	}
	
	@CrossOrigin(origins = hostOrigins)
	@PostMapping("/pay")
	public void getPayment(@RequestBody ObjectNode objectNode){
		Gson gson = new Gson();
		String jsonString = objectNode.toString();
		Payment payment = gson.fromJson(jsonString, Payment.class);
		Optional<Payment> temp_payment = paymentService.findById(payment.getPaymentID());
		Payment exist_bill = temp_payment.get();
		Optional<Rent> lrent = rentService.getRentById(exist_bill.getRentID());
		Rent rent = lrent.get();
		Optional<Vehicle> rentedVehicleOP = vehicleService.findByID(rent.getVehicle_id());
		Vehicle vehicle = rentedVehicleOP.get();
		vehicle.setIs_available(true);
		Optional<Branch> tempBranchOP = branchService.findbyID(vehicle.getBranch_id());
		Branch tempBranch = tempBranchOP.get();
		tempBranch.setAvailableCar(tempBranch.getAvailableCar()+1);
		paymentService.deleteByID(exist_bill.getPaymentID());
		
	}
	
}
