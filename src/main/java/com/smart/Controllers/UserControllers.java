package com.smart.Controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smart.DAO.UserRepository;
import com.smart.Entities.Contact;
import com.smart.Entities.User;
import com.smart.ImageHandler.ImageRetrieve;
import com.smart.Message.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

//This is the controller class that will be mapped with /user/** handler .
@Controller
@RequestMapping("/user")
public class UserControllers {
	
	Principal principal;
	@Autowired
	UserRepository userRepo;  //injecting UserReposiory by Autowiring
	
	@Autowired
	ImageRetrieve imageRetrieve;
	
	
	//Principal object will get the authenticated username from the login page 
	//ModelAttribute make sure that this method is executed before request made goes to any handler method
	@ModelAttribute
	public void addUserToModel(Model model,Principal principal)
	{
		this.principal=principal;
	User user =userRepo.findByEmail(principal.getName());  //findByEmail() returns User object based on username(email in this case)
	System.out.println(user); //printing value in console for testing purpose
	model.addAttribute("user",user); //setting up the Model object with the user attribute to pass in the next page
	}
	
	
	
	
	
	
   
	//This endpoints redirect user to their dashboard.
	//At first it will ask for authentication
	@GetMapping("/user-dash")  //mapping below 'UserDashBorad()' method with the handler "/user-dash"
	public String UserDashBorad()
	{
		
		return "/Normal/user-dashboard";   //return user-dashborad.html from User folder of templates
	}

	
	
	
	
	
	
	
	//mapping 'addContact()' method with the endpoint "/addContact"
	//This endpoint will take user to a  add a new contact form where user can add a new contact by filling up the contact details
	@GetMapping("/addContact") 
	public String addContact(Model model)  
	{
		model.addAttribute("contact", new Contact());
		
		return "/Normal/addContact"; //this handler method will return addContact.html page
	}
	
	
	
	
	
	
	
	
	//This method is responsible for processing the form named "add a new contact"
	//This handler method handles the Post request.
	//Errors are handled as well
	@PostMapping("/process")
	public String processContactForm(@Valid @ModelAttribute("contact") Contact contact, BindingResult result,@RequestParam("fileimage") MultipartFile file, Model model) throws IOException
	{
		boolean imageStored=imageRetrieve.storeImage(file); //calling storeImage() method of class "ImageRetrieve
		
		
		try							//when there is an error in the form then exception will be thrown and handle in the catch block
		{
			
		if(result.hasErrors())    //check whether there is error in the form or not and image  is empty
		{
			

			throw new Exception("Error in form" +result.toString()); //explicitly throwing the error
		}
		}
		
		catch(Exception e )    // this catch block will run only if there is error in the form as error will trigger the throw method
		{
			System.out.println(e.getMessage());
			return "/Normal/addContact";
		}
		
		
		//if there is no error in the form then below part will be executed
		String email=principal.getName(); //getting email from Principal object
		User user=userRepo.findByEmail(email); //fetching user object from logged in user based on email storeImage() method of class ImageRetrieve
		contact.setUser(user);  //setting user field of contact with user object
		user.getContacts().add(contact);  //it will call the all the list of contacts that have been already added in User object and add the current contact object in it
		
		  //if imageStored is true then it will be executed. we are getting value by calling storeImage(file) method
		if(imageStored)
		{
			contact.setImage(file.getOriginalFilename());
		
		}
		userRepo.save(user);  //saving user object in database. This is update the user information with new contact in it and also update contact database based on user id.
		System.out.println(contact.toString()); //printing the data of contact object in console for testing
		
		model.addAttribute("SucessMessage", new Message("New Contact added successfully","alert-success"));
		System.out.println(model.getAttribute("SucessMessage")); //displaying message in console for successfull addition of new contact
		return "/Normal/addContact";
		
		
	}
	
	
}
