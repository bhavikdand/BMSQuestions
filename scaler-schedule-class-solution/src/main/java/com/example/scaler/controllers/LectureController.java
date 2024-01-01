package com.example.scaler.controllers;

import com.example.scaler.dtos.AccessLectureRequestDto;
import com.example.scaler.dtos.AccessLectureResponseDto;
import com.example.scaler.dtos.ResponseStatus;
import com.example.scaler.models.LearnerProgress;
import com.example.scaler.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LectureController {

    private LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    public AccessLectureResponseDto accessLecture(AccessLectureRequestDto requestDto){
        AccessLectureResponseDto responseDto = new AccessLectureResponseDto();
        try{
            LearnerProgress learnerProgress = lectureService.accessLecture(requestDto.getLearnerId(), requestDto.getScheduledLectureId(), requestDto.getLectureLink());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setLearnerProgress(learnerProgress);
        } catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
