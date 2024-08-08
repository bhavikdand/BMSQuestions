package com.example.ecom.controllers;

import com.example.ecom.dtos.DeregisterUserForNotificationRequestDto;
import com.example.ecom.dtos.DeregisterUserForNotificationResponseDto;
import com.example.ecom.dtos.RegisterUserForNotificationRequestDto;
import com.example.ecom.dtos.RegisterUserForNotificationResponseDto;
import com.example.ecom.services.NotificationService;

public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public RegisterUserForNotificationResponseDto registerUser(RegisterUserForNotificationRequestDto requestDto) {
        return null;
    }

    public DeregisterUserForNotificationResponseDto deregisterUser(DeregisterUserForNotificationRequestDto requestDto) {
        return null;
    }
}
