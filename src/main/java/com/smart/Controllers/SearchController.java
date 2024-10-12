package com.smart.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.DAO.ContactRepository;
import com.smart.DAO.UserRepository;
import com.smart.Entities.Contact;
import com.smart.Entities.User;

@Controller
public class SearchController {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	ContactRepository contactRepo;
	
	
	//This method will return the  view  in JSON format instead of page 
	//This handler method takes input as Keyword from the search field
	//it returns list of contacts based on keyword typed by user
	@GetMapping("/searchContact/{keyword}")
	@ResponseBody
	public ResponseEntity<?> searchContact(Principal principal, @PathVariable("keyword") String keyword)
	{
		String email=principal.getName(); //email of the logged-in user
		User user=userRepo.findByEmail(email); //return user object from database based on email address of the user
		List<Contact> contacts= contactRepo.findByNameContainingAndUser(keyword, user); //return list of contact of a user based on keyword
		return ResponseEntity.ok(contacts); //list of contact  is returned as a ResponseEntity 
		
	}
	
	

}
