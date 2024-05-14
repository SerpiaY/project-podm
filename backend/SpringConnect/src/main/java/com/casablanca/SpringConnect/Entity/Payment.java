package com.casablanca.SpringConnect.Entity;
 
import java.util.Date;

import jakarta.persistence.*;
@Entity
@Table(name = "payment", schema = "myschema")
public class Payment {
	@Id
    private int payment_id;
	@Column(name = "paymentamount")
    private float payment_amount;
	@Column(name = "paymentdate")
    private Date payment_date;
	@Column(name = "rentid")
	private int rentid;

    public Payment() {
    	this.setRentID(rentid);
        this.setPayment_amount(payment_amount);
        this.setPayment_date(payment_date);
    }
    
    public int getRentID() {
        return rentid;
    }

    public void setRentID(int rent_id) {
        this.rentid = rent_id;
    }
    public int getPaymentID() {
        return payment_id;
    }

    public void setPaymentID(int payment_id) {
        this.payment_id = payment_id;
    }

    public float getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(float payment_amount) {
        this.payment_amount = payment_amount;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }
}
