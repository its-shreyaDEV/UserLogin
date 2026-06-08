package com.tatapower.demo.ticket_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//web controller
import org.springframework.stereotype.Controller;
//conroller to html
import org.springframework.ui.Model;
//handles http
import org.springframework.web.bind.annotation.GetMapping;
//data to object
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tatapower.demo.ticket_management_system.entity.ActivityResp;
import com.tatapower.demo.ticket_management_system.entity.UserEntity;
import com.tatapower.demo.ticket_management_system.service.IUserService;


@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	
	@GetMapping("/login")
	public String showLoginPage(Model model) {
		 model.addAttribute("user", new UserEntity());
		  return "Login";
	}
	
	
	@PostMapping("/login")
	public String handlelogin(@RequestParam String username, @RequestParam String password, Model model,
			RedirectAttributes redirectAttributes) {

		if (username == null) { 
			redirectAttributes.addFlashAttribute("user", new UserEntity());
			redirectAttributes.addFlashAttribute("error", "Invalid username or password");
			return "redirect:/login";
		}else {
			UserEntity loggedInUser = userService.login(username, password);
			if(loggedInUser !=null) {
			if(loggedInUser.getUsername() !=null && !loggedInUser.getUsername().equals("")) {
				List<UserEntity> allUsers = userService.getAllUser();
				
				System.out.println(allUsers);
				model.addAttribute("loggedInUser", username);
				model.addAttribute("users", allUsers);
				return "Dashboard";
			}else {
				redirectAttributes.addFlashAttribute("user", new UserEntity());
				redirectAttributes.addFlashAttribute("error", "Invalid username or password"); 
				return "redirect:/login";
			}
			}
		} 
		redirectAttributes.addFlashAttribute("error", "Invalid username or password");
		redirectAttributes.addFlashAttribute("user", new UserEntity());
		return "redirect:/login";
	}

	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("user", new UserEntity());
		return "Register";
	}

	@PostMapping("/register")
	public String handleRegister(@ModelAttribute("user") UserEntity newUser) {
		newUser.setPassword("12345");
		System.out.println(newUser);
		userService.saveUser(newUser);
		return "redirect:/dashboard";
	}

	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		List<UserEntity> allUsers = userService.getAllUser();
		model.addAttribute("users", allUsers);
		model.addAttribute("loggedInusers", new UserEntity());
		return "Dashboard";
	}
	
	@GetMapping("/updatePassword")
	public String UpdatePassword(Model model) {
		model.addAttribute("user", new UserEntity());
		return "update_Password";
	}
	
	@GetMapping("/editUser")
	public String showEditPage(@RequestParam Long userId, Model model) {
	    UserEntity existingUser = userService.getUserById(userId);
	    System.out.println(existingUser);
	    model.addAttribute("user", existingUser); 
	    return "Register"; 
	}
	
	
	@PostMapping("/updateUser")
	public String handleUpdate(@RequestParam Long userId, @ModelAttribute("user") UserEntity updatedUser,
			RedirectAttributes redirectAt) {
		updatedUser.setSlNo(userId);
		updatedUser.setStatus(1);
		ActivityResp activityResp = userService.updateUser(updatedUser);
		redirectAt.addFlashAttribute("respObj",activityResp);
		redirectAt.addFlashAttribute("successMessage", "User updated successfully");
		return "redirect:/dashboard";
	}
	
	@GetMapping("/deleteUser")
	public String DeleteUser(@RequestParam Long userId, Model model) {
	    UserEntity existingUser = userService.getUserById(userId);
	    System.out.println(existingUser); 
	    return "redirect:/dashboard";
	}
	

	}
		
		
 		
		
