package com.saisai.web.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
public class OrderRouteDto {
    private String startX;
    private String endX;
    private String startY;
    private String endY;
    private String reqCoordType;
}
