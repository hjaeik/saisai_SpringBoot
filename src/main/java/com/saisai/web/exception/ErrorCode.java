package com.saisai.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
//    Common
    BADREQUEST(400,"BadRequest"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Page Not Found"),
    SERVERERROR(500, "Server Error"),

//    CUSTOM
    MISS_ROUTE_DATA(400, "Miss Match Route Data"),
    API_BAD_REQUEST(400, "Miss Data"),

//    API SERVER
    TMAP_API_BADREQUEST(400, "API Server Error"),
    TMAP_API_SERVERERROR(500, "API Server Error"),;


    private int status;
    private String message;
}
