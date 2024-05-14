package com.casablanca.SpringConnect.Entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle", schema = "myschema")
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleId;
	@Column(name = "brand")
    private String brand;
	@Column(name = "datebought")
    private Date datebought;
	@Column(name = "branchid")
    private int branchid;
	@Column(name = "isavailable")
    private boolean isavailable;
	@Column(name = "costperday")
    private float costperday;
	@Transient
	private String branchAddress;

    public Vehicle() {
    	this.setIs_available(isavailable);
    	this.setBranch_id(branchid);
    	this.setBrand(brand);
    	this.setCost_per_day(costperday);
    	this.setDate_bought(datebought);
    	this.setVehicle_id(vehicleId);
    }

    public int getVehicle_id() {
        return vehicleId;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicleId = vehicle_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getDate_bought() {
        return datebought;
    }

    public void setDate_bought(Date date_bought) {
        this.datebought = date_bought;
    }

    public int getBranch_id() {
        return branchid;
    }

    public void setBranch_id(int branch_id) {
        this.branchid = branch_id;
    }

    public boolean getIs_available() {
        return isavailable;
    }

    public void setIs_available(boolean is_available) {
        this.isavailable = is_available;
    }

	public float getCost_per_day() {
		return costperday;
	}

	public void setCost_per_day(float cost_per_day) {
		this.costperday = cost_per_day;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
}
