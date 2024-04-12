package com.example.qcommerce.adapters;

import com.example.qcommerce.models.Location;

import java.util.List;

public interface MapsAdapter {

    public List<Location> buildRoute(List<Location> locations);
}
