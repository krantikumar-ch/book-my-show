package com.examples.lld.bms.controllers;

import com.examples.lld.bms.dtos.*;
import com.examples.lld.bms.models.User;
import com.examples.lld.bms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import static com.examples.lld.bms.DtoUtils.convertToDto;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    public ResponseDto<SignupResponseDto> signUp(SignupRequestDto request){
        try{
            User user = userService.signUp(request.getEmail(), request.getPassword());
            SignupResponseDto SignupResponseDto = convertToDto(user, SignupResponseDto.class);
            return new ResponseDto<>(ResponseStatus.SUCCESS, SignupResponseDto);
        }
        catch(Exception e){
            return new ResponseDto<>(ResponseStatus.FAILURE, e.getMessage());
        }
    }

    public ResponseDto<LoginResponseDto> login(LoginRequestDto loginRequest){

        try{
            User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            LoginResponseDto loginResponseDto = convertToDto(user, LoginResponseDto.class);
            return new ResponseDto<>(ResponseStatus.SUCCESS, loginResponseDto);
        }
        catch(Exception e){
            return new ResponseDto<>(ResponseStatus.FAILURE, e.getMessage());
        }

    }

}
