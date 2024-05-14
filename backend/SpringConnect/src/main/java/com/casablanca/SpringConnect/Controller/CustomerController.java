package com.casablanca.SpringConnect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casablanca.SpringConnect.Entity.User;
import com.casablanca.SpringConnect.Service.LoginFormService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


@RestController
public class CustomerController {
	public static final String hostOrigins = "http://localhost:8081";
	Integer iDCount = 0;
	@Autowired
	private LoginFormService loginService;
	
	@CrossOrigin(origins = hostOrigins)
	@GetMapping("/sayhello")
	public String sayHello() {
		return "Hello!";
	}
	
	//create
	@CrossOrigin(origins = hostOrigins)
	@PostMapping("/signup")
	public User createUser(@RequestBody ObjectNode objectNode) throws Exception {
		Gson gson = new Gson();
		String jsonString = objectNode.toString();
		User user = gson.fromJson(jsonString, User.class);
		user.setId(iDCount);
		iDCount += 1;
		System.out.print(user.getId());
		loginService.createUser(user);
		return user;
	}
	//Login
	@CrossOrigin(origins = hostOrigins)
	@RequestMapping(value = "/login", method = RequestMethod.POST,
	consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean loginChecker(@RequestBody ObjectNode objectNode) throws Exception{
		Gson gson = new Gson();
		String jsonString = objectNode.toString();
		User user = gson.fromJson(jsonString, User.class);
		User tempUser = loginService.findbyUserdetails_Name(user.getUsername());
        if(tempUser != null) {
        boolean flag = tempUser.getPassword().equals(user.getPassword());
		  if (flag) {
			  System.out.printf("Password correct!, %s, %s", tempUser.getPassword(), user.getPassword() );
			  return true;
		  }else {
			  System.out.printf("Password incorrect!, %s, %s", tempUser.getPassword(), user.getPassword() );
			  return false;
		}
      }return false;
	}
	
	@CrossOrigin(origins = hostOrigins)
	@GetMapping("/login")
	public String getLogin(){
		return "GET login";
	}
	
	@CrossOrigin(origins = hostOrigins)
	@GetMapping("/viewallusers")
	public List<User> getAll(){
		return loginService.getUsers();
	}
	@CrossOrigin(origins = hostOrigins)
	@PostMapping("/deleteuserID")
	public void deletebyID(@RequestBody ObjectNode objectNode){
		Gson gson = new Gson();
		String jsonString = objectNode.toString();
		User user = gson.fromJson(jsonString, User.class);
		loginService.delete_ByID(user.getId());
	}
	@CrossOrigin(origins = hostOrigins)
	@PostMapping("/deleteallusers")
	public void deleteAllUser() {
		loginService.deleteAll();
	}
}
