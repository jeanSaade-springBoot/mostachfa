package com.springframework.controllers;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springframework.domain.Appointment;
import com.springframework.domain.AppointmentTrack;
import com.springframework.domain.Doctor;
import com.springframework.domain.User;
import com.springframework.dto.AppointmentReqDTO;
import com.springframework.dto.AppointmentTrackReqDTO;
import com.springframework.dto.DoctorReqDTO;
import com.springframework.dto.KKeshReqObjectDTO;
import com.springframework.dto.UserReqDTO;
import com.springframework.exceptions.SystemException;
import com.springframework.services.AppointmentService;
import com.springframework.services.AppointmentTrackService;
import com.springframework.services.DoctorService;
import com.springframework.services.UserService;
import com.springframework.enums.AppointmentStatusEnum;
import com.springframework.enums.RedirectPagesEnum;

import static org.apache.commons.lang.StringUtils.isBlank;
@RestController
@RequestMapping(value = "kkesh")
public class kkeshController{
	
	@Autowired
	private final UserService userService;
	@Autowired
	private final AppointmentService appointmentService;
	@Autowired
	private final AppointmentTrackService appointmentTrackService;
	@Autowired
	private final DoctorService doctorService;

	public kkeshController(UserService userService,
			AppointmentService appointmentService,
			AppointmentTrackService appointmentTrackService,
			DoctorService doctorService) {
		this.userService             = userService;
		this.appointmentService      = appointmentService;
		this.appointmentTrackService = appointmentTrackService;
		this.doctorService           = doctorService;
	}
	/*
	 *Begin user/patient section
	 */
	@PostMapping(value = "registeruser", produces = MediaType.APPLICATION_JSON_VALUE)
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
	/*
	 * end user/patient section
	 */
	
	/*
	 *Begin Doctor section
	 */
	@PostMapping(value = "savedoctor", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Doctor>  saveDoctor(@RequestBody DoctorReqDTO doctorReqDTO){
        return new ResponseEntity<>(this.doctorService.saveDoctor(doctorReqDTO), HttpStatus.OK);
    }
	
	@GetMapping(value = "getdoctors", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<List<Doctor>>  getDoctors(@RequestParam(name = "name") String name){
		List<Doctor> doctorsLst;
		if(isBlank(name))
			doctorsLst = doctorService.getAllDoctors();
		else
			doctorsLst = doctorService.getDoctorsByFNameOrLNameContainingIgnoreCase(name);
		
		if(doctorsLst != null)
			return new ResponseEntity<>(doctorsLst, HttpStatus.OK);
		else
			return new ResponseEntity<>(doctorsLst, HttpStatus.NOT_FOUND);
    }
	/*
	 *End Doctor section
	 */
	
	/*
	 *Begin Appointment section
	 */
	
	@PostMapping(value = "saveappointment", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Appointment>  saveAppointment(@RequestBody AppointmentReqDTO appointmentReqDTO){
        return new ResponseEntity<>(this.appointmentService.saveAppointment(appointmentReqDTO), HttpStatus.OK);
    }
	
	@GetMapping(value = "getAppointmentbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Optional<Appointment>>  getAppointmentById(@PathVariable("id") long id){
		Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
		if(appointment.isPresent())
			return new ResponseEntity<>(appointment, HttpStatus.OK);
		return new ResponseEntity<>(appointment, HttpStatus.NOT_FOUND);
    }
	
	@PostMapping(value = "assignappointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<AppointmentTrack>  assignAppointment(@RequestBody AppointmentTrackReqDTO appointmentTrackReqDTO){
		AppointmentTrack appointmentTrack = null;
		try {
			appointmentTrack = this.appointmentTrackService.closeAppointmentTrack(appointmentTrackReqDTO);
			appointmentTrack = this.appointmentTrackService.saveAppointmentTrack(appointmentTrackReqDTO);
			appointmentService.updateAppointmentStatus(appointmentTrack.getAppointmentId(), AppointmentStatusEnum.PENDING.name());
			
			return new ResponseEntity<>(
					appointmentTrack,
					HttpStatus.OK);
		} catch (SystemException e) {
			return new ResponseEntity<>(appointmentTrack, HttpStatus.NOT_FOUND);
		}
	}
	/*
	 *End Appointment section
	 */
	
	/*
	 *Begin redirection section
	 */
	@RequestMapping("/mainscreen")
    public ModelAndView mainPage(ModelMap model)
    {
		model.addAttribute("start", "1");
    	return new ModelAndView("/html/kkeshLogin", model);
    }
	
	
	@RequestMapping(value = "/redirect", method = RequestMethod.POST)
    public KKeshReqObjectDTO redirectTotarget(@RequestBody KKeshReqObjectDTO kkeshReqObjectDTO,final RedirectAttributes redirectAttributes)
    {
      	String patientId = kkeshReqObjectDTO.getPatientId();
      	RedirectPagesEnum redirectEnum = RedirectPagesEnum.valueOf(kkeshReqObjectDTO.getRedirectEnum());
      	String redirectUrl = redirectEnum.label;
      	
      	ModelMap model = new ModelMap();
      	model.addAttribute("kkeshReqObjectDTO", kkeshReqObjectDTO);
      	return kkeshReqObjectDTO;
    }

	
    @RequestMapping("/redirect")
    public ModelAndView  targetScreen(ModelMap model)
    {
    	model.addAttribute("attribute", "redirectWithRedirectPrefix");
    	return new ModelAndView("/html/appointmentDetail", model);
    }  
    /*
	 *End Redirection section
	 */
    
}