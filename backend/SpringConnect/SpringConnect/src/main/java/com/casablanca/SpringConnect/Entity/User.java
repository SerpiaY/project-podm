package com.casablanca.SpringConnect.Entity;



import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "customer", schema = "myschema")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "date")
	private Date dateBirth;
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "licensenumber")
	private int licenseNumber;
	
	public User() {
		this.setId(id);
		this.setUsername(username);
		this.setPassword(password);
		this.setFirst_name(firstName);
		this.setLast_name(lastName);
		this.setDate(dateBirth);
		this.setLicenseNumber(licenseNumber);
	}
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setLicenseNumber(int licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public int getLicenseNumber() {
		return licenseNumber;
	}
	@Id
	public Integer getId() {
	    return id;
	}
	@Id
	public void setId(Integer ID) {
	    this.id = ID;
	}
	public Date getDate() {
		return dateBirth;
	}
	public void setDate(Date date) {
		this.dateBirth = date;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_name() {
		return firstName;
	}
	public void setFirst_name(String first_name) {
		this.firstName = first_name;
	}
	public String getLast_name() {
		return lastName;
	}
	public void setLast_name(String last_name) {
		this.lastName = last_name;
	}
}