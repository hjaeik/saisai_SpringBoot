package com.saisai.web.util.tmap;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Coordinate {
    String longitude;
    String latitude;
}
