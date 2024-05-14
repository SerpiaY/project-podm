package com.casablanca.SpringConnect.Repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.casablanca.SpringConnect.Entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	Optional<Payment> findByRentid(int rentid);
}
