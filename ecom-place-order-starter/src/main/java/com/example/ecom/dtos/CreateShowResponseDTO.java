package com.example.ecom.dtos;

import com.example.ecom.models.Show;
import lombok.Data;

@Data
public class CreateShowResponseDTO {
    private ResponseStatus responseStatus;
    private Show show;
}
