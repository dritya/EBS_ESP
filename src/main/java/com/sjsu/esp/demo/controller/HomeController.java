package com.sjsu.esp.demo.controller;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sjsu.esp.demo.model.Activities;
import com.sjsu.esp.demo.model.User;
import com.sjsu.esp.demo.services.impl.UserServiceImpl;

import ch.qos.logback.core.net.SyslogOutputStream;


@Controller
public class HomeController {
	
	@Autowired 
	UserServiceImpl userServiceImpl;
	
	public String email = "";
	
	@RequestMapping("/")
	public String index(Principal principal){
		
		email = principal.getName();
		System.out.println("Email under root: "+email);
		User user = null;
		try {
			user = userServiceImpl.createUser(email);
		} catch (javax.xml.bind.ValidationException e) {
			e.printStackTrace();
		}
		
	    if (user.getIs_admin().equalsIgnoreCase("true")) {
	    		return "redirect:/adminPage";
	    } else {
	    		return "redirect:/index";
	    }
	}
	
	@PostMapping("/idealweight")
	public String idealWeight(@RequestParam("height") String height, @RequestParam("gender") String gender, HttpSession session){
		
		System.out.println("I am here........");
		System.out.println("Height********* "+height);
		System.out.println("gender********* "+gender);
		 String ideal = userServiceImpl.idealWeight(height, gender);
		 System.out.println("ideal************"+ideal);
		 if (null != ideal) {
			 session.setAttribute("weight", ideal);
			 return "redirect:/test";
		 } else {
			 return null;
		 }
	}
	
	
	
	@GetMapping("/idealweight")
	public String idealWeight1(@RequestParam("height") String height, @RequestParam("gender") String gender, HttpSession session){
		
		System.out.println("I am here........");
		System.out.println("Height********* "+height);
		System.out.println("gender********* "+gender);
		 String ideal = userServiceImpl.idealWeight(height, gender);
		 System.out.println("ideal************"+ideal);
		 if (null != ideal) {
			 session.setAttribute("weight", ideal);
			 return "redirect:/index";
		 } else {
			 return null;
		 }
	}
	
	@GetMapping("/index")
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/adminPage")
	public String adminPage() {
		return "adminPage";
	}

	
	
/*	@GetMapping("/")
	public String test() {
		return "index";
	}*/
	
	//@PostMapping("/showactivities")
	@GetMapping("/showactivities")
	public String showActivities(HttpSession session){
		
		List<Activities> abs = userServiceImpl.getActivities("abs");
		List<Activities> biceps = userServiceImpl.getActivities("biceps");
		List<Activities> chest = userServiceImpl.getActivities("chest");
		List<Activities> back = userServiceImpl.getActivities("back");
		List<Activities> shoulder = userServiceImpl.getActivities("shoulder");
		List<Activities> tricep = userServiceImpl.getActivities("tricep");
		List<Activities> leg = userServiceImpl.getActivities("leg");
		
		session.setAttribute("abs", abs);
		session.setAttribute("biceps", biceps);
		session.setAttribute("chest", chest);
		session.setAttribute("back", back);
		session.setAttribute("shoulder", shoulder);
		session.setAttribute("tricep", tricep);
		session.setAttribute("leg", leg);
		
		return "activities";
	}
	
	@GetMapping("/activities")
	public String activitiesPage() {
		return "activities";
	}
	
	@PostMapping("/recorduseractivity")
	public String recordUserActivity(@RequestParam("muscle") String muscle, @RequestParam("exercise") String exercise, @RequestParam("time") String time){

		userServiceImpl.addUserActivity(email, muscle, exercise, time);
		return "activities";
	}
	
	
	@GetMapping("/recorduseractivity")
	public String recordUserActivity1(@RequestParam("muscle") String muscle, @RequestParam("exercise") String exercise, @RequestParam("time") String time){

		userServiceImpl.addUserActivity(email, muscle, exercise, time);
		return "activities";
	}

	@PostMapping("/calculatecaloriesburnt")
	public String calculateCaloriesBurnt(HttpSession session){
		double calories = userServiceImpl.calculateCaloriesBurnt(email);
		session.setAttribute("caloriesburnt", calories);
		return "redirect:/activities";
	}
	
	@GetMapping("/calculatecaloriesburnt")
	public String calculateCaloriesBurnt1(HttpSession session){
		double calories = userServiceImpl.calculateCaloriesBurnt(email);
		session.setAttribute("caloriesburnt", calories);
		return "redirect:/activities";
	}
	

	@PostMapping("/adminaddnewactivity")
	public String adminAddNewActivity(@RequestParam("muscle") String muscle, @RequestParam("exercise") String exercise,
			@RequestParam("time") String time, @RequestParam("calories") String calories){
		userServiceImpl.adminAddNewActivity(muscle, exercise, time, calories);
		return null;
	}
	
	
	@GetMapping("/adminaddnewactivity")
	public String adminAddNewActivity1(@RequestParam("muscle") String muscle, @RequestParam("exercise") String exercise,
			@RequestParam("time") String time, @RequestParam("calories") String calories, HttpSession session){
		userServiceImpl.adminAddNewActivity(muscle, exercise, time, calories);
		session.setAttribute("message", "Activity Added succesfully");
		return "adminPage";
	}
	
	
}
