package com.smart.Controllers;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.DAO.UserRepository;
import com.smart.Entities.User;

//This is the controller class that will be mapped with /user/** handler .
@Controller
@RequestMapping("/user")
public class UserControllers {
	
	@Autowired
	UserRepository userRepo;  //injecting UserReposiory by Autowiring
	
	//Principal object will get the authenticated username from the login page 
	//ModelAttribute make sure that this method is executed before request made goes to any handler method
	@ModelAttribute
	public void addUserToModel(Model model,Principal principal)
	{
	User user =userRepo.findByEmail(principal.getName());  //findByEmail() returns User object based on username(email in this case)
	System.out.println(user); //printing value in console for testing purpose
	model.addAttribute("user",user); //setting up the Model object with the user attribute to pass in the next page
	}
	
	
	@GetMapping("/user-dash")  //mapping below 'UserDashBorad()' method with the handler "/user-dash"
	public String UserDashBorad()
	{
		
		return "/Normal/user-dashboard";   //return user-dashborad.html from User folder of templates
	}

	@GetMapping("/addContact")  //mapping below 'addContact()' method with the endpoint "/addContact"
	public String addContact(Model model,Principal principal)
	{
		
		return "/Normal/addContact"; //this handler method will return addContact.html page
	}
	
	
	
	
}
