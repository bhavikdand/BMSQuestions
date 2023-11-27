package com.example.qcommerce.libraries.gmaps;

import java.util.Collections;
import java.util.List;

public class GMaps {

    public List<GLocation> getOptimizedRoute(List<GLocation> locations) {
        // API call to Google Maps API
        Collections.shuffle(locations);
        return locations;
    }
}
