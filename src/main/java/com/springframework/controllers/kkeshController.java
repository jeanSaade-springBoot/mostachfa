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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springframework.domain.Admin;
import com.springframework.domain.Appointment;
import com.springframework.domain.AppointmentTrack;
import com.springframework.domain.Doctor;
import com.springframework.domain.Patient;
import com.springframework.dto.AdminReqDTO;
import com.springframework.dto.AppointmentReqDTO;
import com.springframework.dto.AppointmentTrackReqDTO;
import com.springframework.dto.DoctorReqDTO;
import com.springframework.dto.KKeshReqObjectDTO;
import com.springframework.dto.PatientReqDTO;
import com.springframework.dto.SignInReqDTO;
import com.springframework.dto.SignInResponseDTO;

import com.springframework.exceptions.SystemException;
import com.springframework.services.AdminService;
import com.springframework.services.AppointmentService;
import com.springframework.services.AppointmentTrackService;
import com.springframework.services.DoctorService;
import com.springframework.services.PatientService;
import com.springframework.enums.AppointmentStatusEnum;
import com.springframework.enums.RedirectPagesEnum;
import com.springframework.enums.ResponseCodeEnum;

import static org.apache.commons.lang.StringUtils.isBlank;
import com.springframework.utils.UserUtils;

import com.google.gson.Gson;
@RestController
@RequestMapping(value = "kkesh")
public class kkeshController{
	
	@Autowired
	private final PatientService patientService;
	@Autowired
	private final AppointmentService appointmentService;
	@Autowired
	private final AppointmentTrackService appointmentTrackService;
	@Autowired
	private final DoctorService doctorService;
	@Autowired
	private final AdminService adminService;

	public kkeshController(PatientService patientService,
			AppointmentService appointmentService,
			AppointmentTrackService appointmentTrackService,
			DoctorService doctorService,
			AdminService adminService) {
		this.patientService             = patientService;
		this.appointmentService      = appointmentService;
		this.appointmentTrackService = appointmentTrackService;
		this.doctorService           = doctorService;
		this.adminService            = adminService;
	}
	/*
	 *Begin user/patient section
	 */
	@PostMapping(value = "registerpatient", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Patient>  registerpatient(@RequestBody PatientReqDTO patientReqDTO){
		SignInResponseDTO signInResponseDTO = getUserByEmailFromAllEntity(patientReqDTO.getEmailAddress());
		if(signInResponseDTO != null)
			 return new ResponseEntity<>(null, HttpStatus.FOUND);
     
		return new ResponseEntity<>(this.patientService.saveUser(patientReqDTO), HttpStatus.OK);
    }
	
	@GetMapping(value = "checkifuserexists/{emailAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<SignInResponseDTO>  checkIfUserExists(@PathVariable(name = "emailAddress") String emailAddress){
		SignInResponseDTO signInResponseDTO = getUserByEmailFromAllEntity(emailAddress);
		if(signInResponseDTO != null)
		   return new ResponseEntity<>(signInResponseDTO, HttpStatus.FOUND);
		else
			return new ResponseEntity<>(signInResponseDTO, HttpStatus.OK);
    }
	
	public SignInResponseDTO getUserByEmailFromAllEntity(String emailAddress)
	{
		SignInResponseDTO signInResponseDTO = null;
		
		Optional<Patient> patient = patientService.findPatientByEmail(emailAddress);
		if(patient.isPresent())
		{
			signInResponseDTO = UserUtils.buildSigninResponseFromPatient(patient.get());
			return signInResponseDTO;
		}
		Optional<Doctor> doctor = doctorService.findDoctorByEmail(emailAddress);
		if(doctor.isPresent())
		{
			
			signInResponseDTO = UserUtils.buildSigninResponseFromDoctor(doctor.get());
			return signInResponseDTO;
		}
		Optional<Admin> admin = adminService.findAdminByEmail(emailAddress);
		if(admin.isPresent())
		{
			signInResponseDTO = UserUtils.buildSigninResponseFromAdmin(admin.get());
			return signInResponseDTO;
		}
		return signInResponseDTO;
	}
	
	
	@GetMapping(value = "findadminbyemail", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Optional<Admin>>  findAdminByEmail(@RequestParam(name = "emailAddress") String emailAddress){
    	 return new ResponseEntity<>(adminService.findAdminByEmail(emailAddress), HttpStatus.OK);
    }
	
	@GetMapping(value = "findpatientbyemail", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Optional<Patient>>  findPatientByEmail(@RequestParam(name = "emailAddress") String emailAddress){
    	 return new ResponseEntity<>(patientService.findPatientByEmail(emailAddress), HttpStatus.OK);
    }
	 
	@GetMapping(value = "finddoctorbyemail", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Optional<Doctor>>  findDoctortByEmail(@RequestParam(name = "emailAddress") String emailAddress){
    	 return new ResponseEntity<>(doctorService.findDoctorByEmail(emailAddress), HttpStatus.OK);
    }
	
	
	
	@PostMapping(value = "signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<SignInResponseDTO>  signInUser(@RequestBody SignInReqDTO signInReqDTO){
		Patient patient = patientService.signIn(signInReqDTO);
		if(patient != null)
			return new ResponseEntity<>(UserUtils.buildSigninResponseFromPatient(patient), HttpStatus.OK);
		
		Doctor doctor = doctorService.signIn(signInReqDTO);
		if(doctor != null)
			 return new ResponseEntity<>(UserUtils.buildSigninResponseFromDoctor(doctor), HttpStatus.OK);
		
		Admin admin = adminService.signIn(signInReqDTO);
		if(admin != null)
			 return new ResponseEntity<>(UserUtils.buildSigninResponseFromAdmin(admin), HttpStatus.OK);
		
	    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
	/*
	 * end user/patient section
	 */
	
	/*
	 *Begin Doctor section
	 */
	@PostMapping(value = "registerdoctor", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Doctor>  registerDoctor(@RequestBody DoctorReqDTO doctorReqDTO){
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
	@PostMapping(value = "registeradmin", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Admin>  saveAdmin(@RequestBody AdminReqDTO adminReqDTO){
        return new ResponseEntity<>(this.adminService.saveAdmin(adminReqDTO), HttpStatus.OK);
    }
	
	/*
	 *Begin Appointment section
	 */
	
	@PutMapping(value = "saveappointment", produces = MediaType.APPLICATION_JSON_VALUE)
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
			appointmentService.updateAppointmentStatus(appointmentTrack.getAppointmentId(), AppointmentStatusEnum.PENDING.getCode());
			
			return new ResponseEntity<>(
					appointmentTrack,
					HttpStatus.OK);
		} catch (SystemException e) {
			return new ResponseEntity<>(appointmentTrack, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "getappointmentadminlist/{emailAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getAppointmentAdminList(@PathVariable("emailAddress") String emailAddress)
	{
		return new ResponseEntity<>(appointmentService.getAppointmentAdminList(),HttpStatus.OK);
	}
	
	@GetMapping(value = "getappointmentdoctorlist/{emailAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getAppointmentDoctorList(@PathVariable("emailAddress") String emailAddress)
	{
		Optional<Doctor> doctOpt = doctorService.findDoctorByEmail(emailAddress);
		Doctor doctor = doctOpt.get();
		return new ResponseEntity<>(appointmentService.getAppointmentDoctorList(doctor.getId(),AppointmentStatusEnum.PENDING),HttpStatus.OK);
	}
	
	@GetMapping(value = "getappointmentPatientlist/{emailAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getAppointmentPatientList(@PathVariable("emailAddress") String emailAddress)
	{
		Optional<Patient> patientOpt = patientService.findPatientByEmail(emailAddress);
		Patient patient = patientOpt.get();
		return new ResponseEntity<>(appointmentService.getAppointmentPatientList(patient.getId(),AppointmentStatusEnum.PENDING),HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "getappointmentlstbyuser/{emailAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getAppointmentLstByUser(@PathVariable("emailAddress") String emailAddress)
	{
		List<Appointment> appointmentLst = null;
		SignInResponseDTO signInResponseDTO = getUserByEmailFromAllEntity(emailAddress);
		
		if(signInResponseDTO.isAdmin)
			appointmentLst = appointmentService.getAppointmentAdminList();
		if(signInResponseDTO.isDoctor)
			appointmentLst = appointmentService.getAppointmentDoctorList(signInResponseDTO.getId(),AppointmentStatusEnum.PENDING);
		if(signInResponseDTO.isPatient)
			appointmentLst = appointmentService.getAppointmentPatientList(signInResponseDTO.getId(),AppointmentStatusEnum.PENDING);
		
		return new ResponseEntity<>(appointmentLst,HttpStatus.OK);
	}
	/*
	 *End Appointment section
	 */
	
	/*
	 *Begin redirection section
	 */
	
	@RequestMapping( value =  "/secureforward/{pageEnum}/{emailAddress}")
    public ModelAndView mainPage(ModelMap model,@PathVariable(name = "pageEnum") String pageEnum,@PathVariable(name = "emailAddress") String emailAddress)
    {
		RedirectPagesEnum redirectEnum = RedirectPagesEnum.valueOf(pageEnum);
		String redirectUrl = redirectEnum.label;
		if(!isBlank(emailAddress))
		{   Gson gson = new Gson();
			SignInResponseDTO signInResponseDTO = getUserByEmailFromAllEntity(emailAddress);
			model.addAttribute("signInResponseDTO", gson.toJson(signInResponseDTO));
		}
    	return new ModelAndView(redirectUrl, model);
    }

    /*
	 *End Redirection section
	 */
    
}
