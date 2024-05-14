package com.casablanca.SpringConnect.Service;

import com.casablanca.SpringConnect.Entity.Payment;
import com.casablanca.SpringConnect.Repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    public PaymentRepository payment_repo;

    public Payment createPayment(Payment payment) {
		return payment_repo.save(payment);
	}

    public List<Payment> getPayments() {
        return payment_repo.findAll();
    }

    public Optional<Payment> findById(Integer payment_id) {
        return payment_repo.findById(payment_id);
    }
    public Optional<Payment> findByRent(Integer rent_id) {
    	return payment_repo.findByRentid(rent_id);
    }

    public Payment update(Integer rent_id, Payment paymentInformation) {
		Optional<Payment> payment = payment_repo.findById(rent_id);
		if (payment.isPresent()) {
			Payment exist_Payment = payment.get();
			exist_Payment.setRentID(paymentInformation.getRentID());
			exist_Payment.setPayment_amount(paymentInformation.getPayment_amount());
			exist_Payment.setPayment_date(paymentInformation.getPayment_date());
			return payment_repo.save(exist_Payment);
		}
	    return null;
	}

	public void deleteAll() {
		payment_repo.deleteAll();
	}

    public void deleteByID(Integer payment_id) {
        payment_repo.deleteById(payment_id);
    }
}