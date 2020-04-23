package com.springframework.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springframework.domain.User;
import com.springframework.dto.KKeshReqObjectDTO;
import com.springframework.dto.UserReqDTO;
import com.springframework.services.UserService;

@Controller
@RequestMapping(value = "kkeshuser")
@EnableWebMvc
public class kkeshController extends WebMvcConfigurerAdapter{
	
	@Autowired
	private final UserService userService;
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/html/kkeshlogin").setViewName("/kkeshuser/redirect");
    }
	
	
	public kkeshController(UserService userService)
	{
		this.userService = userService;
	}
	
	@PostMapping(value = "saveuser", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<User>  saveUser(@RequestBody UserReqDTO userReqDTO){
        return new ResponseEntity<>(this.userService.saveUser(userReqDTO), HttpStatus.OK);
    }
	
	@GetMapping(value = "signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<User>  signInUser(@RequestBody UserReqDTO userReqDTO){
		User user = userService.signIn(userReqDTO);
		if(user != null)
        return new ResponseEntity<>(user, HttpStatus.OK);
		else
				return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }
	

	@RequestMapping(value = "/redirect", method = RequestMethod.POST)
    public ModelAndView redirectTotarget(@RequestBody KKeshReqObjectDTO kkeshReqObjectDTO,final RedirectAttributes redirectAttributes)
    {
      	String patientId = kkeshReqObjectDTO.getPatientId();
      	String redirectUrl = kkeshReqObjectDTO.getRedirectUrl().name();
      	ModelMap model = new ModelMap();
      	model.addAttribute("kkeshReqObjectDTO", kkeshReqObjectDTO);
      	return new ModelAndView("/html/kkeshLogin", model);
    }

	/*
    @RequestMapping("/redirect")
    public ModelAndView  targetScreen(ModelMap model)
    {
    	model.addAttribute("attribute", "redirectWithRedirectPrefix");
    	return new ModelAndView("/html/kkeshLogin", model);
    }  
    */
    
}
