package com.saisai.web.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private Boolean success;
    private Object response;
    private Object error;

    public Message(boolean success, Object data) {
        this.success = success;
        if(success) this.response = data;
        else  this.error = data;
    }
}
