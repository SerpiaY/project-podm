package com.casablanca.SpringConnect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casablanca.SpringConnect.Entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{
	public List<Vehicle> findByIsavailable(boolean aFlag);
	public List<Vehicle> findByBranchid(Integer branchID);
	public List<Vehicle> findByBranchidAndIsavailable(Integer branchID, boolean flag);
}
