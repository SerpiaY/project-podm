package com.casablanca.SpringConnect.Service;

import com.casablanca.SpringConnect.Entity.Rent;
import com.casablanca.SpringConnect.Repository.RentRepository;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentService {
    @Autowired
    public RentRepository rent_repo;

    public void createRent(Rent rent) {
//        rent_repo.createRentbill(rent.getVehicle_id(), rent.getCustomer_id(), rent.getTrip_duration(), rent.getDate_rented());
    	rent_repo.save(rent);
    }

    public List<Rent> getAllRents() {
        return rent_repo.findAll();
    }

    public Optional<Rent> getRentById(int rent_id) {
        return rent_repo.findById(rent_id);
    }

    public void deleteAll() {
		rent_repo.deleteAll();
	}

    public void deleteRent(int rent_id) {
        rent_repo.deleteById(rent_id);
    }
    public Rent update(Integer ID, Rent rentInformation) {
		Optional<Rent> rent = rent_repo.findById(ID);
		if (rent.isPresent()) {
			Rent exist_Rent = rent.get();
			exist_Rent.setCustomer_id(rentInformation.getCustomer_id());
			exist_Rent.setDate_rented(rentInformation.getDate_rented());
			exist_Rent.setTrip_duration(rentInformation.getTrip_duration());
			exist_Rent.setDate_returned(DateUtils.addDays(exist_Rent.getDate_rented(), exist_Rent.getTrip_duration()));
			return rent_repo.save(exist_Rent);
		}
	    return null;
    }
}