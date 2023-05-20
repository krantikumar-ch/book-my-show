package com.examples.lld.bms;

import com.examples.lld.bms.controllers.UserController;
import com.examples.lld.bms.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {

	@Autowired
	private UserController userController;

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		signUp();
		login();
	}

	private void signUp(){
		SignupResponseDto response = userController.signUp(new SignupRequestDto("krantikumar.ch@gmail.com", "welcome"));
		System.out.println("Sign UP response "+response.getStatus());
		showExceptionMessage(response);
	}

	private void login(){
		LoginResponseDto response = userController.login(new LoginRequestDto("krantikumar.ch@gmail.com", "welcome1"));
		System.out.println("Login response "+response.getStatus());
		showExceptionMessage(response);

	}

	private void showExceptionMessage(BaseResponseDto response){
		String message = response.getExceptionMessage();
		if(message != null && !message.isBlank()){
			System.out.println("exception Message: "+message);
		}
	}
}
