package com.springframework.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springframework.domain.Appointment;
import com.springframework.domain.User;
import com.springframework.dto.KKeshReqObjectDTO;
import com.springframework.dto.UserReqDTO;
import com.springframework.services.AppointmentService;
import com.springframework.services.UserService;

@RestController
@RequestMapping(value = "kkesh")
public class kkeshController{
	
	@Autowired
	private final UserService userService;
	@Autowired
	private final AppointmentService appointmentService;
	
	
	public kkeshController(UserService userService,AppointmentService appointmentService)
	{
		this.userService        = userService;
		this.appointmentService = appointmentService;
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
	
	@GetMapping(value = "getAppointmentbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Optional<Appointment>>  getAppointmentById(@PathVariable("id") long id){
		Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
		if(appointment != null)
        return new ResponseEntity<>(appointment, HttpStatus.OK);
		else
				return new ResponseEntity<>(appointment, HttpStatus.NOT_FOUND);
    }
	
	
	@RequestMapping("/kkesh")
    public ModelAndView mainPage(ModelMap model)
    {
		model.addAttribute("start", "1");
    	return new ModelAndView("/html/kkeshLogin", model);
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

	
    @RequestMapping("/redirect")
    public ModelAndView  targetScreen(ModelMap model)
    {
    	model.addAttribute("attribute", "redirectWithRedirectPrefix");
    	return new ModelAndView("/html/kkeshLogin", model);
    }  
    
    
}
