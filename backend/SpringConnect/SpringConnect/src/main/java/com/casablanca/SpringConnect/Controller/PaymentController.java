package com.casablanca.SpringConnect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casablanca.SpringConnect.Entity.Payment;
import com.casablanca.SpringConnect.Service.PaymentService;

@RestController
public class PaymentController {
	public static final String hostOrigins = "http://localhost:8081";
	@Autowired
	private PaymentService paymentService;
	
	@CrossOrigin(origins = hostOrigins)
	@GetMapping("/view-payment")
	public List<Payment> getAllPayment(){
		return paymentService.getPayments();
	}
	
}
