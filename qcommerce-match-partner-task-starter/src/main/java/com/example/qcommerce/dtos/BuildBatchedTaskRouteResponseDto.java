package com.example.qcommerce.dtos;

import com.example.qcommerce.models.Location;
import lombok.Data;

import java.util.List;

@Data
public class BuildBatchedTaskRouteResponseDto {
    private List<Location> routeToBeTaken;

    private ResponseStatus status;
}
