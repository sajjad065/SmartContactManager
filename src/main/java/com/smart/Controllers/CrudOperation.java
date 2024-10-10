package com.smart.Controllers;

import java.security.Principal;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smart.DAO.ContactRepository;
import com.smart.DAO.UserRepository;
import com.smart.Entities.Contact;
import com.smart.Entities.User;
import com.smart.Message.Message;

import jakarta.validation.Valid;

@Controller
public class CrudOperation {
	
	@Autowired
	ContactRepository contactRepo;
	@Autowired
	UserRepository userRepo;
	

	
	
	@GetMapping("/deleteContact/{cid}")
	public String deleteContact(@PathVariable("cid") int cid)
	
	{
		System.out.println("cid is " +cid);
	     contactRepo.deleteById(cid);
		 return "redirect:/user/viewContacts/0";
	}

	
	

	@PostMapping("/updateForm/{cid}")
	public String UpdateContactForm(@PathVariable("cid") int cid, Model model)
	
	{ 
	  java.util.Optional<Contact> contacts=contactRepo.findById(cid);
	  if(contacts.isPresent())
	  {
	  Contact contact=contacts.get();
	  model.addAttribute("user",contact.getUser());
	  model.addAttribute("contact", contact);
	  }
	  else
	  {
		  throw new RuntimeException("Contact not found");
	  }
		
		
		 return "/Normal/update-form";
	}
	
	
	@PostMapping("/processUpdate")
	public String updateProcessing(@Valid @ModelAttribute("contact") Contact contact,RedirectAttributes rd, Principal principal, Model model)
	{
		String email=principal.getName();
		User user=userRepo.findByEmail(email);
		
		rd.addFlashAttribute("updateMessage",new Message("Contact Updated Successfully", "alert-success"));
		contact.setUser(user);
		contactRepo.save(contact);		//saving updated contact in the database
		return "redirect:/user/viewContacts/0";
	}
	
	
}
