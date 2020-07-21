package com.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springsecurity.database.Database;
import com.springsecurity.model.User;
import com.springsecurity.repository.UserRepository;

@Controller
public class MainController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Database database;
	
	@RequestMapping("/")
	public String showHome() {
		return "home";
	}
	
	@RequestMapping("/show-report")
	public String showReport() {
		return "report";
	}
	
	@RequestMapping("/show-users")
	public String showUsers() {
		return "users";
	}
	
	@RequestMapping("/sign-up")
	public String showSignUp(Model model) {
		//use sign up page to insert value into DB
		
		//manually adding values for testing
		User user = new User();
		user.setName("Harry");
		user.setUsername("harry33");
		user.setPassword(passwordEncoder.encode("1234"));
		user.setRole("USER");
		//database.insertUser(user);
		//inserting using hibernate
		User userdb = userRepository.save(user);
		System.out.println(userdb);
		model.addAttribute("user",user);
		return "home";
	}

}
