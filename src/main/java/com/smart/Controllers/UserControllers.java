package com.smart.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserControllers {
	
	@GetMapping("/user-dash")
	public String UserDashBorad()
	{
		return "user-dashborad";
	}

}
