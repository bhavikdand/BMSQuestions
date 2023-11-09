package com.example.bmsbookticket.controllers;

import com.example.bmsbookticket.dtos.*;
import com.example.bmsbookticket.models.User;
import com.example.bmsbookticket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    public SignupUserResponseDTO signupUser(SignupUserRequestDTO requestDTO){
        return null;
    }

    public LoginResponseDto login(LoginRequestDto requestDto){
        return null;
    }

}
