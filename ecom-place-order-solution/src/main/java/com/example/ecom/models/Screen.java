package com.example.ecom.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "screens")
public class Screen extends BaseModel{
    private String name;
    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;

    private ScreenStatus status;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.ORDINAL)
    private List<Feature> features;

}
