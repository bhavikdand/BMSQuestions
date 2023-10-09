package com.example.ecom.services;

import com.example.ecom.exceptions.*;
import com.example.ecom.models.Feature;
import com.example.ecom.models.SeatType;
import com.example.ecom.models.Show;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.List;

public interface ShowService {
    public Show createShow(int userId, int movieId, int screenId, Date startTime, Date endTime, List<Pair<SeatType, Double>> pricingConfig, List<Feature> features) throws MovieNotFoundException, ScreenNotFoundException, FeatureNotSupportedByScreen, InvalidDateException, UserNotFoundException, UnAuthorizedAccessException;
}
