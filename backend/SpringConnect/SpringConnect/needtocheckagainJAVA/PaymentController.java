package com.casablanca.SpringConnect.Controller;

import com.casablanca.SpringConnect.Entity.Payment;
import com.casablanca.SpringConnect.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getPayments() {
        List<Payment> payments = paymentService.getPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @GetMapping("/{rentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable int rentId) {
        Optional<Payment> payment = paymentService.findById(rentId);
        return payment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Payment> savePayment(@RequestBody Payment payment) {
        Payment savedPayment = paymentService.update(payment);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    @PutMapping("/{rentId}")
    public ResponseEntity<Payment> updatePayment(@PathVariable int rentId, @RequestBody Payment updatedPayment) {
        Optional<Payment> existingPayment = paymentService.getPaymentById(rentId);
        if (existingPayment.isPresent()) {
            Payment payment = existingPayment.get();
            payment.setPayment_amount(updatedPayment.getPayment_amount());
            payment.setPayment_date(updatedPayment.getPayment_date());
            return new ResponseEntity<>(paymentService.savePayment(payment), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{rentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable int rentId) {
        Optional<Payment> payment = paymentService.getPaymentById(rentId);
        if (payment.isPresent()) {
            paymentService.deletePayment(rentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}