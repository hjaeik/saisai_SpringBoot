package com.saisai.web.chat;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@Document(value = "ChatRoom")
@ToString
public class ChatRoom {
    @Id
    private String code;
    private String orderCode;
    private Date createdAt;
    private Object avatarImage;
    private String name;
    private Map<String, String> participant;
    private String status;

    @Builder
    public ChatRoom(String code, String orderCode, Date createdAt, Object avatarImage, String name, Map<String, String> participant, String status) {
        this.code = code;
        this.orderCode = orderCode;
        this.createdAt = createdAt;
        this.avatarImage = avatarImage;
        this.name = name;
        this.participant = participant;
        this.status = status;
    }
}
