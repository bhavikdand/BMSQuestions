package com.example.bmsbookticket.controllers;

import com.example.bmsbookticket.dtos.*;
import com.example.bmsbookticket.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @Test
    public void testSignupUserSuccess_AndLoginSuccess() {
        SignupUserRequestDTO requestDTO = new SignupUserRequestDTO();
        String email = "test@scaler.com";
        String password = "Password@123";
        String name = "Test User";
        requestDTO.setEmail(email);
        requestDTO.setName(name);
        requestDTO.setPassword(password);

        SignupUserResponseDTO signupUserResponseDTO = userController.signupUser(requestDTO);
        assertEquals(ResponseStatus.SUCCESS, signupUserResponseDTO.getResponseStatus());
        assertEquals(email, signupUserResponseDTO.getEmail());
        assertEquals(name, signupUserResponseDTO.getName());

        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(email);
        loginRequestDto.setPassword(password);
        LoginResponseDto loginResponseDto = userController.login(loginRequestDto);
        assertTrue(loginResponseDto.isLoggedIn());
        assertEquals(ResponseStatus.SUCCESS, loginResponseDto.getResponseStatus());

        userRepository.deleteAll();
    }

    @Test
    public void testSignupUserSuccess_AndLoginWithIncorrectPassword() {
        SignupUserRequestDTO requestDTO = new SignupUserRequestDTO();
        String email = "test@scaler.com";
        String password = "Password@123";
        String name = "Test User";
        requestDTO.setEmail(email);
        requestDTO.setName(name);
        requestDTO.setPassword(password);

        SignupUserResponseDTO signupUserResponseDTO = userController.signupUser(requestDTO);
        assertEquals(ResponseStatus.SUCCESS, signupUserResponseDTO.getResponseStatus());
        assertEquals(email, signupUserResponseDTO.getEmail());
        assertEquals(name, signupUserResponseDTO.getName());

        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(email);
        loginRequestDto.setPassword(password + "1");
        LoginResponseDto loginResponseDto = userController.login(loginRequestDto);
        assertFalse(loginResponseDto.isLoggedIn());
        assertEquals(ResponseStatus.SUCCESS, loginResponseDto.getResponseStatus());

        userRepository.deleteAll();
    }

    @Test
    public void testSignupUser_UserAlreadyExists() {
        SignupUserRequestDTO requestDTO = new SignupUserRequestDTO();
        String email = "test@scaler.com";
        String password = "Password@123";
        String name = "Test User";
        requestDTO.setEmail(email);
        requestDTO.setName(name);
        requestDTO.setPassword(password);

        //User registering for the first time
        SignupUserResponseDTO signupUserResponseDTO = userController.signupUser(requestDTO);

        //User registering for the second time
        signupUserResponseDTO = userController.signupUser(requestDTO);
        assertEquals(ResponseStatus.FAILURE, signupUserResponseDTO.getResponseStatus());
        assertNull(signupUserResponseDTO.getEmail());
        assertNull(signupUserResponseDTO.getName());
        userRepository.deleteAll();
    }

    @Test
    public void testLogin_UserNotSignedUp() {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        String email = "test@scaler.com";
        String password = "Password@123";
        loginRequestDto.setEmail(email);
        loginRequestDto.setPassword(password);

        LoginResponseDto loginResponseDto = userController.login(loginRequestDto);
        assertFalse(loginResponseDto.isLoggedIn());
        assertEquals(ResponseStatus.FAILURE, loginResponseDto.getResponseStatus());
        userRepository.deleteAll();
    }
}
