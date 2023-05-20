package com.examples.lld.bms.controllers;

import com.examples.lld.bms.dtos.*;
import com.examples.lld.bms.models.User;
import com.examples.lld.bms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    public SignupResponseDto signUp(SignupRequestDto request){
        SignupResponseDto response = new SignupResponseDto();
        try{
            User user = userService.signUp(request.getEmail(), request.getPassword());
            response.setUserId(user.getId());
            response.setStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            response.setStatus(ResponseStatus.FAILURE);
            response.setExceptionMessage(e.getMessage());
        }
        return response;
    }

    public LoginResponseDto login(LoginRequestDto loginRequest){
        LoginResponseDto response = new LoginResponseDto();
        try{
            User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            response.setUserId(user.getId());
            response.setStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            response.setStatus(ResponseStatus.FAILURE);
            response.setExceptionMessage(e.getMessage());
        }
        return response;
    }
}
