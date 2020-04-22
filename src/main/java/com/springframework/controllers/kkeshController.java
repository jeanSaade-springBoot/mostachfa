package com.springframework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.domain.User;
import com.springframework.dto.UserReqDTO;
import com.springframework.services.UserService;

@RestController
@RequestMapping(value = "user")
@Controller
public class kkeshController {
	
	@Autowired
	private final UserService userService;
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
	
	
	
	
    @RequestMapping("/")
    public String redirectToLogScreen(){
        return "redirect:/html/kkeshLogin";
    }
    
    @RequestMapping({"/html/kkeshLogin", "/html"})
    public String login(Model model){
      //  model.addAttribute("products", productService.listAll());
        return "html/kkeshLogin";
    }
}
