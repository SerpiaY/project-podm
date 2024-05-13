package com.casablanca.SpringConnect.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "branch", schema = "myschema")
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int branchid;
	@Column(name = "availablecar")
    private int availablecar;
	@Column(name = "address")
    private String address;

    public Branch() {
        this.setAddress(address);
        this.setBranch_id(branchid);
        this.setAvailableCar(availablecar);
    }

    public int getBranch_id() {
        return branchid;
    }

    public void setBranch_id(int branch_id) {
        this.branchid = branch_id;
    }

    public int getAvailableCar() {
        return availablecar;
    }

    public void setAvailableCar(int availablecar) {
        this.availablecar = availablecar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
