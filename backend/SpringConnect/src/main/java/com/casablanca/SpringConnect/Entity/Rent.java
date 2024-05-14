package com.casablanca.SpringConnect.Entity;


import java.util.Date;
import jakarta.persistence.*;



//@NamedStoredProcedureQuery(name = "rentbill", procedureName = "rent_vehicle", parameters = {
//		  @StoredProcedureParameter(mode = ParameterMode.IN, name = "vehicleID", type = Integer.class),
//		  @StoredProcedureParameter(mode = ParameterMode.IN, name = "customerID", type = Integer.class),
//		  @StoredProcedureParameter(mode = ParameterMode.IN, name = "duration", type = Integer.class),
//		  @StoredProcedureParameter(mode = ParameterMode.IN, name = "rentdate", type = Date.class),})
@Entity
@Table(name = "rentbill", schema = "myschema")
public class Rent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentid;
	@Column(name = "vehicle_id")
    private int vehicleid;
	@Column(name = "tripduration")
    private int tripduration;
	@Column(name = "customerid")
    private int customerid;
	@Column(name = "isreturned")
	private boolean isreturned;
	@Column(name = "daterented")
	private Date daterented;
	@Column(name = "datereturned")
	private Date datereturned;

    public Rent() {
        this.setCustomer_id(customerid);
        this.setDate_rented(daterented);
        this.setDate_returned(datereturned);
        this.setRent_id(rentid);
        this.setReturned(isreturned);
        this.setTrip_duration(tripduration);
        this.setVehicle_id(vehicleid);
    }

	public int getRent_id() {
        return rentid;
    }

    public void setRent_id(int rent_id) {
        this.rentid = rent_id;
    }

    public int getVehicle_id() {
        return vehicleid;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicleid = vehicle_id;
    }

    public int getTrip_duration() {
        return tripduration;
    }

    public void setTrip_duration(int trip_duration) {
        this.tripduration = trip_duration;
    }


    public int getCustomer_id() {
        return customerid;
    }

    public void setCustomer_id(int customer_id) {
        this.customerid = customer_id;
    }

    public boolean Is_returned() {
        return isreturned;
    }

    public void setReturned(boolean returned) {
        isreturned = returned;
    }

    public Date getDate_rented() {
        return daterented;
    }

    public void setDate_rented(Date date_rented) {
        this.daterented = date_rented;
    }

    public Date getDate_returned() {
        return datereturned;
    }

    public void setDate_returned(Date date_returned) {
        this.datereturned = date_returned;
    }

}
