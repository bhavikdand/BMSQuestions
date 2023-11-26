package com.example.ecom.controllers;

import com.example.ecom.dtos.GenerateRecommendationsRequestDto;
import com.example.ecom.dtos.GenerateRecommendationsResponseDto;
import com.example.ecom.dtos.ResponseStatus;
import com.example.ecom.models.Product;
import com.example.ecom.services.RecommendationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RecommendationsController {

    private RecommendationsService recommendationsService;

    @Autowired
    public RecommendationsController(RecommendationsService recommendationsService) {
        this.recommendationsService = recommendationsService;
    }

    public GenerateRecommendationsResponseDto generateRecommendations(GenerateRecommendationsRequestDto requestDto) {
        GenerateRecommendationsResponseDto responseDto = new GenerateRecommendationsResponseDto();
        try{
            List<Product> recommendations = recommendationsService.getRecommendations(requestDto.getProductId());
            responseDto.setRecommendations(recommendations);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDto;
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;
        }
    }
}
