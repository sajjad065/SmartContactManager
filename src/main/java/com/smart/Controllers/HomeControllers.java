package com.smart.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.DAO.UserRepository;
import com.smart.Entities.User;
import com.smart.Message.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeControllers {
	@Autowired
	UserRepository repo;
	
	@GetMapping("/")
	public String home(Model m)
	{
		m.addAttribute("title", "Home Page");
		return "home";
	
	}
	@GetMapping("/about")
	public String about(Model m)
	{
		m.addAttribute("title", "About Page");
		return "about";
	}
	
	@GetMapping("/signin")
	public String login(Model m)
	{
		m.addAttribute("title", "Login Page");
		return "login";
	}
	@GetMapping("/signup")
	public String signup(Model m, HttpSession session)
	{
		m.addAttribute("title", "SignUp Page");
		m.addAttribute("user", new User());
		session.removeAttribute("message");
		 
		return "signup";
	}
	
	
	//This handler method handles the Post request.
	//This method will run when user submit a request for a signup 
	//Errors are handled as well
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam(value="terms", defaultValue="false") 
			 boolean agreement,  Model model, HttpSession session)
	{
		try {
		
		if(!agreement)
		{
			throw new Exception("Please Accept Terms and Conditions");
			
		}
		if(result.hasErrors())
		{
			System.out.println(result.toString());
			model.addAttribute("user", user);
			return "signup";
		}
		user.setRole("ROLE_USER");
		user.setEnabled(true);
		
		repo.save(user);
		model.addAttribute("user",user);
		System.out.println("agreement " +agreement);
		System.out.println(user);
		model.addAttribute("user", new User());
		System.out.println("Registered Successfully");
		session.setAttribute("message",new Message("Registered Successfully","alert-success"));
		System.out.println("Session Message: " + session.getAttribute("message"));

		return "signup";
		}
		
		catch(Exception e )
		{
			System.out.println(e.getMessage());
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Please Accept terms and conditions","alert-danger"));
			System.out.println("Session Message: " + session.getAttribute("message"));

			return "signup";
		}
	}

}
