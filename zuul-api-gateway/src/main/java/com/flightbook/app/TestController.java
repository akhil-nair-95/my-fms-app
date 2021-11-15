package com.flightbook.app;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		String pass = "admin";
		String encodedPass = passEncoder.encode(pass);
		System.out.println(encodedPass);
	}

}
