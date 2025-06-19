package com.shopzone.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.shopzone.app.controller.UserController;
import com.shopzone.app.dto.EmailRequest;
import com.shopzone.app.dto.EmailResponse;
import com.shopzone.app.dto.UserDto;
import com.shopzone.app.dto.VerifyEmailOtpRequest;
import com.shopzone.app.repo.UserRepo;

@Service
public class EmailService {

	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private GeneralParameterService generalParameterService;

	@Autowired
	private UserRepo userRepo;
	
	private static final Logger log = LoggerFactory.getLogger(EmailService.class);
	
	@Async
	public void sendWelcomeEmail(UserDto userDto) {
		
		log.info("Registration email sending by thread "+Thread.currentThread());
		try {
			if("Y".equals(generalParameterService.fetchParameterValue("registration_email"))) {
				log.info("Registration email sent flag true");
			    EmailRequest emailRequest= new EmailRequest();
			    emailRequest.setEmail(userDto.getEmail());
			    String message = "Welcome to Booking App!\n\n"
			               + "Your username is: " + userDto.getUsername() + "\n"
			               + "We're excited to have you with us.";

			    emailRequest.setMessage(message);
//			    emailRequest.setMessage("Welcome to booking app /n Your username is +userdto.getusername");
				ResponseEntity<EmailResponse> response = restTemplate.postForEntity(
    		    "http://localhost:8081/email/welcome",
    		    emailRequest,
    		    EmailResponse.class
    		);

    		if (response.getStatusCode().is2xxSuccessful()) {
    		    EmailResponse body = response.getBody();
    		    log.info("Email status: " + body.getStatus());
    		    log.info("Message: " + body.getMessage());
    		}
			}
		}catch (ResourceAccessException e) {
			 log.error("Email service is not responding", e);
		}
		
		catch (Exception e) {
		    log.error("Exception during email sending", e);
		}
		
	}

	public String sendOtp(EmailRequest req) {
		String resp;
		
		
		try {
//			if (userRepo.existsByEmail(req.getEmail())) {
//			    return "Email already in use: " + req.getEmail();
//			}
			
			
		EmailRequest emailRequest= new EmailRequest();
		emailRequest.setEmail(req.getEmail());
		ResponseEntity<String> response = restTemplate.postForEntity(
    		    "http://localhost:8081/email/send-otp",
    		    emailRequest,
    		    String.class
    		);
		String msg=response.getBody();
		resp="Otp sent on email";
		}catch (ResourceAccessException e) {
			resp="Email service is not responding";
			 log.error("Email service is not responding", e);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			resp="Email service error";
		    log.error("Exception during email sending", e);
		}
		return resp;
	}

	public String verifyOtp(VerifyEmailOtpRequest req) {
		String msg;
		try {

			ResponseEntity<String> response = restTemplate.postForEntity(
	    		    "http://localhost:8081/email/verify-otp",
	    		    req,
	    		    String.class
	    		);
			msg=response.getBody();
			log.info(msg);
			return msg;
			}catch (ResourceAccessException e) {
				 log.error("Email service is not responding", e);
			}
			
			catch (Exception e) {
				e.printStackTrace();
			    log.error("Exception during email sending", e);
			}
		return null;
	}

	public String sendOrderConfirmedEmail(EmailRequest req) {
		String msg;
		ResponseEntity<String> response = restTemplate.postForEntity(
    		    "http://localhost:8081/email/notify",
    		    req,
    		    String.class
    		);
		msg=response.getBody();
		log.info(msg);
		return msg;
	}
}
