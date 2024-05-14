package com.casablanca.SpringConnect.Service;
import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casablanca.SpringConnect.Entity.User;
import com.casablanca.SpringConnect.Repository.LoginRepository;
//import org.springframework.web.bind.annotation.CrossOrigin;

@Service
public class LoginFormService {
	@Autowired
	public LoginRepository login_repo;
	
	//@CrossOrigin(origins = "http://localhost:8081")
	public User createUser(User user) {
		return login_repo.save(user);
	}
	public List<User> getUsers(){
		return login_repo.findAll();
	}
	public Optional<User> findByID(Integer ID){
		return login_repo.findById(ID);
	}
	public User findbyUserdetails_Name(String username){
		List<User> user_list = login_repo.findByUsername(username);
		for (User user : user_list) {
			if(user != null) {
				return user;
			}
		}return null;
	}
	public User update(Integer ID, User userInformation) {
		Optional<User> user = login_repo.findById(ID);
		if (user.isPresent()) {
			User exist_User = user.get();
			exist_User.setFirst_name(userInformation.getFirst_name());
			exist_User.setUsername(userInformation.getUsername());
			exist_User.setPassword(userInformation.getPassword());
			exist_User.setLast_name(userInformation.getLast_name());
			exist_User.setDate(userInformation.getDate());
			return login_repo.save(exist_User);
		}
	    return null;
	}
	public void deleteAll() {
		login_repo.deleteAll();
	}
	public void delete_ByID(Integer ID) {
		login_repo.deleteById(ID);
	}
}