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
		ResponseDto<SignupResponseDto> response = userController.signUp(new SignupRequestDto("krantikumar.ch@gmail.com", "welcome"));
		SignupResponseDto signupResponseDto = response.body();
		System.out.println("Sign UP response "+response.responseStatus());
		showExceptionMessage(response);
	}

	private void login(){
		ResponseDto<LoginResponseDto> responseDto = userController.login(new LoginRequestDto("krantikumar.ch@gmail.com", "welcome1"));
		LoginResponseDto response = responseDto.body();
		System.out.println("Login response "+responseDto.responseStatus());
		showExceptionMessage(responseDto);

	}

	private void showExceptionMessage(ResponseDto<?> response){
		String message = response.message();
		if(message != null && !message.isBlank()){
			System.out.println("exception Message: "+message);
		}
	}
}
