package com.sporthub.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sporthub.backend.model.Role;
import com.sporthub.backend.model.User;
import com.sporthub.backend.repository.RoleRepository;
import com.sporthub.backend.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepo;

	//@Autowired
	//private RoleRepository roleRepo;


	@GetMapping("/all")
	public @ResponseBody Iterable<User> getUsers(){
		return userRepo.findAll();
	}

	@PostMapping("/add")
	public @ResponseBody String addUser(
			@RequestParam String first_name,
			@RequestParam String last_name,
			@RequestParam String email,
			@RequestParam String password,
			@RequestParam String phone_number,
			@RequestParam String city,
			@RequestParam String address,
			@RequestParam String zip_code,
			@RequestParam String document_id,
			@RequestParam Integer role_id
	) {
		Optional<Role> role = roleRepo.findById(role_id);
		if(role.isPresent()) {
			User user = new User();
			user.setFirst_name(first_name);
			user.setLast_name(last_name);
			user.setEmail(email);
			user.setPassword(password);
			user.setPhone_number(phone_number);
			user.setCity(city);
			user.setAddress(address);
			user.setZip_code(zip_code);
			user.setDocument_id(document_id);
			user.setRole(role.get());
			userRepo.save(user);
			return "user added";
		}
		return "not added";

	}
}
