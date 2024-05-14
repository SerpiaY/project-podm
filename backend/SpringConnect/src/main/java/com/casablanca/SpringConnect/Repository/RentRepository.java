package com.casablanca.SpringConnect.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.casablanca.SpringConnect.Entity.Rent;


public interface RentRepository extends JpaRepository<Rent, Integer>{
//	@Modifying
//	@Query(nativeQuery = true, value = "call myschema.rentbill.rent_vehicle(:vehicleID, :customerID, :duration, :rentdate)")
//	void createRentbill(@Param("vehicleID") int vehicleID,@Param("customerID") int customerID,@Param("duration") int duration, @Param("rentdate") Date rentdate);
//	
}

