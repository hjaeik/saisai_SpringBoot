package com.saisai.web.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Coordinate {
    private String latitude;
    private String longitude;

    @Builder
    Coordinate(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
